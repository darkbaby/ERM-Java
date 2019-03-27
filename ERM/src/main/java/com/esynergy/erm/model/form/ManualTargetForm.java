package com.esynergy.erm.model.form;

public class ManualTargetForm {

	private long id;
	private String baseCurrency;
	private String pairCurrency;
	private long ownerID;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}
	
}
