package com.esynergy.erm.model.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esynergy.erm.model.IFileUpload;
import com.esynergy.erm.model.ob.Country;


public class ExchangeRateManualForm {
	private long   id;
	private String  rateDate;
	private String  rateDateStatic;
	private String refDate;
//	private String createdUser;
//	private String lastUpdateUser;
//	private Date   createdDate;
//	private Date   lastUpdateDate;
	private String pairCurrencyType;
    private List<ExchangeRateManualDetailForm> exchangeRateDetailList;
    private List<FileUploadForm> fileUploadList;
	private List<Long> exchangeRateDetailFormRemoveList;
	private List<FileUploadForm> fileUploadFormRemoveList;
//    private Map<String,IFileUpload> fileUploadMap;
    public ExchangeRateManualForm(){
    	this.exchangeRateDetailList = new ArrayList<ExchangeRateManualDetailForm>();
    	this.fileUploadList = new  ArrayList<FileUploadForm>();
    	this.exchangeRateDetailFormRemoveList = new ArrayList<Long>();
    	this.fileUploadFormRemoveList = new ArrayList<FileUploadForm>();
//    	this.fileUploadMap = new HashMap<String, IFileUpload>();
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRateDate() {
		return rateDate;
	}
	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}
	 
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//	public Date getLastUpdateDate() {
//		return lastUpdateDate;
//	}
//	public void setLastUpdateDate(Date lastupdateDate) {
//		this.lastUpdateDate = lastupdateDate;
//	}
	public List<ExchangeRateManualDetailForm> getExchangeRateDetailList() {
		return exchangeRateDetailList;
	}
	public void setExchangeRateDetailList(List<ExchangeRateManualDetailForm> exchangeRateDetailList) {
		this.exchangeRateDetailList = exchangeRateDetailList;
	}
    public void addExchangeRateDetailList(ExchangeRateManualDetailForm e) {
    	this.exchangeRateDetailList.add(e);
    }
	public String getRateDateStatic() {
		return rateDateStatic;
	}
	public void setRateDateStatic(String rateDateStatic) {
		this.rateDateStatic = rateDateStatic;
	}
//	public Map<String, IFileUpload> getFileUploadMap() {
//		return fileUploadMap;
//	}
//	public void setFileUploadMap(Map<String, IFileUpload> fileUploadMap) {
//		this.fileUploadMap = fileUploadMap;
//	}
// 
//	public void putFileUploadMap(String key,FileUploadERManualForm obj){
//		this.fileUploadMap.put(key, obj);
//	}
	public List<FileUploadForm> getFileUploadList() {
		return fileUploadList;
	}
	public void setFileUploadList(
			List<FileUploadForm> fileUploadList) {
		this.fileUploadList = fileUploadList;
	}
	public void addFileUploadList(FileUploadForm f){
		this.fileUploadList.add(f);
	}
//	public String getCreatedUser() {
//		return createdUser;
//	}
//	public String getLastUpdateUser() {
//		return lastUpdateUser;
//	}
//	public void setCreatedUser(String createdUser) {
//		this.createdUser = createdUser;
//	}
//	public void setLastUpdateUser(String lastUpdateUser) {
//		this.lastUpdateUser = lastUpdateUser;
//	}
	public String getPairCurrencyType() {
		return pairCurrencyType;
	}
	public void setPairCurrencyType(String pairCurrencyType) {
		this.pairCurrencyType = pairCurrencyType;
	}
	public String getRefDate() {
		return refDate;
	}
	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}
	public List<Long> getExchangeRateDetailFormRemoveList() {
		return exchangeRateDetailFormRemoveList;
	}
	public void setExchangeRateDetailFormRemoveList(List<Long> exchangeRateDetailFormRemoveList) {
		this.exchangeRateDetailFormRemoveList = exchangeRateDetailFormRemoveList;
	}
	public List<FileUploadForm> getFileUploadFormRemoveList() {
		return fileUploadFormRemoveList;
	}
	public void setFileUploadFormRemoveList(List<FileUploadForm> fileUploadFormRemoveList) {
		this.fileUploadFormRemoveList = fileUploadFormRemoveList;
	}

}
