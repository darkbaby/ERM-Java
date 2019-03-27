package com.esynergy.erm.model.form;

public class GenerateRateSearchForm {
	
	private String pairCurrency;
	private String baseCurrency;
	private String bankName;

	public String getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(String pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
