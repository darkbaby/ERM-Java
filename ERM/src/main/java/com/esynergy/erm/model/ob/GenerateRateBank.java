package com.esynergy.erm.model.ob;

public class GenerateRateBank {
	private long id;
	private Bank bank;
	private GenerateRateDetail generateRateDetail;
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
	public GenerateRateDetail getGenerateRateDetail() {
		return generateRateDetail;
	}
	public void setGenerateRateDetail(GenerateRateDetail generateRateDetail) {
		this.generateRateDetail = generateRateDetail;
	}

}
