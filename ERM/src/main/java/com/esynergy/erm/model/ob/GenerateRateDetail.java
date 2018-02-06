package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class GenerateRateDetail {
	
	private long id;
	private GenerateRate generateRate;
	private Set<GenerateRateBank> generateRateBanks = new HashSet<GenerateRateBank>();
	private String type;
	private Currency baseCurrency;
	private Currency pairCurrency;
	private long pairCurrencyType;
	private String rateType;
	private String addUser;
	private String changeUser;
	private Date addDate;
	private Date changeDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public GenerateRate getGenerateRate() {
		return generateRate;
	}
	public void setGenerateRate(GenerateRate generateRate) {
		this.generateRate = generateRate;
	}
	public Set<GenerateRateBank> getGenerateRateBanks() {
		return generateRateBanks;
	}
	public void setGenerateRateBanks(Set<GenerateRateBank> generateRateBanks) {
		this.generateRateBanks = generateRateBanks;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Currency getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public Currency getPairCurrency() {
		return pairCurrency;
	}
	public void setPairCurrency(Currency pairCurrency) {
		this.pairCurrency = pairCurrency;
	}
	public long getPairCurrencyType() {
		return pairCurrencyType;
	}
	public void setPairCurrencyType(long pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	
	
}
