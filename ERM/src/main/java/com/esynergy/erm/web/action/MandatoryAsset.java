package com.esynergy.erm.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.AuthorizeFunction;
import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.AuthorizeFunctionService;
import com.esynergy.erm.service.AuthorizeGroupService;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.UserService;

@Component
public class MandatoryAsset {
		
	@PostConstruct
	public void initialAsset() {
//		pythonDirectory = codeValueService.getPythonPath().getValue();
//		manualDirectory = codeValueService.getManualPath().getValue();

		currencyList = currencyService.listAll();
		currencyMap = currencyService.listAllMap();
		countryList = countryService.listAll();
		countryMap = countryService.listAllMap();
		extractionTimeList = codeValueService.listAllExtractionTimeField();
		extractionTypeList = codeValueService.listAllExtractionType();
		extractionDateList = codeValueService.listAllExtractionDate();
		extractionPageTypeList = codeValueService.listAllPageType();
		extractionCurrencyTypeList = codeValueService.listAllCurrencyType();
		extractionStatusList = codeValueService.listAllStatus();
		extractionFormatDateList = codeValueService.listAllFormatDate();
		extractionValueList = codeValueService.listAllValue();
		generateRateTimeList = codeValueService.listAllGenerateRateTimeField();
		for(AuthorizeFunction item : authorizeFunctionService.listAll()) {
			authorizeFunctionMap.put(item.getFunctionName().toLowerCase(), item);
		}
		authorizeGroupList = authorizeGroupService.listAll();
		pythonDirectory = codeValueService.getPythonPath().getValue();
		manualDirectory = codeValueService.getManualPath().getValue();
		userList = userService.list();
		userMap = userService.listMap();
	}
	
	@Autowired
	private CodeValueService codeValueService;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired 
	private AuthorizeFunctionService authorizeFunctionService;
	
	@Autowired 
	private AuthorizeGroupService authorizeGroupService;
	
	@Autowired
	private UserService userService;
	
	private List<IUser> userList = new ArrayList<IUser>(); 
	private Map<Long,IUser> userMap = new HashMap<Long,IUser>(); 
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private Map<Long, Country> countryMap = new HashMap<Long, Country>();
	
	private List<CodeValue> extractionTimeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionDateList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionPageTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionCurrencyTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionStatusList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionFormatDateList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionValueList = new ArrayList<CodeValue>();
	
	private List<CodeValue> generateRateTimeList = new ArrayList<CodeValue>();
	
	private Map<String, AuthorizeFunction> authorizeFunctionMap = new HashMap<String, AuthorizeFunction>();
	private List<AuthorizeGroup> authorizeGroupList = new ArrayList<AuthorizeGroup>();
	
	private String pythonDirectory = "";
	private String manualDirectory = "";

	public Map<String, AuthorizeFunction> getAuthorizeFunctionMap() {
		return authorizeFunctionMap;
	}

	public void setAuthorizeFunctionMap(Map<String, AuthorizeFunction> authorizeFunctionMap) {
		this.authorizeFunctionMap = authorizeFunctionMap;
	}

	public List<AuthorizeGroup> getAuthorizeGroupList() {
		return authorizeGroupList;
	}

	public void setAuthorizeGroupList(List<AuthorizeGroup> authorizeGroupList) {
		this.authorizeGroupList = authorizeGroupList;
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

	public Map<Long, Country> getCountryMap() {
		return countryMap;
	}

	public void setCountryMap(Map<Long, Country> countryMap) {
		this.countryMap = countryMap;
	}

	public List<CodeValue> getExtractionTimeList() {
		return extractionTimeList;
	}

	public void setExtractionTimeList(List<CodeValue> extractionTimeList) {
		this.extractionTimeList = extractionTimeList;
	}

	public List<CodeValue> getExtractionTypeList() {
		return extractionTypeList;
	}

	public void setExtractionTypeList(List<CodeValue> extractionTypeList) {
		this.extractionTypeList = extractionTypeList;
	}

	public List<CodeValue> getExtractionDateList() {
		return extractionDateList;
	}

	public void setExtractionDateList(List<CodeValue> extractionDateList) {
		this.extractionDateList = extractionDateList;
	}

	public List<CodeValue> getExtractionPageTypeList() {
		return extractionPageTypeList;
	}

	public void setExtractionPageTypeList(List<CodeValue> extractionPageTypeList) {
		this.extractionPageTypeList = extractionPageTypeList;
	}

	public List<CodeValue> getExtractionCurrencyTypeList() {
		return extractionCurrencyTypeList;
	}

	public void setExtractionCurrencyTypeList(List<CodeValue> extractionCurrencyTypeList) {
		this.extractionCurrencyTypeList = extractionCurrencyTypeList;
	}

	public List<CodeValue> getExtractionStatusList() {
		return extractionStatusList;
	}

	public void setExtractionStatusList(List<CodeValue> extractionStatusList) {
		this.extractionStatusList = extractionStatusList;
	}

	public List<CodeValue> getExtractionFormatDateList() {
		return extractionFormatDateList;
	}

	public void setExtractionFormatDateList(List<CodeValue> extractionFormatDateList) {
		this.extractionFormatDateList = extractionFormatDateList;
	}

	public List<CodeValue> getExtractionValueList() {
		return extractionValueList;
	}

	public void setExtractionValueList(List<CodeValue> extractionValueList) {
		this.extractionValueList = extractionValueList;
	}

	public String getPythonDirectory() {
		return pythonDirectory;
	}

	public void setPythonDirectory(String pythonDirectory) {
		this.pythonDirectory = pythonDirectory;
	}

	public String getManualDirectory() {
		return manualDirectory;
	}

	public void setManualDirectory(String manualDirectory) {
		this.manualDirectory = manualDirectory;
	}

	public List<CodeValue> getGenerateRateTimeList() {
		return generateRateTimeList;
	}

	public void setGenerateRateTimeList(List<CodeValue> generateRateTimeList) {
		this.generateRateTimeList = generateRateTimeList;
	}

	public List<IUser> getUserList() {
		return userList;
	}

	public void setUserList(List<IUser> userList) {
		this.userList = userList;
	}

	public Map<Long, IUser> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<Long, IUser> userMap) {
		this.userMap = userMap;
	}
	
}