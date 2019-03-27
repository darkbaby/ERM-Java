package com.esynergy.erm.model.ob;

import java.util.Date;

public class ExtractionDetail {

	private Extraction extraction;
	private long id;
	private Currency currency;
	private long value;
	private String extractCurrency;
	private String extractBuyingRate;
	private String extractSellingRate;
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
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public String getExtractCurrency() {
		return extractCurrency;
	}
	public void setExtractCurrency(String extractCurrency) {
		this.extractCurrency = extractCurrency;
	}
	public String getExtractBuyingRate() {
		return extractBuyingRate;
	}
	public void setExtractBuyingRate(String extractBuyingRate) {
		this.extractBuyingRate = extractBuyingRate;
	}
	public String getExtractSellingRate() {
		return extractSellingRate;
	}
	public void setExtractSellingRate(String extractSellingRate) {
		this.extractSellingRate = extractSellingRate;
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
	public Extraction getExtraction() {
		return extraction;
	}
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}
}
