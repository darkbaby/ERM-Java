package com.esynergy.erm.api.action;

import java.util.ArrayList;
import java.util.List;

 
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.ob.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("exchangeRateAPIActionCtrl")
@Scope("prototype")
public class ExchangeRateAPIAction extends ActionSupport {
	private List<User> users = new ArrayList<User>();
	
 
 	
 	public String execute()  {
 		   users = new ArrayList<User>();
 		    User user = new User();
 	        user.setLogOnId("user01");
 	        user.setFirstName("Amorn");
 	        users.add(user);
 	  return SUCCESS;
 	}
 	  
	
	public List<User> getUsers() {
		return users;
	}
	public void setUser(List<User> users) {
		this.users = users;
	}
}
