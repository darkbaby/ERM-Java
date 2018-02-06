package com.esynergy.erm.model;

import java.util.Date;
import java.util.Set;

import com.esynergy.erm.model.ob.BankOfRate;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.FileUploadERAuto;
import com.esynergy.erm.model.ob.FileUploadERManual;
import com.esynergy.erm.model.ob.User;

public interface IExchangeRate {
	public long getId();
	public void setId(long id);
	public Date getRateDate();
	public void setRateDate(Date rateDate);
	public Set<ExchangeRateDetail> getExchangeRateDetails();
	public void setExchangeRateDetails(Set<ExchangeRateDetail> exchangeRateDetails);
	public void addExchangeRateDetails(ExchangeRateDetail exchangeRateDetail);
	public void clearExchangeRateDetails();
	public String getOwnerType();
	public void setOwnerType(String ownerType);
	public String getCreatedUser();
	public void setCreatedUser(String createdUser);
	public String getLastUpdateUser();
	public void setLastUpdateUser(String lastUpdateUser);
	public Date getCreatedDate();
	public void setCreatedDate(Date createdDate);
	public Date getLastUpdateDate();
	public void setLastUpdateDate(Date lastUpdateDate);
	public long getPariCurrencyType();
	public void setPariCurrencyType(long pariCurrencyType);
	public Set<FileUploadERAuto> getFileUploadERAutoList();
	public void setFileUploadERAutoList(Set<FileUploadERAuto> fileUploadERAutoList);
	public void addFileUploadERAutoList(FileUploadERAuto  fileUploadERAuto);
	public Set<FileUploadERManual> getFileUploadERManualList();
	public void setFileUploadERManualList(Set<FileUploadERManual> fileUploadERManualList);
	public void addFileUploadERManualList(FileUploadERManual fileUploadERManual);
	public void setBankOfRate(Set<BankOfRate> bankOfRate);
	public Set<BankOfRate> getBankOfRate();
//	public BankOfRate getBankOfRate();
//	public void setBankOfRate(BankOfRate bankOfRate);
}
