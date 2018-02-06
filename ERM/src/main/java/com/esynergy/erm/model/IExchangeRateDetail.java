package com.esynergy.erm.model;

import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.ExchangeRate;

public interface IExchangeRateDetail {
	public long getId();
	public void setId(long id);
	public String getType();
	public void setType(String type);
	public double getValue();
	public void setValue(double value);
	public double getBuyingRate();
	public void setBuyingRate(double buyingRate);
	public double getSellingRate();
	public void setSellingRate(double sellingRate);
	public double getAvgRate();
	public void setAvgRate(double avgRate);
	public ExchangeRate getExchangeRate();
	public void setExchangeRate(ExchangeRate exchangeRate);
	public Bank getBank();
	public void setBank(Bank bank);
}
