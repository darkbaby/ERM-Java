package com.esynergy.erm.model.ob;

import java.util.Date;

import javax.persistence.Entity;

public class ManualTarget {
	
	private long id;
	private Currency pairCurrency;
	private Currency baseCurrency;
	private String status;
	private User owner;
	private Date addDate;
	private String addUser;
	private Date changeDate;
	private String changeUser;
	private Date effectiveDate;
	private Date expiredDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	
}
