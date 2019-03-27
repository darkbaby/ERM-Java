package com.esynergy.erm.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.form.ExchangeRateSearchForm;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.CurrencyPairs;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.ExchangeRateVeiwView;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.esynergy.erm.service.ExchangeRateService;

@SuppressWarnings("serial")
public class DashboardAction extends ActionCommon {
    
	private List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
	private List<ExchangeRateVeiwView> exchangeSuccessList = new ArrayList<ExchangeRateVeiwView>();
	private List<ExchangeRateAutoHISTLog> exchangeRateFailList = new ArrayList<ExchangeRateAutoHISTLog>();
	private ExchangeRateSearchForm exchangeRateSearchForm = new ExchangeRateSearchForm();
	private String dateOfRate;
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private boolean dateNextIsDisplay = false;
	private List<CurrencyPairs> remainList =  new ArrayList<CurrencyPairs>();
 
    private String menuName;
	
	@Autowired
	private MandatoryAsset mandatoryAsset;
    
	@Autowired private ExchangeRateService exchangeRateService;
	@Autowired private ExchangeRateAutoHISTLogService exchangeRateAutoHISTLogService;
//	@Autowired private CurrencyService currencyService;
//	@Autowired private CountryService countryService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
//		HttpSession session = request.getSession(false) == null ? request
//				.getSession(true) : request.getSession(false);
//		request.setAttribute(MAIN_MENU_ATTR, MENU_DASHBOARD);
//		if (session.getAttribute(SESSION_CURRENCY_LIST) == null) {
//			currencyList = currencyService.listAll();
//			session.setAttribute(SESSION_CURRENCY_LIST, currencyList);
//		} else {
//			currencyList = (List<Currency>) session
//					.getAttribute(SESSION_CURRENCY_LIST);
//		}
//		if (session.getAttribute(SESSION_CURRENCY_MAP) == null) {
//			currencyMap = currencyService.listAllMap();
//			session.setAttribute(SESSION_CURRENCY_MAP, currencyMap);
//		} else {
//			currencyMap = (Map<Long, Currency>) session
//					.getAttribute(SESSION_CURRENCY_MAP);
//		}
//		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
//			countryList = countryService.listAll();
//			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
//		} else {
//			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
//		}
		
//		mandatoryAsset.initialAsset();
		currencyList = mandatoryAsset.getCurrencyList();
		currencyMap = mandatoryAsset.getCurrencyMap();
		countryList = mandatoryAsset.getCountryList();
		
	}
	
	public String prepare() throws ParseException{
		
		menuName = getText("header.dashboard");
		
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		Date now =  UIUtil.setZoroTime(new Date());
		dateOfRate = IPageContains.DATE_FORMAT.format(now);
		exchangeRateSearchForm.setCurrentDate(dateOfRate);
		exchangeSuccessList = exchangeRateService.getByDate(now);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(now);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(now);
		return SUCCESS;
	}
	public String previousDate() throws ParseException{
		
		menuName = getText("header.dashboard");
		
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		
		Date current = IPageContains.DATE_FORMAT.parse(dateOfRate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.DATE, -1);
		current = cal.getTime();
		dateOfRate = IPageContains.DATE_FORMAT.format(current);
		exchangeRateSearchForm.setCurrentDate(dateOfRate);
		exchangeSuccessList = exchangeRateService.getByDate(current);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(current);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(current);
		String d1 = DATE_FORMAT.format(UIUtil.setZoroTime(current));
		String d2 = DATE_FORMAT.format(UIUtil.setZoroTime(new Date()));
		this.dateNextIsDisplay = !d1.equals(d2);
		return SUCCESS;
	}
	public String nextDate() throws ParseException{
		
		menuName = getText("header.dashboard");
		
		exchangeRateSearchForm = new ExchangeRateSearchForm();
		
		Date current = IPageContains.DATE_FORMAT.parse(dateOfRate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.DATE, 1);
		current = cal.getTime();
		dateOfRate = IPageContains.DATE_FORMAT.format(current);
		exchangeRateSearchForm.setCurrentDate(dateOfRate);
		exchangeSuccessList = exchangeRateService.getByDate(current);
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(current);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(current);
		String d1 = DATE_FORMAT.format(UIUtil.setZoroTime(current));
		String d2 = DATE_FORMAT.format(UIUtil.setZoroTime(new Date()));
		this.dateNextIsDisplay = !d1.equals(d2);
		return SUCCESS;
	}
	public String search() throws ParseException{
		
		menuName = getText("header.dashboard");
		
		Date current = IPageContains.DATE_FORMAT.parse(dateOfRate);
		dateOfRate = IPageContains.DATE_FORMAT.format(current);
		
		exchangeSuccessList= new ArrayList<ExchangeRateVeiwView>();
		exchangeSuccessList = exchangeRateService.search(exchangeRateSearchForm.getPairCurrencyId()
														,exchangeRateSearchForm.getBaseCurrencyId()
														,exchangeRateSearchForm.getCountryOfBank()
														,exchangeRateSearchForm.getOrigin()
														,IPageContains.DATE_FORMAT.parse(exchangeRateSearchForm.getCurrentDate()));
		
		exchangeRateFailList = new ArrayList<ExchangeRateAutoHISTLog>();
		exchangeRateFailList = exchangeRateAutoHISTLogService.getFailByDate(current);
		remainList = new ArrayList<CurrencyPairs>();
		remainList = exchangeRateService.getRemainByDate(current);
		
		String d1 = DATE_FORMAT.format(UIUtil.setZoroTime(current));
		String d2 = DATE_FORMAT.format(UIUtil.setZoroTime(new Date()));
		this.dateNextIsDisplay = !d1.equals(d2);
		return SUCCESS;
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public String getDateOfRate() {
		return dateOfRate;
	}

	public void setDateOfRate(String dateOfRate) {
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
