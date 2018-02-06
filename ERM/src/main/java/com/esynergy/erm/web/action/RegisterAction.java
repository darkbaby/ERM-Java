package com.esynergy.erm.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
 

@SuppressWarnings("serial")
@Controller("testRegisterAction")
public class RegisterAction extends ActionSupport{  
private String name,password,email;
private Map<String,String> errors = new HashMap<String, String>();
public void validattionVal() { 
	 
    if(name.length()<1)  
        this.addErrors("nameError","Name can't be blank");  
    if(password.length()<6)  
    	this.addErrors("passwordError","Password must be greater than 5");  
}  
  
//getters and setters  
  
public String edit(){  
//perform business logic here  
	this.email =   getText("menu.main.extraction.manage");
	validattionVal();
	if(!errors.isEmpty()) {
		return INPUT;  
	}else {
		return SUCCESS;  
	}
}
public String prepare(){  
	//perform business logic here  
	    return "success";  
	}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@SuppressWarnings({ "rawtypes", "unchecked" })
public Map getErrors() {
	return errors;
}

public void setErrors(Map<String, String> errors) {
	this.errors = errors;
}  
public void addErrors(String key,String msg) {
	this.errors.put(key, msg);
}
}  