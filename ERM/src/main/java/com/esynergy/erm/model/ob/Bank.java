package com.esynergy.erm.model.ob;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Bank {
	private long id;
	private String bankName;
	private String bankShortName;
	private Country country;
//	private Set<BankOfRate> bankOfRate = new HashSet<BankOfRate>();
	
//	private Extraction extraction = new Extraction();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankShortName() {
		return bankShortName;
	}
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
//	public Extraction getExtraction() {
//		return extraction;
//	}
//	public void setExtraction(Extraction extraction) {
//		this.extraction = extraction;
//	}
//	public Set<BankOfRate> getBankOfRate() {
//		return bankOfRate;
//	}
//	public void setBankOfRate(Set<BankOfRate> bankOfRate) {
//		this.bankOfRate = bankOfRate;
//	}
	
}
