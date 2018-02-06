package com.esynergy.erm.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.hbm.util.HibernateUtil;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

 
@SuppressWarnings("serial")
@Controller("exchangeRateActionCtrl")
@Scope("prototype")
public class ExchangeRateAction extends ActionSupport implements IPageContains,ServletRequestAware,ModelDriven {
	private static final Logger logger = Logger.getLogger(ExchangeRateAction.class);
	
	private List<IExchangeRate> exchangeRateManuals = new ArrayList<IExchangeRate>();
	private ExchangeRateManual exchangeRateManual;
	
	@Autowired
	private ExchangeRateManualService exchangeRateManualService;
	
	
	private String mainMenu;
	
	public void setServletRequest(HttpServletRequest request) {
		request.setAttribute(MAIN_MENU_ATTR, MENU_DASHBOARD);
		
	}
	
	public String prepareViewExchangeRateByManual() {
		exchangeRateManuals = exchangeRateManualService.listExchangeRateManualByRateDate(new Date());
	/**
	    Logger
	 */
		logger.debug("***************************************************");
		logger.debug("**Method : prepareViewExchangeRateByManual");
		logger.debug("**JSP : pages/listExchangeRateByManual.jsp");
		logger.debug("*********************END**************************");
		
		return SUCCESS;
	} 
 	
 	public String execute()  {
 		    /*User user = new User();
 	        user.setLogOnId("user01");
 	        user.setFirstName("Amorn");
 	        user.setLsatName("Jandee");
 	        users.add(user);*/
 		Session session = HibernateUtil.getSession();
 	  return SUCCESS;
 	}
 	  
	
 	public String getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}
	public List<IExchangeRate> getExchangeRateManuals() {
		return exchangeRateManuals;
	}
	public void setExchangeRateManuals(List<IExchangeRate> exchangeRateManuals) {
		this.exchangeRateManuals = exchangeRateManuals;
	}
	public ExchangeRateManual getExchangeRateManual() {
		return exchangeRateManual;
	}
	public void setExchangeRateManual(ExchangeRateManual exchangeRateManual) {
		this.exchangeRateManual = exchangeRateManual;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return exchangeRateManual;
	}

	
	
}
