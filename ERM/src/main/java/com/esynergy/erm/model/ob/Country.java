package com.esynergy.erm.model.ob;

public class Country {

	private long id;
	private String countryName;
//	private String currencyCode;
	private Currency currency;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
//	public String getCurrencyCode() {
//		return currencyCode;
//	}
//	public void setCurrencyCode(String currencyCode) {
//		this.currencyCode = currencyCode;
//	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
