 package com.esynergy.erm.web.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.service.UserService;
import com.opensymphony.xwork2.ActionContext;

//@Controller("userLogonAction")
//@Scope("prototype")
@SuppressWarnings({ "rawtypes", "serial" })
public class UserLogonAction extends ActionCommon {
	
	@Autowired 
	private UserService userService;
	
	private UserLogonForm userLogonForm = new UserLogonForm();
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		
	}
	
	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}
	
	public String prepareLogon(){
		userLogonForm = new UserLogonForm();
		return SUCCESS;
	}
	
	public String logOn() {		
		IUser user = userService.getUser(userLogonForm.getUserLogonId(), userLogonForm.getPwd());
		if(user!=null){
			if(user.getRecordStatus().equals(RECORD_STS_ACTIVE)){
				HttpSession session = getReq().getSession(false) == null ? getReq()
						.getSession(true) : getReq().getSession(false);
				session.setAttribute(SESSION_USER, user);
				return SUCCESS;
			}else{
				addErrors("logonError","error.user.inactive");
				return INPUT;
			}
			
		}else{
			addErrors("logonError","error.user.pwd.incorrect");
			return INPUT;
		} 
	 
	}
	public String logOut(){
		HttpSession session = getReq().getSession(false);
		if(session!=null){
			Enumeration keys = session.getAttributeNames();
			while(keys.hasMoreElements()){
				String key = (String) keys.nextElement();
				session.removeAttribute(key);
			}
		}		 
		
		return SUCCESS;
	}
	
	public UserLogonForm getUserLogonForm() {
		return userLogonForm;
	}
	
	public void setUserLogonForm(UserLogonForm userLogonForm) {
		this.userLogonForm = userLogonForm;
	}
}
