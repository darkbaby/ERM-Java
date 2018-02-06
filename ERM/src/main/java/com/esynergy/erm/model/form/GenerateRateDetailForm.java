package com.esynergy.erm.model.form;

import com.esynergy.erm.model.ob.GenerateRateBank;

public class GenerateRateDetailForm {
	
	private long id;
	private String type;
	private GenerateRateBankForm bankForm;
	private String baseCurrency;
	private String pairCurrency;
	private String pairCurrencyType;
	private String rateType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public GenerateRateBankForm getBankForm() {
		return bankForm;
	}
	public void setBankForm(GenerateRateBankForm bankForm) {
		this.bankForm = bankForm;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(String pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public String getPairCurrencyType() {
		return pairCurrencyType;
	}
	public void setPairCurrencyType(String pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	
	
}
