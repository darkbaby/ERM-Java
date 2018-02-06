package com.esynergy.erm.model.ob;

import java.util.Date;

import com.esynergy.erm.model.IUser;

public class User implements IUser{
	private long id;
	private String logOnId;
	private String pwd;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String recordStatus;
	private String   createdUser;
	private String   lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	private Country country;
	private AuthorizeGroup authorizeGroup;
	
	public User() {}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getLogOnId() {
		return logOnId;
	}
	public void setLogOnId(String logOnId) {
		this.logOnId = logOnId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public Country getCountry() {
		return country;
	}
	public AuthorizeGroup getAuthorizeGroup() {
		return authorizeGroup;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public void setAuthorizeGroup(AuthorizeGroup authorizeGroup) {
		this.authorizeGroup = authorizeGroup;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
 
	
}
