
package com.esynergy.erm.model.ob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AuthorizeFunction {
	private long id;
	private String functionName;
	private String status;
	private String   createdUser;
	private String   lastUpdateUser;
	private Date   createdDate;
	private Date   lastUpdateDate;
	/*private Set<AuthorizePermission> permissionList = new HashSet<AuthorizePermission>();*/
	 
	public long getId() {
		return id;
	}
	public String getFunctionName() {
		return functionName;
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
	/*public Set<AuthorizePermisssion> getPermissionList() {
		return permissionList;
	}*/
	public void setId(long id) {
		this.id = id;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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
 
}
