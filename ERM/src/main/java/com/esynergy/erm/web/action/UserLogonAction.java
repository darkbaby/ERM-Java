 package com.esynergy.erm.web.action;

 
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userLogonAction")
@SuppressWarnings({ "rawtypes", "serial" })
public class UserLogonAction extends ActionCommon implements
ServletRequestAware, ModelDriven{
	@Autowired private UserService userService;
	private UserLogonForm userLogonForm = new UserLogonForm();
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		 
	}
	
	public String prepareLogon(){
		errors = new HashMap<String, Object>(); 
		userLogonForm = new UserLogonForm();
		return SUCCESS;
	}
	public String logOn() {
		
//		throw new GenericJDBCException("excep", new SQLException());
		
		errors = new HashMap<String, Object>();
		 IUser user = userService.getUser(userLogonForm.getUserLogonId(), userLogonForm.getPwd());
		
		 if(user!=null){
			if(user.getRecordStatus().equals(RECORD_STS_ACTIVE)){
				HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				HttpSession session = request.getSession(false) == null ? request
						.getSession(true) : request.getSession(false);
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
			HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			HttpSession session = request.getSession(false);
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
