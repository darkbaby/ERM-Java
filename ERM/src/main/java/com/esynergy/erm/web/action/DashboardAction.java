package com.esynergy.erm.web.action;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.form.ExchangeRateSearchForm;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.CurrencyPairs;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.ExchangeRateDetailVeiwView;
import com.esynergy.erm.model.ob.ExchangeRateVeiwView;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.esynergy.erm.service.ExchangeRateService;
import com.esynergy.erm.service.ExtractionDetailService;
import com.esynergy.erm.service.ExtractionService;
import com.esynergy.erm.service.ExtractionTimeService;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "rawtypes", "serial" })
@Controller("dasboardAction")
public class DashboardAction extends ActionCommon implements
 ServletRequestAware, ModelDriven{
    
	private List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
	private List<ExchangeRateVeiwView> exchangeSuccessList = new ArrayList<ExchangeRateVeiwView>();
	private List<ExchangeRateAutoHISTLog> exchangeRateFailList = new ArrayList<ExchangeRateAutoHISTLog>();
	private ExchangeRateSearchForm exchangeRateSearchForm = new ExchangeRateSearchForm();
	private Date dateOfRate;
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private boolean dateNextIsDisplay = false;
	private List<CurrencyPairs> remainList =  new ArrayList<CurrencyPairs>();
 
    
	@Autowired private ExchangeRateService exchangeRateService;
	@Autowired private ExtractionService extractionService;
	@Autowired private ExchangeRateAutoHISTLogService exchangeRateAutoHISTLogService;
	@Autowired private CurrencyService currencyService;
	@Autowired private CountryService countryService;
	@Autowired private ExtractionDetailService extractionDetailService;
	@Autowired private ExtractionTimeService extractionTimeService;
	@Autowired private CodeValueService codeValueService;
	
	public String prepare() throws ParseException{
		this.addActionMsg("actionTitle", "header.dashboard");
		//this.actionMsg = new HashMap<String, Object>();2017-08-15
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		dateOfRate =  UIUtil.setZoroTime(new Date());
		exchangeSuccessList = exchangeRateService.getByDate(dateOfRate);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(dateOfRate);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(dateOfRate);
		
	 	 
		return SUCCESS;
	}
	public String previousDate(){
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dateOfRate);
		cal.add(Calendar.DATE, -1);
		this.setDateOfRate(cal.getTime()); 
		exchangeSuccessList = exchangeRateService.getByDate(dateOfRate);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(dateOfRate);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(dateOfRate);
		String d1 = DATE_FORMAT.format(UIUtil.setZoroTime(dateOfRate));
		String d2 = DATE_FORMAT.format(UIUtil.setZoroTime(new Date()));
		this.dateNextIsDisplay = !d1.equals(d2);
		return SUCCESS;
	}
	public String nextDate(){
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dateOfRate);
		cal.add(Calendar.DATE, 1);
		this.setDateOfRate(cal.getTime()); 
		exchangeSuccessList = exchangeRateService.getByDate(dateOfRate);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(dateOfRate);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(dateOfRate);
		String d1 = DATE_FORMAT.format(UIUtil.setZoroTime(dateOfRate));
		String d2 = DATE_FORMAT.format(UIUtil.setZoroTime(new Date()));
		this.dateNextIsDisplay = !d1.equals(d2);
		return SUCCESS;
	}
	public String search(){
		exchangeSuccessList= new ArrayList<ExchangeRateVeiwView>();
		exchangeSuccessList = exchangeRateService.search(exchangeRateSearchForm.getPairCurrencyId()
														,exchangeRateSearchForm.getBaseCurrencyId()
														,exchangeRateSearchForm.getCountryOfBank()
														,exchangeRateSearchForm.getOrigin());
		return SUCCESS;
	}
	@Override
	public Object getModel() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		this.addActionMsg("actionTitle", "header.dashboard");
		request.setAttribute(MAIN_MENU_ATTR, MENU_DASHBOARD);
		HttpSession session = request.getSession(false) == null ? request
				.getSession(true) : request.getSession(false);
		if (session.getAttribute(SESSION_CURRENCY_LIST) == null) {
			currencyList = currencyService.listAll();
			session.setAttribute(SESSION_CURRENCY_LIST, currencyList);
		} else {
			currencyList = (List<Currency>) session
					.getAttribute(SESSION_CURRENCY_LIST);
		}
		if (session.getAttribute(SESSION_CURRENCY_MAP) == null) {
			currencyMap = currencyService.listAllMap();
			session.setAttribute(SESSION_CURRENCY_MAP, currencyMap);
		} else {
			currencyMap = (Map<Long, Currency>) session
					.getAttribute(SESSION_CURRENCY_MAP);
		}
		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
			countryList = countryService.listAll();
			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
		} else {
			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
		}
		
	}
	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public Date getDateOfRate() {
		return dateOfRate;
	}

	public void setDateOfRate(Date dateOfRate) {
		this.dateOfRate = dateOfRate;
	}
 
	public List<ExchangeRateAutoHISTLog> getExchangeRateFailList() {
		return exchangeRateFailList;
	}
	public void setExchangeRateFailList(List<ExchangeRateAutoHISTLog> exchangeRateFailList) {
		this.exchangeRateFailList = exchangeRateFailList;
	}
	public ExchangeRateSearchForm getExchangeRateSearchForm() {
		return exchangeRateSearchForm;
	}
	public void setExchangeRateSearchForm(
			ExchangeRateSearchForm exchangeRateSearchForm) {
		this.exchangeRateSearchForm = exchangeRateSearchForm;
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
	public List<ExchangeRateVeiwView> getExchangeSuccessList() {
		return exchangeSuccessList;
	}
	public void setExchangeSuccessList(
			List<ExchangeRateVeiwView> exchangeSuccessList) {
		this.exchangeSuccessList = exchangeSuccessList;
	}
	public boolean isDateNextIsDisplay() {
		return dateNextIsDisplay;
	}
	public void setDateNextIsDisplay(boolean dateNextIsDisplay) {
		this.dateNextIsDisplay = dateNextIsDisplay;
	}
	public List<CurrencyPairs> getRemainList() {
		return remainList;
	}
	public void setRemainList(List<CurrencyPairs> remainList) {
		this.remainList = remainList;
	}

}
