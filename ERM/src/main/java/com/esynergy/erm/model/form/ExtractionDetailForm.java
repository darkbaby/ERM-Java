package com.esynergy.erm.model.form;

public class ExtractionDetailForm {

	private long id;
	private long currency;
	private long value;
	private String extractionCurrency;
	private String extractionBuyingRate;
	private String extractionSellingRate;
	private String chk;
	private String currencyCode;
	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCurrency() {
		return currency;
	}
	public void setCurrency(long currency) {
		this.currency = currency;
	}
	public long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getExtractionCurrency() {
		return extractionCurrency;
	}
	public void setExtractionCurrency(String extractionCurrency) {
		this.extractionCurrency = extractionCurrency;
	}
	public String getExtractionBuyingRate() {
		return extractionBuyingRate;
	}
	public void setExtractionBuyingRate(String extractionBuyingRate) {
		this.extractionBuyingRate = extractionBuyingRate;
	}
	public String getExtractionSellingRate() {
		return extractionSellingRate;
	}
	public void setExtractionSellingRate(String extractionSellingRate) {
		this.extractionSellingRate = extractionSellingRate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
