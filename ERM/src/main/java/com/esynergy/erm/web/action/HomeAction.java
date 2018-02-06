package com.esynergy.erm.web.action;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

 
import com.esynergy.erm.service.CurrencyService;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
@Controller("homeAction")
public class HomeAction extends ActionSupport implements IPageContains,ServletRequestAware  {
    /**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(HomeAction.class);
    
	@Autowired
	private CurrencyService currencyService;
	
	public void setServletRequest(HttpServletRequest request) {
		request.setAttribute(MENU_DASHBOARD, MENU_EXCHANGE_RATE);
		HttpSession session = request.getSession(false)==null? request.getSession(true):request.getSession(false);
		session.setAttribute(SESSION_CURRENCY_LIST, currencyService.listAll()); 
		logger.debug("--Created Session is session_currency_list");
		
	}
	
	public String exicue() {
		return SUCCESS;
	}
	
	
	 
	 

}
