package com.esynergy.erm.model.ob;


public class ExchangeRateDetailVeiwView implements Cloneable {
	private long id;
	private String type;
	private double value;
	private double buyingRate;
	private double sellingRate;
	private double avgRate;
	private long ownerId;
	private Bank bank;
	public long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public double getValue() {
		return value;
	}
	public double getBuyingRate() {
		return buyingRate;
	}
	public double getSellingRate() {
		return sellingRate;
	}
	public double getAvgRate() {
		return avgRate;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public Bank getBank() {
		return bank;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public void setBuyingRate(double buyingRate) {
		this.buyingRate = buyingRate;
	}
	public void setSellingRate(double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
}
