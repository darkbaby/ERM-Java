package com.esynergy.erm.model.form;

public class UserChangePwdForm extends UserLogonForm {
	private String currentPwd;

	public String getCurrentPwd() {
		return currentPwd;
	}

	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}


}
