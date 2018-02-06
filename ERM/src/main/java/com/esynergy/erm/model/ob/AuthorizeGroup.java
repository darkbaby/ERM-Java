package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AuthorizeGroup {
	private long id;
	private String groupName;
	private String description;
	private String status;
	private String   createdUser;
	private String   lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	private Set<AuthorizePermission> permissionList = new HashSet<AuthorizePermission>();
	public long getId() {
		return id;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getDescription() {
		return description;
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
 	public Set<AuthorizePermission> getPermissionList() {
		return permissionList;
	} 
	public void setId(long id) {
		this.id = id;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setPermissionList(Set<AuthorizePermission> permissionList) {
		this.permissionList = permissionList;
	}
	public void addPermissionList( AuthorizePermission permission) {
		this.permissionList.add(permission);
	} 
	
}
