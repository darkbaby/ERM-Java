package com.esynergy.erm.api.action;

 
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.service.CurrencyService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("currencyAPIActionCtrl")
@Scope("prototype")
public class CurrencyAPIActionCtrl extends ActionSupport {
	private static final Logger logger = Logger.getLogger(CurrencyAPIActionCtrl.class);
	
 
	@Autowired
	private CurrencyService currencyService;
	
	private List<Currency> currency;
	private String parm;

	CurrencyAPIActionCtrl(){
		logger.debug("Started! CurrencyAPIActionCtrl >> ");
	}
	public String listAll() {
		logger.debug("-------listAll-------");	
		currency = currencyService.listAll();
		return SUCCESS;
	}
	 
	public List<Currency> getCurrency() {
		return currency;
	}
	public void setCurrency(List<Currency> currency) {
		this.currency = currency;
	}
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
 
 
 
	 

}
