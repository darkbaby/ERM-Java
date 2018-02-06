package com.esynergy.erm.web.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;
import com.esynergy.erm.model.IFileUpload;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.ExchangeRateAutoDetailForm;
import com.esynergy.erm.model.form.ExchangeRateAutoForm;
import com.esynergy.erm.model.form.ExchangeRateAutoSearchForm;
import com.esynergy.erm.model.form.ExchangeRateManualSearchForm;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.esynergy.erm.service.ExchangeRateAutoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "rawtypes", "serial" })
@Controller("exchangeRagteByAutoAction")
public class ExchangeRateByAutoAction extends ActionCommon implements
 ServletRequestAware, ModelDriven{
    
    private String parm;
	private Date dateOfRate;
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private List<ExchangeRateAutoHISTLog> logList = new ArrayList<ExchangeRateAutoHISTLog>();
	private ExchangeRateAutoSearchForm exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
    private ExchangeRateAuto exchangeRateAuto = new ExchangeRateAuto();
    private ExchangeRateAutoForm exchangeRateAutoForm = new ExchangeRateAutoForm(); 
	
	private String saveDirectory;
	private InputStream fileDownloadStream;
	private String fileDownloadName;
	private long contentLength;
    
	@Autowired private ExchangeRateAutoHISTLogService exchangeRateAutoHISTLogService;
	@Autowired private ExchangeRateAutoService exchangeRateAutoService;
	@Autowired private CurrencyService currencyService;
	@Autowired private CountryService countryService; 
	@Autowired private CodeValueService codeValueService;
	public String prepareMange() throws ParseException{
		if(UIUtil.isEmptyOrNull((String)getReq().getAttribute("updated"))){
			super.errors.clear();
		}
		dateOfRate =  new Date();
		exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
		exchangeRateAutoSearchForm.setDateStart(DATE_FORMAT.format(new Date()));
		exchangeRateAutoSearchForm.setDateEnd(DATE_FORMAT.format(new Date()));
		this.logList = exchangeRateAutoHISTLogService.listByDate(dateOfRate);
		return SUCCESS;
	}
	public String prepareAdjust() throws ParseException{
		if(UIUtil.isEmptyOrNull((String)getReq().getAttribute("updated"))){
			super.errors.clear();
		}
		super.addActionMsg("actionTitle", "menu.adjust.exchange.rate");
		dateOfRate =  new Date();
		exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
		exchangeRateAutoSearchForm.setDateStart(DATE_FORMAT.format(dateOfRate));
		exchangeRateAutoSearchForm.setDateEnd(DATE_FORMAT.format(dateOfRate));
		logList = exchangeRateAutoHISTLogService.listByDate(dateOfRate);
		return SUCCESS;
	}
	public String search(){
		super.addActionMsg("actionTitle", "menu.adjust.exchange.rate");
 		this.logList = exchangeRateAutoHISTLogService.search(exchangeRateAutoSearchForm.getDateStart(), exchangeRateAutoSearchForm.getDateEnd(), exchangeRateAutoSearchForm.getBankName(), exchangeRateAutoSearchForm.getLogStatust()); 		
 		return SUCCESS;
	}
	public String viewByAutoMange(){
		return view();
	}
	public String view(){
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "ViewExchangeRateAuto")){
			return ERROR;
		}
		super.addActionMsg("actionTitle", "header.view.exchange.auto.rate");
		HttpServletRequest request = getReq();
		HttpSession session = request.getSession();
		exchangeRateAuto = exchangeRateAutoService.getById(Long.parseLong(parm));
		exchangeRateAutoForm = new ExchangeRateAutoForm();
		pupulateObToForm(exchangeRateAuto,exchangeRateAutoForm);
		Map<String,IFileUpload> fileMap = new HashMap<String, IFileUpload>();
		if(exchangeRateAuto!=null){
			if(exchangeRateAuto.getFileUploadERAutoList()!=null 
					&& exchangeRateAuto.getFileUploadERAutoList().size()>0){
				for(IFileUpload f:exchangeRateAuto.getFileUploadERAutoList()){
					fileMap.put(String.valueOf(f.getId()), f);
				}
			}
		}
		if(session.getAttribute(SESSION_FILE_UPLOAD_BY_AUTO)!=null) 
		{session.removeAttribute(SESSION_FILE_UPLOAD_BY_AUTO);}
	    session.setAttribute(SESSION_FILE_UPLOAD_BY_AUTO, fileMap);
		return SUCCESS;
	}
	
	
	public String downloadFile()   {
	 	HttpServletRequest request = getReq();
		HttpSession  session =  request.getSession(false);
		Map<String,Object> fileMap =(Map<String,Object>) session.getAttribute(SESSION_FILE_UPLOAD_BY_AUTO);
		IFileUpload fileUploadER = (IFileUpload) fileMap.get(request.getParameter("parm"));

		try {
			File file = new File(fileUploadER.getUrl());
			this.contentLength = file.length();
			this.fileDownloadStream = new FileInputStream(file);
			fileDownloadName = file.getName(); 
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		 
		return SUCCESS;
	}
	
	public String prepareEdit(){
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		exchangeRateAuto = exchangeRateAutoService.getById(idLong);
		if(!ValidatorUtil.checkPermission(user, "EditExchangeRateAuto")){
			return ERROR;
		}
		
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.vedit.exchange.auto.rate");
		exchangeRateAutoForm = new ExchangeRateAutoForm();
		HttpServletRequest request = getReq();
		HttpSession session = request.getSession();
		
		pupulateObToForm(exchangeRateAuto,exchangeRateAutoForm);
		Map<String,IFileUpload> fileMap = new HashMap<String, IFileUpload>();
		if(exchangeRateAuto!=null){
			if(exchangeRateAuto.getFileUploadERAutoList()!=null 
					&& exchangeRateAuto.getFileUploadERAutoList().size()>0){
				for(IFileUpload f:exchangeRateAuto.getFileUploadERAutoList()){
					fileMap.put(String.valueOf(f.getId()), f);
				}
			}
		}
		if(session.getAttribute(SESSION_FILE_UPLOAD_BY_AUTO)!=null) 
		{session.removeAttribute(SESSION_FILE_UPLOAD_BY_AUTO);}
	    session.setAttribute(SESSION_FILE_UPLOAD_BY_AUTO, fileMap);
	    super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}
	public String save(){
		exchangeRateAuto = new ExchangeRateAuto();
		if(!validateForm()){
			return INPUT;
		}
		pupulateFormToOb(exchangeRateAutoForm,exchangeRateAuto);
		HttpSession session = getReq().getSession();
		IUser user = (IUser)session.getAttribute(SESSION_USER);
		exchangeRateAuto.setLastUpdateUser(user.getLogOnId());
		exchangeRateAuto.setLastUpdateDate(new Date());
		exchangeRateAutoService.save(exchangeRateAuto);
		getReq().setAttribute("updated","updated");
	 	super.addActionMsg("saveSuccess",getText("msg.action.save.success"));
	 	errors.put("isSuccess", true);
		return SUCCESS;
	}
	public boolean validateForm() {
		errors.put("isError", false);
		errors = new HashMap<String, Object>();
 
		for(int i=0;i<exchangeRateAutoForm.getExchangeRateAutoDetails().size();i++){
			ExchangeRateAutoDetailForm formDtl = exchangeRateAutoForm.getExchangeRateAutoDetails().get(i);
			if(UIUtil.isEmptyOrNull(formDtl.getBuyingRate())){
				addErrors("buyingRateRequireError"+i, "error.require");
			}else{
				if(ValidatorUtil.checkAmount(formDtl.getBuyingRate(),MIN_RATE, MAX_RATE, DECIMAL_RATE)){
					String[] errMsgParm = { MIN_RATE_STR, MAX_RATE_STR };
					addErrors("buyingRateError" + i, "error.rate.format",
							errMsgParm);
				}
			}
			if(UIUtil.isEmptyOrNull(formDtl.getSellingRate())){
				addErrors("sellingRateRequireError"+i, "error.require");
			}else{
				if(ValidatorUtil.checkAmount(formDtl.getSellingRate(),MIN_RATE, MAX_RATE, DECIMAL_RATE)){
					String[] errMsgParm = { MIN_RATE_STR, MAX_RATE_STR };
					addErrors("sellingRateError" + i, "error.rate.format",
							errMsgParm);
				}
			}
		}
		if (errors.isEmpty()) {
			return true;
		}
		super.addActionMsg("error",getText("error.action.message"));
		errors.put("isError", true);
		return false;
	}
	@Override
	public Object getModel() {
		return null;
	}
	public void pupulateFormToOb(ExchangeRateAutoForm form,ExchangeRateAuto ob){
		
//		for(ExchangeRateDetail item : ob.getExchangeRateDetails()) {
//			for(ExchangeRateAutoDetailForm formDtl:form.getExchangeRateAutoDetails()) {
//				if(formDtl.getId() == item.getId()) {
//					double fBuyingRate = Double.parseDouble(formDtl.getBuyingRate());
//					double fSellingRate = Double.parseDouble(formDtl.getSellingRate());
//					if(fBuyingRate != item.getBuyingRate() || fSellingRate != item.getSellingRate()) {
//						item.setBuyingRate(fBuyingRate);
//						item.setSellingRate(fSellingRate);
//					}
//					break;
//				}
//			}
//		}
		ob.setId(form.getId());
		for(ExchangeRateAutoDetailForm formDtl:form.getExchangeRateAutoDetails()){
			ExchangeRateDetail dtl = new ExchangeRateDetail();
			ob.addExchangeRateDetails(dtl);
			dtl.setId(formDtl.getId());
			dtl.setBuyingRate(Double.parseDouble(formDtl.getBuyingRate().replace(",", "")));
			dtl.setSellingRate(Double.parseDouble(formDtl.getSellingRate().replace(",", "")));
		}
	}
	private void pupulateObToForm(ExchangeRateAuto ob,ExchangeRateAutoForm form){
		if(ob!=null){
			form.setId(ob.getId());
			form.setRateDate(ob.getRateDate());
			form.setCreatedDate(ob.getCreatedDate());
			form.setBankName(ob.getBankOfRate().iterator().next().getBank().getBankName());
			form.setCountryOfBank(ob.getBankOfRate().iterator().next().getBank().getCountry().getCountryName());
			form.setFileUploadERAutoList(ob.getFileUploadERAutoList());
			if(ob.getExchangeRateDetails()!=null && ob.getExchangeRateDetails().size()>0){
				for(ExchangeRateDetail dtl : ob.getExchangeRateDetails()){
					ExchangeRateAutoDetailForm dtlForm = new ExchangeRateAutoDetailForm();
					dtlForm.setId(dtl.getId());
					dtlForm.setCurrencyCode(dtl.getPairCurrency().getCode());
					dtlForm.setValue(VALUE_RATE_FORMAT.format(dtl.getValue()));
					dtlForm.setBuyingRate(EXCHANGE_RATE_FORMAT.format(dtl.getBuyingRate()));
					dtlForm.setSellingRate(EXCHANGE_RATE_FORMAT.format(dtl.getSellingRate()));
					form.addExchangeRateAutoDetails(dtlForm);
				}
			}
			
			Collections.sort(form.getExchangeRateAutoDetails(),new Comparator<ExchangeRateAutoDetailForm>() {
				public int compare(ExchangeRateAutoDetailForm o1, ExchangeRateAutoDetailForm o2) {
					return o1.getId() > o2.getId() ? -1 : 1;
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.addActionMsg("actionTitle", "header.exchange.rate.auto.process.mange");
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
//		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO);
//		request.setAttribute(SUB_MENU1_ATTR, MENU_EXCHANGE_RATE_AUTO_VIEW_ADJUST);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO_VIEW_ADJUST);

		HttpSession session = request.getSession(false) == null ? request
				.getSession(true) : request.getSession(false);
		 
		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
			countryList = countryService.listAll();
			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
		} else {
			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
		}
	}
	 
	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 
	public Date getDateOfRate() {
		return dateOfRate;
	}

	public void setDateOfRate(Date dateOfRate) {
		this.dateOfRate = dateOfRate;
	}
	 
	public List<Currency> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}
	public Map<Long, Currency> getCurrencyMap() {
		return currencyMap;
	}
	public void setCurrencyMap(Map<Long, Currency> currencyMap) {
		this.currencyMap = currencyMap;
	}
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	public List<ExchangeRateAutoHISTLog> getLogList() {
		return logList;
	}
	public void setLogList(List<ExchangeRateAutoHISTLog> logList) {
		this.logList = logList;
	}
	public ExchangeRateAutoSearchForm getExchangeRateAutoSearchForm() {
		return exchangeRateAutoSearchForm;
	}
	public void setExchangeRateAutoSearchForm(
			ExchangeRateAutoSearchForm exchangeRateAutoSearchForm) {
		this.exchangeRateAutoSearchForm = exchangeRateAutoSearchForm;
	}
	public ExchangeRateAuto getExchangeRateAuto() {
		return exchangeRateAuto;
	}
	public void setExchangeRateAuto(ExchangeRateAuto exchangeRateAuto) {
		this.exchangeRateAuto = exchangeRateAuto;
	}
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
	public String getSaveDirectory() {
		return saveDirectory;
	}
	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}
	public InputStream getFileDownloadStream() {
		return fileDownloadStream;
	}
	public void setFileDownloadStream(InputStream fileDownloadStream) {
		this.fileDownloadStream = fileDownloadStream;
	}
	public String getFileDownloadName() {
		return fileDownloadName;
	}
	public void setFileDownloadName(String fileDownloadName) {
		this.fileDownloadName = fileDownloadName;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public ExchangeRateAutoForm getExchangeRateAutoForm() {
		return exchangeRateAutoForm;
	}
	public void setExchangeRateAutoForm(ExchangeRateAutoForm exchangeRateAutoForm) {
		this.exchangeRateAutoForm = exchangeRateAutoForm;
	}

 


}
