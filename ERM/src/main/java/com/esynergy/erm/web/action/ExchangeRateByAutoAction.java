package com.esynergy.erm.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.form.ExchangeRateAutoDetailForm;
import com.esynergy.erm.model.form.ExchangeRateAutoForm;
import com.esynergy.erm.model.form.ExchangeRateAutoSearchForm;
import com.esynergy.erm.model.form.FileUploadForm;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.FileUploadERAuto;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateAutoService;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({ "rawtypes", "serial" })
public class ExchangeRateByAutoAction extends ActionCommon {
    
	private List<Currency> currencyList = new ArrayList<Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private List<ExchangeRateAutoHISTLog> logList = new ArrayList<ExchangeRateAutoHISTLog>();
	private List<IExchangeRate> rateList = new ArrayList<IExchangeRate>();
	private ExchangeRateAutoSearchForm exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
//    private ExchangeRateAuto exchangeRateAuto = new ExchangeRateAuto();
    private ExchangeRateAutoForm exchangeRateAutoForm = new ExchangeRateAutoForm(); 
	
    private String menuName;
    private String backAction;
	private InputStream fileDownloadStream;
	private String fileDownloadName;
	private long contentLength;
    
	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@Autowired private ExchangeRateAutoService exchangeRateAutoService;
	@Autowired private ExchangeRateManualService exchangeRateManualService;
	@Autowired private CurrencyService currencyService;
	@Autowired private CountryService countryService; 
		
	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO_VIEW_ADJUST);
		
		currencyList = mandatoryAsset.getCurrencyList();
		countryList = mandatoryAsset.getCountryList();
		
		backAction = "prepareAutoRateAdjust";
		if(getReq().getParameter("parmBackAction") != null
				&& getReq().getParameter("parmBackAction").toString().equalsIgnoreCase("prepareManageExchangeRateAuto")) {
			backAction = "prepareManageExchangeRateAuto";
		}
	}
	 
	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 
	
	public String prepareAdjust() throws ParseException{
		
		menuName = getText("menu.adjust.exchange.rate");
		
		if(getReq().getParameter("updated") != null && getReq().getParameter("updated").equalsIgnoreCase("true") ){
			super.addErrors("haveResult", true);
		 	super.addActionMsg("resultMessage",getText("msg.action.save.success"));
		}
		
		Date dateOfRate =  new Date();
		exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
		exchangeRateAutoSearchForm.setDateStart(DATE_FORMAT.format(dateOfRate));
		exchangeRateAutoSearchForm.setDateEnd(DATE_FORMAT.format(dateOfRate));
		exchangeRateAutoSearchForm.setBaseCurrencyID("0");
		exchangeRateAutoSearchForm.setPairCurrencyID("0");
		exchangeRateAutoSearchForm.setBankName("");
		List<IExchangeRate> temp1 = exchangeRateAutoService.listByDate(dateOfRate);
		List<IExchangeRate> temp2 = exchangeRateManualService.listExchangeRateManualByRateDate(dateOfRate);
		
		rateList = new ArrayList<IExchangeRate>();
		rateList.addAll(temp1);
		rateList.addAll(temp2);
				
		return SUCCESS;
	}
	public String search(){
		menuName = getText("menu.adjust.exchange.rate");
		
		long baseCID = Long.parseLong(exchangeRateAutoSearchForm.getBaseCurrencyID());
		long pairCID = Long.parseLong(exchangeRateAutoSearchForm.getPairCurrencyID());
		
		List<IExchangeRate> temp1 = exchangeRateAutoService.search(exchangeRateAutoSearchForm.getDateStart(),
				exchangeRateAutoSearchForm.getDateEnd(), exchangeRateAutoSearchForm.getBankName(), baseCID, pairCID);
		
		List<IExchangeRate> temp2 = new ArrayList<IExchangeRate>();
		if(UIUtil.isEmptyOrNull(exchangeRateAutoSearchForm.getBankName())) {
			temp2 = exchangeRateManualService.search(exchangeRateAutoSearchForm.getDateStart(),
					exchangeRateAutoSearchForm.getDateEnd(), baseCID, pairCID);
		}
		
		rateList = new ArrayList<IExchangeRate>();
		rateList.addAll(temp1);
		rateList.addAll(temp2);
		
		if(baseCID != 0 || pairCID != 0) {
			for(IExchangeRate item:rateList) {
				Iterator tempIT = item.getExchangeRateDetails().iterator();
				while(tempIT.hasNext()) {
					ExchangeRateDetail dt = (ExchangeRateDetail)tempIT.next();
					if(baseCID != 0 && dt.getFirstCurrency().getId() != baseCID) {
						tempIT.remove();
						continue;
					}
					if(pairCID != 0 && dt.getPairCurrency().getId() != pairCID) {
						tempIT.remove();
						continue;
					}
				}
			}
		}
		
 		return SUCCESS;
	}

	public String view(){
		
		menuName = getText("header.view.exchange.auto.rate");
		ExchangeRateAuto exchangeRateAuto = exchangeRateAutoService.getById(Long.parseLong(getReq().getParameter("parmID")));
		exchangeRateAutoForm = new ExchangeRateAutoForm();
		pupulateObToForm(exchangeRateAuto,exchangeRateAutoForm);
		return SUCCESS;
	}
	
	public String downloadFile()   {

	 	String idObject = getReq().getParameter("parmID");
	 	String urlDownload = getReq().getParameter("parmURL");
	 	
		try {
		 	urlDownload = java.net.URLDecoder.decode(urlDownload, "UTF-8");
			File file = new File(urlDownload);
			this.contentLength = file.length();
			this.fileDownloadStream = new FileInputStream(file);
			fileDownloadName = file.getName(); 
		} catch (Exception e) {
			e.printStackTrace();
			return "file_not_found";
		}
		 
		return SUCCESS;
	}
	
	public String prepareEdit(){
		menuName = getText("header.vedit.exchange.auto.rate");
		
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(getReq().getParameter("parmID")));
		ExchangeRateAuto exchangeRateAuto = exchangeRateAutoService.getById(idLong);

		exchangeRateAutoForm = new ExchangeRateAutoForm();
		
		pupulateObToForm(exchangeRateAuto,exchangeRateAutoForm);
		return SUCCESS;
	}
	public String save(){
		if(!validateForm()){
			return INPUT;
		}
		ExchangeRateAuto exchangeRateAuto = new ExchangeRateAuto();
		pupulateFormToOb(exchangeRateAutoForm,exchangeRateAuto);
		exchangeRateAuto.setLastUpdateUser(iUser.getLogOnId());
		exchangeRateAuto.setLastUpdateDate(new Date());
		exchangeRateAutoService.save(exchangeRateAuto);
		return SUCCESS;
	}
	public boolean validateForm() {
		if(UIUtil.isEmptyOrNull(exchangeRateAutoForm.getRefDateStr())) {
			addErrors("refDateRequireError", "error.require");
		}
		
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
		super.addErrors("isError", true);
		return false;
	}

	public void pupulateFormToOb(ExchangeRateAutoForm form,ExchangeRateAuto ob){
		try {
			ob.setId(form.getId());
			Date refDate = ICommonContains.DATE_FORMAT_ORACLE.parse(form.getRefDateStr()+" 00:00:00");
			ob.setRefDate(refDate);
			for(ExchangeRateAutoDetailForm formDtl:form.getExchangeRateAutoDetails()){
				ExchangeRateDetail dtl = new ExchangeRateDetail();
				ob.addExchangeRateDetails(dtl);
				dtl.setId(formDtl.getId());
				dtl.setBuyingRate(Double.parseDouble(formDtl.getBuyingRate().replace(",", "")));
				dtl.setSellingRate(Double.parseDouble(formDtl.getSellingRate().replace(",", "")));
				dtl.setLastUpdateUser(iUser.getLogOnId());
				dtl.setLastUpdateDate(new Date());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void pupulateObToForm(ExchangeRateAuto ob,ExchangeRateAutoForm form){
		if(ob!=null){
			form.setId(ob.getId());
			form.setRateDate(ob.getRateDate());
			form.setRateDateStr(DATE_FORMAT.format(ob.getRateDate()));
			form.setRefDate(ob.getRefDate());
			form.setRefDateStr(DATE_FORMAT.format(ob.getRefDate()));
			form.setCreatedDate(ob.getCreatedDate());
			form.setCreateDateStr(DATE_FORMAT.format(ob.getCreatedDate()));
			form.setBankName(ob.getBankOfRate().iterator().next().getBank().getBankName());
			form.setCountryOfBank(ob.getBankOfRate().iterator().next().getBank().getCountry().getCountryName());
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
			
			if(ob.getFileUploadERAutoList()!=null && ob.getFileUploadERAutoList().size()>0){
				for(FileUploadERAuto dtl : ob.getFileUploadERAutoList()){
					FileUploadForm dtlForm = new FileUploadForm();
					dtlForm.setId(dtl.getId());
					dtlForm.setFile(dtl.getFile());
					dtlForm.setName(dtl.getName());
					dtlForm.setUrl(dtl.getUrl());
					form.addFileUploadList(dtlForm);
				}
			}
			
			Collections.sort(form.getExchangeRateAutoDetails(),new Comparator<ExchangeRateAutoDetailForm>() {
				public int compare(ExchangeRateAutoDetailForm o1, ExchangeRateAutoDetailForm o2) {
					return o1.getId() > o2.getId() ? -1 : 1;
				}
			});
		}
	}
	 
	public List<Currency> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
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
	public List<IExchangeRate> getRateList() {
		return rateList;
	}
	public void setRateList(List<IExchangeRate> rateList) {
		this.rateList = rateList;
	}
	public ExchangeRateAutoSearchForm getExchangeRateAutoSearchForm() {
		return exchangeRateAutoSearchForm;
	}
	public void setExchangeRateAutoSearchForm(
			ExchangeRateAutoSearchForm exchangeRateAutoSearchForm) {
		this.exchangeRateAutoSearchForm = exchangeRateAutoSearchForm;
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
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getBackAction() {
		return backAction;
	}

	public void setBackAction(String backAction) {
		this.backAction = backAction;
	}
	
}