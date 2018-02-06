package com.esynergy.erm.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.dao.jdbc.ExchangeRateDashboardJdbc;
import com.esynergy.erm.model.ob.Chart;
import com.esynergy.erm.model.ob.CurrencyPairs;

@Service("exchangeRateDashboardService")
public class ExchangeRateDashboardServiceImpl implements ExchangeRateDashboardService,ICommonContains{
	@Autowired ExchangeRateDashboardJdbc exchangeRateDashboardJdbc;
	
 
	public Chart getChartERManualByAddDateSystemDate() {
		String currentDate = DATE_FORMAT.format(new Date());
		
		Object[] parm = new Object[1];
//		parm[0] = SYSTEM_DATE_TXT;
		parm[0] = currentDate;
		Chart chart = new Chart();
		chart.setSuccess(exchangeRateDashboardJdbc.getCountSuccessExchangeRateManualByAddDate(parm));
		parm = new Object[3]; 
//		parm[0] = SYSTEM_DATE_TXT;
		parm[0] = currentDate;
//		parm[1] = SYSTEM_DATE_TXT;
		parm[1] = currentDate;
//		parm[2] = SYSTEM_DATE_TXT;
		parm[2] = currentDate;
		List<CurrencyPairs> manualRemain = exchangeRateDashboardJdbc.getRemainingExchangeRateManualByAddDate(parm);
		chart.setRemaining(manualRemain!=null&&manualRemain.size()>0?manualRemain.size():0);
		return chart;
	}

 
	public Chart getChartERAutoByAddDateSystemDate() {
		String currentDate = DATE_FORMAT.format(new Date());

		Object[] parm = new Object[1];
//		parm[0] = SYSTEM_DATE_TXT;
		parm[0] = currentDate;
		Chart chart = new Chart();
		chart.setSuccess(exchangeRateDashboardJdbc.getCountSuccessExchangeRateAutoByAddDate(parm));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date dateExtraction=cal.getTime(); 
		List<Long> fails = exchangeRateDashboardJdbc.getExtractionLogHistIdFailByDate(dateExtraction);
		if(fails!=null & fails.size()>0){
			chart.setFail(fails.size());
		}else{
			chart.setFail(0);
		}
		chart.setRemaining(exchangeRateDashboardJdbc.getCountRemainingExchangeRateAutoByAddDate());
		return chart;
	}
 
	

}
