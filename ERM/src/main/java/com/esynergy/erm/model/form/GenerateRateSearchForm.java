package com.esynergy.erm.model.form;

public class GenerateRateSearchForm {
	
	private long pairCurrencyID;
	private long baseCurrencyID;
	private String bankName;
	
	public long getPairCurrencyID() {
		return pairCurrencyID;
	}
	public void setPairCurrencyID(long pairCurrencyID) {
		this.pairCurrencyID = pairCurrencyID;
	}
	public long getBaseCurrencyID() {
		return baseCurrencyID;
	}
	public void setBaseCurrencyID(long baseCurrencyID) {
		this.baseCurrencyID = baseCurrencyID;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
