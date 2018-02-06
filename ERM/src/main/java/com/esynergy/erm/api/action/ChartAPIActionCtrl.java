package com.esynergy.erm.api.action;

 
 

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.ob.Chart;
import com.esynergy.erm.service.ExchangeRateDashboardService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("chartAPIActionCtrl")
@Scope("prototype")
public class ChartAPIActionCtrl extends ActionSupport {
	private static final Logger logger = Logger.getLogger(ChartAPIActionCtrl.class);
 
	private  Chart  charts;
	private String parm;
	
	@Autowired ExchangeRateDashboardService exchangeRateDashboardService;
	
 	public String getErmByAuto() {
		charts = exchangeRateDashboardService.getChartERAutoByAddDateSystemDate();
		return SUCCESS;
	}
	public String getErmByManual() {
		 charts = exchangeRateDashboardService.getChartERManualByAddDateSystemDate();
		return SUCCESS;
	}
 
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}


	public Chart getCharts() {
		return charts;
	}


	public void setCharts(Chart charts) {
		this.charts = charts;
	} 

}
