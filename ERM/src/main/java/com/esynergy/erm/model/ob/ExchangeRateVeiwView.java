package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class ExchangeRateVeiwView implements Cloneable {
	private Currency firstCurrency;
	private Currency pairCurrency;
	private long pairCurrencyType;
	private Date rateDate;
	private List<ExchangeRateDetailVeiwView> exchangeRateDetails = new ArrayList<ExchangeRateDetailVeiwView>();
    
	@Override
	 public Object clone() throws CloneNotSupportedException {
		return super.clone();
	 }

	public Currency getPairCurrency() {
		return pairCurrency;
	}

	public Currency getFirstCurrency() {
		return firstCurrency;
	}

	public void setPairCurrency(Currency pairCurrency) {
		this.pairCurrency = pairCurrency;
	}

	public void setFirstCurrency(Currency firstCurrency) {
		this.firstCurrency = firstCurrency;
	}

	public List<ExchangeRateDetailVeiwView> getExchangeRateDetails() {
		return exchangeRateDetails;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public void setExchangeRateDetails(
			List<ExchangeRateDetailVeiwView> exchangeRateDetails) {
		this.exchangeRateDetails = exchangeRateDetails;
	}
	public void addExchangeRateDetails(ExchangeRateDetailVeiwView exchangeRateDetail) {
		this.exchangeRateDetails.add(exchangeRateDetail);
	}

	public long getPairCurrencyType() {
		return pairCurrencyType;
	}

	public void setPairCurrencyType(long pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	
}
