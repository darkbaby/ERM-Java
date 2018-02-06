package com.esynergy.erm.api.action;

import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("generateRateAPIActionCtrl")
@Scope("prototype")
public class GenerateRateAPIActionCtrl extends ActionSupport {

	private static final Logger logger = Logger.getLogger(GenerateRateAPIActionCtrl.class);

	private String result;
	
	private String buyingRate;
	
	private String sellingRate;
	
	private String id;
	
	public String save() {
		
		double buyingRate = 0;
		double sellingRate = 0;
		long id = 0;
		
		try {
			buyingRate = Double.parseDouble(this.buyingRate);
			sellingRate = Double.parseDouble(this.sellingRate);
			id = Long.parseLong(this.id);
			result = "0";
		}
		catch (Exception e) {
			result = "1";
		}
		
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBuyingRate() {
		return buyingRate;
	}

	public void setBuyingRate(String buyingRate) {
		this.buyingRate = buyingRate;
	}

	public String getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(String sellingRate) {
		this.sellingRate = sellingRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
