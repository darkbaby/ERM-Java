package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ExchangeRateAutoHISTLog {
	
	private long id;
	private String logModule;
	private String logStatus;
	private String logDescription;
	private String createdUser;
	private String lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	
	private ExchangeRateAuto exchangeRateAuto;
	
	private Extraction extraction;
	private Set<BankOfRate> bankOfRate = new HashSet<BankOfRate>();
	
//	public Set<BankOfRate> getBankOfRate() {
//		return bankOfRate;
//	}
//	public void setBankOfRate(Set<BankOfRate> bankOfRate) {
//		this.bankOfRate = bankOfRate;
//	}
	
	public ExchangeRateAuto getExchangeRateAuto() {
		return exchangeRateAuto;
	}
	public void setExchangeRateAuto(ExchangeRateAuto exchangeRateAuto) {
		this.exchangeRateAuto = exchangeRateAuto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogModule() {
		return logModule;
	}
	public void setLogModule(String logModule) {
		this.logModule = logModule;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getLogDescription() {
		return logDescription;
	}
	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Extraction getExtraction() {
		return extraction;
	}
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}
}
