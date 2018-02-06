package com.esynergy.erm.api.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("exchangeRateManualAPIActionCtrl")
@Scope("prototype")
public class ExchangeRateManualAPIActionCtrl extends ActionSupport {
	private static final Logger logger = Logger.getLogger(ExchangeRateManualAPIActionCtrl.class);
	
	@Autowired
	private ExchangeRateManualService exchangeRateManualService;
	
	
	@Autowired
	private CurrencyService currencyService;
	
	private ExchangeRateManual exchangeRateManual;
	private List<ExchangeRateManual> exchangeRateManuals;

	ExchangeRateManualAPIActionCtrl(){
		logger.debug("Started! ExchangeRateAPIActionCtrl >> ");
	}
	
	public ExchangeRateManual getExchangeRateManual() {
		return exchangeRateManual;
	}
	public void setExchangeRateManual(ExchangeRateManual exchangeRateManual) {
		this.exchangeRateManual = exchangeRateManual;
	}
	public List<ExchangeRateManual> getExchangeRateManuals() {
		return exchangeRateManuals;
	}
	public void setExchangeRateManuals(List<ExchangeRateManual> exchangeRateManuals) {
		this.exchangeRateManuals = exchangeRateManuals;
	}
 
	 

}
