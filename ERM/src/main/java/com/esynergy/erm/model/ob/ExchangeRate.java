package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class ExchangeRate implements Cloneable {
	protected long   id;
	protected Date   rateDate;
	protected long    pariCurrencyType;
	protected String ownerType;
	protected String   createdUser;
	protected String   lastUpdateUser;
	protected Date   createdDate;
	protected Date   lastUpdateDate;
	protected Date   refDate;
	protected Set<ExchangeRateDetail> exchangeRateDetails = new HashSet<ExchangeRateDetail>();
 	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getRateDate() {
		return rateDate;
	}
	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}
	public Set<ExchangeRateDetail> getExchangeRateDetails() {
		return exchangeRateDetails;
	}
	public void setExchangeRateDetails(Set<ExchangeRateDetail> exchangeRateDetails) {
		this.exchangeRateDetails = exchangeRateDetails;
	}
	public void addExchangeRateDetails(ExchangeRateDetail exchangeRateDetail) {
		this.exchangeRateDetails.add(exchangeRateDetail);
	}
	public void clearExchangeRateDetails(){
		if(this.exchangeRateDetails!=null){
			this.exchangeRateDetails.clear();
		}
	}
	@Override
	 public Object clone() throws CloneNotSupportedException {
		return super.clone();
	 }
 
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
 
	public long getPariCurrencyType() {
		return pariCurrencyType;
	}
	public void setPariCurrencyType(long pariCurrencyType) {
		this.pariCurrencyType = pariCurrencyType;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Date getRefDate() {
		return refDate;
	}
	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}
 
}
