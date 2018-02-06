package com.esynergy.erm.model.form;

import com.esynergy.erm.model.ob.Bank;

public class GenerateRateBankForm {
	private long id;
	private String bankName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
