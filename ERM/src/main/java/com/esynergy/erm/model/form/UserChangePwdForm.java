package com.esynergy.erm.model.form;

public class UserChangePwdForm extends UserLogonForm {
	private String currenctPwd;

	public String getCurrenctPwd() {
		return currenctPwd;
	}

	public void setCurrenctPwd(String currenctPwd) {
		this.currenctPwd = currenctPwd;
	}
}
