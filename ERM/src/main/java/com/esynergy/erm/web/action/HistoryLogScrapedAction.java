package com.esynergy.erm.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;

import com.esynergy.erm.model.form.ExchangeRateAutoSearchForm;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({ "serial" })
public class HistoryLogScrapedAction extends ActionCommon {
    
	private List<Country> countryList = new ArrayList<Country>();
	private List<ExchangeRateAutoHISTLog> logList = new ArrayList<ExchangeRateAutoHISTLog>();
	private ExchangeRateAutoSearchForm exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
//    private ExchangeRateAutoForm exchangeRateAutoForm = new ExchangeRateAutoForm(); 

	private String menuName;	
	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@Autowired private ExchangeRateAutoHISTLogService exchangeRateAutoHISTLogService;
//	@Autowired private ExchangeRateAutoService exchangeRateAutoService;

	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO);
		request.setAttribute(SUB_MENU1_ATTR, MENU_EXCHANGE_RATE_AUTO_HISTLOG_SCRAPED);
				 
//		HttpSession session = request.getSession(false) == null ? request
//				.getSession(true) : request.getSession(false);
//		 
//		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
//			countryList = countryService.listAll();
//			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
//		} else {
//			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
//		}
		
//		mandatoryAsset.initialAsset();
		countryList = mandatoryAsset.getCountryList();
	}
	 
	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 
	
//	public String prepareView(){
//		menuName = getText("header.view.exchange.auto.rate");
//		ExchangeRateAuto exchangeRateAuto = exchangeRateAutoService.getById(Long.parseLong(getReq().getParameter("parmID")));
//		exchangeRateAutoForm = new ExchangeRateAutoForm();
//		pupulateObToForm(exchangeRateAuto,exchangeRateAutoForm);
//		return SUCCESS;
//	}
	
	public String prepareManage() throws ParseException{
		
		menuName = getText("menu.main.auto.histlog.scraped");
		
		Date dateOfRate =  new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date previousDate = cal.getTime();
		
		exchangeRateAutoSearchForm = new ExchangeRateAutoSearchForm();
		exchangeRateAutoSearchForm.setDateStart(DATE_FORMAT.format(previousDate));
		exchangeRateAutoSearchForm.setDateEnd(DATE_FORMAT.format(previousDate));
		this.logList = exchangeRateAutoHISTLogService.listByDate(dateOfRate);
		return SUCCESS;
	}
 
	public String search(){
		menuName = getText("menu.main.auto.histlog.scraped");
 		this.logList = exchangeRateAutoHISTLogService.search(exchangeRateAutoSearchForm.getDateStart(), exchangeRateAutoSearchForm.getDateEnd(), exchangeRateAutoSearchForm.getBankName(), exchangeRateAutoSearchForm.getLogStatust()); 		
 		return SUCCESS;
	}

//	private void pupulateObToForm(ExchangeRateAuto ob,ExchangeRateAutoForm form){
//		if(ob!=null){
//			form.setId(ob.getId());
//			form.setRateDate(ob.getRateDate());
//			form.setRateDateStr(DATE_FORMAT.format(ob.getRateDate()));
//			form.setRefDate(ob.getRefDate());
//			form.setRefDateStr(DATE_FORMAT.format(ob.getRefDate()));
//			form.setCreatedDate(ob.getCreatedDate());
//			form.setCreateDateStr(DATE_FORMAT.format(ob.getCreatedDate()));
//			form.setBankName(ob.getBankOfRate().iterator().next().getBank().getBankName());
//			form.setCountryOfBank(ob.getBankOfRate().iterator().next().getBank().getCountry().getCountryName());
//			if(ob.getExchangeRateDetails()!=null && ob.getExchangeRateDetails().size()>0){
//				for(ExchangeRateDetail dtl : ob.getExchangeRateDetails()){
//					ExchangeRateAutoDetailForm dtlForm = new ExchangeRateAutoDetailForm();
//					dtlForm.setId(dtl.getId());
//					dtlForm.setCurrencyCode(dtl.getPairCurrency().getCode());
//					dtlForm.setValue(VALUE_RATE_FORMAT.format(dtl.getValue()));
//					dtlForm.setBuyingRate(EXCHANGE_RATE_FORMAT.format(dtl.getBuyingRate()));
//					dtlForm.setSellingRate(EXCHANGE_RATE_FORMAT.format(dtl.getSellingRate()));
//					form.addExchangeRateAutoDetails(dtlForm);
//				}
//			}
//			
//			if(ob.getFileUploadERAutoList()!=null && ob.getFileUploadERAutoList().size()>0){
//				for(FileUploadERAuto dtl : ob.getFileUploadERAutoList()){
//					FileUploadForm dtlForm = new FileUploadForm();
//					dtlForm.setId(dtl.getId());
//					dtlForm.setFile(dtl.getFile());
//					dtlForm.setName(dtl.getName());
//					dtlForm.setUrl(dtl.getUrl());
//					form.addFileUploadList(dtlForm);
//				}
//			}
//			
//			Collections.sort(form.getExchangeRateAutoDetails(),new Comparator<ExchangeRateAutoDetailForm>() {
//				public int compare(ExchangeRateAutoDetailForm o1, ExchangeRateAutoDetailForm o2) {
//					return o1.getId() > o2.getId() ? -1 : 1;
//				}
//			});
//		}
//	}
	
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
