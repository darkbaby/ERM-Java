package com.esynergy.erm.model.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import com.esynergy.erm.model.IFileUpload;
import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.FileUpload;
import com.esynergy.erm.model.ob.FileUploadERAuto;
 

@Entity
public class ExchangeRateAutoForm {
	private long   id;
	private Date   rateDate;
	private Date   createdDate;
	private String bankName;
	private String countryOfBank;
	private List<ExchangeRateAutoDetailForm> exchangeRateAutoDetails = new  ArrayList<ExchangeRateAutoDetailForm>();
	private Set<FileUploadERAuto> fileUploadERAutoList = new HashSet<FileUploadERAuto>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getRateDate() {
		return rateDate;
	}
	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCountryOfBank() {
		return countryOfBank;
	}
	public void setCountryOfBank(String countryOfBank) {
		this.countryOfBank = countryOfBank;
	}
	public List<ExchangeRateAutoDetailForm> getExchangeRateAutoDetails() {
		return exchangeRateAutoDetails;
	}
	public void setExchangeRateAutoDetails(
			List<ExchangeRateAutoDetailForm> exchangeRateAutoDetails) {
		this.exchangeRateAutoDetails = exchangeRateAutoDetails;
	}	
	public void addExchangeRateAutoDetails(ExchangeRateAutoDetailForm exchangeRateAutoDetails){
		this.exchangeRateAutoDetails.add(exchangeRateAutoDetails);
	}
	public Set<FileUploadERAuto> getFileUploadERAutoList() {
		return fileUploadERAutoList;
	}
	public void setFileUploadERAutoList(Set<FileUploadERAuto> fileUploadERAutoList) {
		this.fileUploadERAutoList = fileUploadERAutoList;
	}
}
