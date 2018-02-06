package com.esynergy.erm.model.ob;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.esynergy.erm.model.IExchangeRate;


@Entity
public class ExchangeRateManual extends ExchangeRate implements IExchangeRate {
	private Set<FileUploadERManual> fileUploadERManualList = new HashSet<FileUploadERManual>();

	public Set<FileUploadERManual> getFileUploadERManualList() {
		return fileUploadERManualList;
	}

	public void setFileUploadERManualList(
			Set<FileUploadERManual> fileUploadERManualList) {
		this.fileUploadERManualList = fileUploadERManualList;
	}
	public void addFileUploadERManualList(FileUploadERManual fileUploadERManual){
		this.fileUploadERManualList.add(fileUploadERManual);
	}

	@Override
	public Set<FileUploadERAuto> getFileUploadERAutoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFileUploadERAutoList(
			Set<FileUploadERAuto> fileUploadERAutoList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFileUploadERAutoList(FileUploadERAuto fileUploadERAuto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBankOfRate(Set<BankOfRate> bankOfRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<BankOfRate> getBankOfRate() {
		// TODO Auto-generated method stub
		return null;
	}
}
