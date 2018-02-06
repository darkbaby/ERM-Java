package com.esynergy.erm.model.ob;

import javax.persistence.Entity;

@Entity
public class BankOfRate {
	private long id;
	private Bank bank;
	private ExchangeRateAuto exchangeRateAuto;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public ExchangeRateAuto getExchangeRateAuto() {
		return exchangeRateAuto;
	}
	public void setExchangeRateAuto(ExchangeRateAuto exchangeRateAuto) {
		this.exchangeRateAuto = exchangeRateAuto;
	}
}
