package com.esynergy.erm.model.form;


public class UserLogonResetPwdForm {
	private UserLogonForm user = new UserLogonForm();
	private String adminLogOnId;
	private String adminPwd;
	private String newPasswordOfUser;
	 
	public void setAdminLogOnId(String adminLogOnId) {
		this.adminLogOnId = adminLogOnId;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public UserLogonForm getUser() {
		return user;
	}
	public String getAdminLogOnId() {
		return adminLogOnId;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
 
	public void setUser(UserLogonForm user) {
		this.user = user;
	}
	public String getNewPasswordOfUser() {
		return newPasswordOfUser;
	}
	public void setNewPasswordOfUser(String newPasswordOfUser) {
		this.newPasswordOfUser = newPasswordOfUser;
	}
	 
}
