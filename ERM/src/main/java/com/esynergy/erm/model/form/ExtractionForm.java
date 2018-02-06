package com.esynergy.erm.model.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExtractionTime;

public class ExtractionForm {

	private long id;
	private long bankID;
	private String bankName;
	private String bankShortName;
	private long bankCountry;
	private long baseCurrency;
	private String url;
	private String extractionDate;
	private List<ExtractionTimeForm> extractionTimeFormList;
	private String formatDate;
	private String cssDate;
	private long pageType;
	private long pairCurrencyType;
	private long extractionType;
	private String status;
	private List<ExtractionDetailForm> extractionDetailFormList;
	private List<Long> selectedCurrencyAPI;
	
	private String bankCountryName;
	private String extractionTypeName;
	private String statusName;
	private String pairCurrencyTypeName;
	private String pageTypeName;
	private String baseCurrencyName;

	public ExtractionForm(){
		this.extractionTimeFormList = new ArrayList<ExtractionTimeForm>();
		this.extractionDetailFormList = new ArrayList<ExtractionDetailForm>();
		this.selectedCurrencyAPI = new ArrayList<Long>();
	}
	
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	public long getBankID() {
		return bankID;
	}
	public void setBankID(long bankID) {
		this.bankID = bankID;
	}
	public long getPageType() {
		return pageType;
	}
	public void setPageType(long pageType) {
		this.pageType = pageType;
	}
	public String getCssDate() {
		return cssDate;
	}
	public void setCssDate(String cssDate) {
		this.cssDate = cssDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getBankCountry() {
		return bankCountry;
	}
	public void setBankCountry(long bankCountry) {
		this.bankCountry = bankCountry;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExtractionDate() {
		return extractionDate;
	}

	public void setExtractionDate(String extractionDate) {
		this.extractionDate = extractionDate;
	}

	public List<ExtractionTimeForm> getExtractionTimeFormList() {
		return extractionTimeFormList;
	}

	public void setExtractionTimeFormList(List<ExtractionTimeForm> extractionTimeFormList) {
		this.extractionTimeFormList = extractionTimeFormList;
	}

	public long getPairCurrencyType() {
		return pairCurrencyType;
	}

	public void setPairCurrencyType(long pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}

	public String getFormatDate() {
		return formatDate;
	}
	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}
	public long getExtractionType() {
		return extractionType;
	}
	public void setExtractionType(long extractionType) {
		this.extractionType = extractionType;
	}
	public List<ExtractionDetailForm> getExtractionDetailFormList() {
		return extractionDetailFormList;
	}
	public void setExtractionDetailFormList(List<ExtractionDetailForm> extractionDetailFormList) {
		this.extractionDetailFormList = extractionDetailFormList;
	}
	public String getBankCountryName() {
		return bankCountryName;
	}
	public void setBankCountryName(String bankCountryName) {
		this.bankCountryName = bankCountryName;
	}
	public void addExtractionDetailFormList(ExtractionDetailForm extractionDetailForm) {
		this.extractionDetailFormList.add(extractionDetailForm);
	}
	public void addExtractionTimeFormList(ExtractionTimeForm extractionTimeForm) {
		this.extractionTimeFormList.add(extractionTimeForm);
	}
	public void addSelectedCurrencyAPI(Long value) {
		this.selectedCurrencyAPI.add(value);
	}
	public List<Long> getSelectedCurrencyAPI() {
		return selectedCurrencyAPI;
	}

	public void setSelectedCurrencyAPI(List<Long> selectedCurrencyAPI) {
		this.selectedCurrencyAPI = selectedCurrencyAPI;
	}

	public String getExtractionTypeName() {
		return extractionTypeName;
	}

	public void setExtractionTypeName(String extractionTypeName) {
		this.extractionTypeName = extractionTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPairCurrencyTypeName() {
		return pairCurrencyTypeName;
	}

	public void setPairCurrencyTypeName(String pairCurrencyTypeName) {
		this.pairCurrencyTypeName = pairCurrencyTypeName;
	}

	public String getPageTypeName() {
		return pageTypeName;
	}

	public void setPageTypeName(String pageTypeName) {
		this.pageTypeName = pageTypeName;
	}

	public String getBaseCurrencyName() {
		return baseCurrencyName;
	}

	public void setBaseCurrencyName(String baseCurrencyName) {
		this.baseCurrencyName = baseCurrencyName;
	}

	public long getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(long baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
}
