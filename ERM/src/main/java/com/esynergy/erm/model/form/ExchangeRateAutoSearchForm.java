package com.esynergy.erm.model.form;


public class ExchangeRateAutoSearchForm {
	 
	private String  dateStart;
	private String  dateEnd;
	private String  baseCurrencyID;
	private String  pairCurrencyID;
	private String  bankName;
	private String  logStatust;
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getLogStatust() {
		return logStatust;
	}
	public void setLogStatust(String logStatust) {
		this.logStatust = logStatust;
	}
	public String getBaseCurrencyID() {
		return baseCurrencyID;
	}
	public void setBaseCurrencyID(String baseCurrencyID) {
		this.baseCurrencyID = baseCurrencyID;
	}
	public String getPairCurrencyID() {
		return pairCurrencyID;
	}
	public void setPairCurrencyID(String pairCurrencyID) {
		this.pairCurrencyID = pairCurrencyID;
	}
	 

}
