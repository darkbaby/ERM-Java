package com.esynergy.erm.model.ob;

import java.util.Date;

public class AuthorizePermission {
	private long id;
	private String status;
	private String   createdUser;
	private String   lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	private AuthorizeGroup authorizeGroup;
	private AuthorizeFunction authorizeFunction;
	public long getId() {
		return id;
	}
	public String getStatus() {
		return status;
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
	public AuthorizeGroup getAuthorizeGroup() {
		return authorizeGroup;
	}
	public AuthorizeFunction getAuthorizeFunction() {
		return authorizeFunction;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public void setAuthorizeGroup(AuthorizeGroup authorizeGroup) {
		this.authorizeGroup = authorizeGroup;
	}
	public void setAuthorizeFunction(AuthorizeFunction authorizeFunction) {
		this.authorizeFunction = authorizeFunction;
	}
	
}
