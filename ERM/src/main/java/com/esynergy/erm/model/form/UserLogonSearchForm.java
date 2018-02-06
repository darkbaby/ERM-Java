package com.esynergy.erm.model.form;


public class UserLogonSearchForm {
	private String userLogonId;
	private String firstName;
	private String lastName;
	private String emailAddr;
	private String countryId;
	private String groupId;
	public String getUserLogonId() {
		return userLogonId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getCountryId() {
		return countryId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setUserLogonId(String userLogonId) {
		this.userLogonId = userLogonId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
}
	