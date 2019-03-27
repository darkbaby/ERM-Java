package com.esynergy.erm.model.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLogonForm {
	private long id;
	private String userLogonId;
	private String pwd;
	private String pwdConfirm;
	private String firstName;
	private String lastName;
	private String emailAddr;
	private String countryId;
	private String groupId;
	private String   createdUser;
	private String   lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	private String status;
	
	private List<ManualTargetCheckForm> manualTargetCheckFormList = new ArrayList<ManualTargetCheckForm>();;
	
	public String getUserLogonId() {
		return userLogonId;
	}
	public void setUserLogonId(String userLogonId) {
		this.userLogonId = userLogonId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmailAddr() {
		return emailAddr;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getPwdConfirm() {
		return pwdConfirm;
	}
	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public List<ManualTargetCheckForm> getManualTargetCheckFormList() {
		return manualTargetCheckFormList;
	}
	public void setManualTargetCheckFormList(List<ManualTargetCheckForm> manualTargetCheckFormList) {
		this.manualTargetCheckFormList = manualTargetCheckFormList;
	}

	
}
