package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.type.PrimitiveType;
import javax.persistence.Entity;

@Entity
public class Extraction {

	private long id;
	private Bank bank = new Bank();
	private long extractionType;
	private String bankURL;
	private String formatDate;
	private String cssDate;
	private long pairCurrencyType;
	private String extractionDate;
	private long pageType;
	private String status;
	private String addUser;
	private String changeUser;
	private Date addDate;
	private Date changeDate;
	private long deleteFlag;
	private Currency baseCurrency;
	private Set<ExtractionTime> extractionTimeList = new HashSet<ExtractionTime>();
	private Set<ExtractionDetail> extractionDetailList = new HashSet<ExtractionDetail>();	
	
	public long getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(long deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

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
	public long getExtractionType() {
		return extractionType;
	}
	public void setExtractionType(long extractionType) {
		this.extractionType = extractionType;
	}
	public String getBankURL() {
		return bankURL;
	}
	public void setBankURL(String bankURL) {
		this.bankURL = bankURL;
	}
	public String getFormatDate() {
		return formatDate;
	}
	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}
	public String getCssDate() {
		return cssDate;
	}
	public void setCssDate(String cssDate) {
		this.cssDate = cssDate;
	}
	public long getPairCurrencyType() {
		return pairCurrencyType;
	}
	public void setPairCurrencyType(long pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	public String getExtractionDate() {
		return extractionDate;
	}
	public void setExtractionDate(String extractionDate) {
		this.extractionDate = extractionDate;
	}
	public long getPageType() {
		return pageType;
	}
	public void setPageType(long pageType) {
		this.pageType = pageType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Set<ExtractionTime> getExtractionTimeList() {
		return extractionTimeList;
	}
	public void setExtractionTimeList(Set<ExtractionTime> extractionTimeList) {
		this.extractionTimeList = extractionTimeList;
	}
	public Set<ExtractionDetail> getExtractionDetailList() {
		return extractionDetailList;
	}
	public void setExtractionDetailList(Set<ExtractionDetail> extractionDetailList) {
		this.extractionDetailList = extractionDetailList;
	}
//	public void addExtractionDetailList(ExtractionDetail extractionDetail) {
//		this.extractionDetailList.add(extractionDetail);
//	}
//	public void addExtractionTimeList(ExtractionTime extractionTime) {
//		this.extractionTimeList.add(extractionTime);
//	}
	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	
}
