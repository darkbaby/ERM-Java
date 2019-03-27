package com.esynergy.erm.model.form;

public class ManualTargetCheckForm {

	private long id;
	private String baseCurrencyStr;
	private String pairCurrencyStr;
	private String chk;
	private String owner;
	private boolean haveAnotherOwner;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getBaseCurrencyStr() {
		return baseCurrencyStr;
	}

	public void setBaseCurrencyStr(String baseCurrencyStr) {
		this.baseCurrencyStr = baseCurrencyStr;
	}

	public String getPairCurrencyStr() {
		return pairCurrencyStr;
	}

	public void setPairCurrencyStr(String pairCurrencyStr) {
		this.pairCurrencyStr = pairCurrencyStr;
	}

	public boolean isHaveAnotherOwner() {
		return haveAnotherOwner;
	}

	public void setHaveAnotherOwner(boolean haveAnotherOwner) {
		this.haveAnotherOwner = haveAnotherOwner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
