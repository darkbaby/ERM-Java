package com.esynergy.erm.model.ob;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import com.esynergy.erm.model.IExchangeRate;

@Entity
public class ExchangeRateAuto extends ExchangeRate implements IExchangeRate{

	private Set<BankOfRate>   bankOfRate = new HashSet<BankOfRate>();
//	private BankOfRate   bankOfRate;
	private Set<FileUploadERAuto> fileUploadERAutoList = new HashSet<FileUploadERAuto>();
	
	public Set<FileUploadERAuto> getFileUploadERAutoList() {
		return fileUploadERAutoList;
	}
	public void setFileUploadERAutoList(Set<FileUploadERAuto> fileUploadERAutoList) {
		this.fileUploadERAutoList = fileUploadERAutoList;
	}
	public void addFileUploadERAutoList(FileUploadERAuto  fileUploadERAuto) {
		this.fileUploadERAutoList.add(fileUploadERAuto);
	}

	@Override
	public Set<FileUploadERManual> getFileUploadERManualList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFileUploadERManualList(
			Set<FileUploadERManual> fileUploadERManualList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFileUploadERManualList(FileUploadERManual fileUploadERManual) {
		// TODO Auto-generated method stub
		
	}
//	public BankOfRate getBankOfRate() {
//		return bankOfRate;
//	}
//	public void setBankOfRate(BankOfRate bankOfRate) {
//		this.bankOfRate = bankOfRate;
//	}

	public void setBankOfRate(Set<BankOfRate> bankOfRate) {
		this.bankOfRate = bankOfRate;
	}

	public Set<BankOfRate> getBankOfRate() {
		return bankOfRate;
	}
	
}
