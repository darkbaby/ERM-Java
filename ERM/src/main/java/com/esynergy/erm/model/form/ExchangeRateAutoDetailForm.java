package com.esynergy.erm.model.form;

import javax.persistence.Entity;

@Entity
public class ExchangeRateAutoDetailForm {
	private long id;
	private String currencyCode;
	private String value;
	private String buyingRate;
	private String sellingRate;
  
 	private long exchangeRateId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getExchangeRateId() {
		return exchangeRateId;
	}

	public void setExchangeRateId(long exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getBuyingRate() {
		return buyingRate;
	}

	public void setBuyingRate(String buyingRate) {
		this.buyingRate = buyingRate;
	}

	public String getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(String sellingRate) {
		this.sellingRate = sellingRate;
	} 	 
}
