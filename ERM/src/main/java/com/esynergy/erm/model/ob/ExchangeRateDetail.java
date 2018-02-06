package com.esynergy.erm.model.ob;

public class ExchangeRateDetail {
	private long id;
	private Currency firstCurrency;
	private Currency pairCurrency;
	private double value;
	private double buyingRate;
	private double sellingRate;
	private double avgRate;
	private long ownerId;
	private String recordStatus;
	private ExchangeRate exchangeRate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getBuyingRate() {
		return buyingRate;
	}
	public void setBuyingRate(double buyingRate) {
		this.buyingRate = buyingRate;
	}
	public double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public double getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public Currency getFirstCurrency() {
		return firstCurrency;
	}
	public void setFirstCurrency(Currency firstCurrency) {
		this.firstCurrency = firstCurrency;
	}
	public Currency getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(Currency pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public ExchangeRate getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
 
}
