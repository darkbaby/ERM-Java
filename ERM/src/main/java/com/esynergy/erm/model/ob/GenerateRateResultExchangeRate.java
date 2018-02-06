package com.esynergy.erm.model.ob;

public class GenerateRateResultExchangeRate {
	
	private String bankName;
	private long pairCurrencyType;
	private String type;
	private Currency baseCurrency;
	private Currency pairCurrency;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getPairCurrencyType() {
		return pairCurrencyType;
	}
	public void setPairCurrencyType(long pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Currency getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(Currency pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public Currency getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
	
}
