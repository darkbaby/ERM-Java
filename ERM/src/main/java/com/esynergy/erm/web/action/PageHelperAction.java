package com.esynergy.erm.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("pageHelperAction")
public class PageHelperAction  extends ActionSupport implements
ServletRequestAware, ModelDriven{
    public String notPermissionPage(){
    	return SUCCESS;
    }
    public String sessionTimOut(){
    	return SUCCESS;
    }
    
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
}
