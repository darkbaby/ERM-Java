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
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("countryAPIActionCtrl")
@Scope("prototype")
public class CountryAPIActionCtrl extends ActionSupport {
	private static final Logger logger = Logger.getLogger(CountryAPIActionCtrl.class);
	
 
	@Autowired
	private CountryService countryService;
	private Country country;
	private String parm;

	CountryAPIActionCtrl(){
		logger.debug("Started! CountryAPIActionCtrl >> ");
	}
	public String getById() {
		logger.debug("-------listAll-------");
		long id = Long.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		country = countryService.getById(id);
		return SUCCESS;
	}
	 
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
