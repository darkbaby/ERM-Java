package com.esynergy.erm.model.ob;

public class ManualTarget {
	
	private long id;
	private Currency pairCurrency;
	private Currency baseCurrency;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Currency getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public Currency getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(Currency pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
