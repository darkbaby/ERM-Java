package com.esynergy.erm.model.form;

public class ExchangeRateSearchForm {
	private long baseCurrencyId;
	private long pairCurrencyId;
	private long countryOfBank;
	private String origin;
	private String status;
	private String currentDate;
 
	public long getCountryOfBank() {
		return countryOfBank;
	}
	public void setCountryOfBank(long countryOfBank) {
		this.countryOfBank = countryOfBank;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getBaseCurrencyId() {
		return baseCurrencyId;
	}
	public void setBaseCurrencyId(long baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}
	public long getPairCurrencyId() {
		return pairCurrencyId;
	}
	public void setPairCurrencyId(long pairCurrencyId) {
		this.pairCurrencyId = pairCurrencyId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	 
 
}
