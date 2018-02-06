package com.esynergy.erm.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.dao.ExchangeRateAutoHISTLogDAO;
import com.esynergy.erm.dao.jdbc.ExchangeRateDashboardJdbc;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
 
@Service("exchangeRateAutoHISTLogService")
public class ExchangeRateAutoHISTLogServiceImpl implements ExchangeRateAutoHISTLogService {
    @Autowired private ExchangeRateAutoHISTLogDAO exchangeRateAutoHISTLogDAO;
	@Autowired private ExchangeRateDashboardJdbc exchangeRateDashboardJdbc;
	@Override
	public List<ExchangeRateAutoHISTLog> getFailByDate(Date date) {
		Date dateExtraction = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		dateExtraction=cal.getTime(); 
		List<Long> logIdFaildDailyList =  exchangeRateDashboardJdbc.getExtractionLogHistIdFailByDate(dateExtraction);
		List<ExchangeRateAutoHISTLog> logList=new ArrayList<ExchangeRateAutoHISTLog>();
		if(logIdFaildDailyList!=null && logIdFaildDailyList.size()>0){
			for(long logId:logIdFaildDailyList){
				ExchangeRateAutoHISTLog log = exchangeRateAutoHISTLogDAO.getById(logId);
				if(log!=null){
					logList.add(log);
				}
			}
		}
		return logList;
	}
	
	
	@Override
	public List<ExchangeRateAutoHISTLog> listByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
 		cal.add(Calendar.DATE, -1);
		date = cal.getTime();
		return exchangeRateAutoHISTLogDAO.getByDate(date);
	}

	public List<ExchangeRateAutoHISTLog> search(String dateStratStr,String dateEndStr,String bankName,String logStatust) {
		try {
			Date dateStrat =UIUtil.isEmptyOrNull(dateStratStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateStratStr+" 00:00:00");
			Date dateEnd = UIUtil.isEmptyOrNull(dateEndStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateEndStr+" 23:59:59");
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(dateStrat);
//			cal.add(Calendar.DATE);
			dateStrat = cal.getTime();
			
			cal.setTime(dateEnd);
//			cal.add(Calendar.DATE);
			dateEnd = cal.getTime();
			
			return exchangeRateAutoHISTLogDAO.search(dateStrat, dateEnd, bankName, logStatust);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ExchangeRateAutoHISTLog> search(String dateStratStr,
			String dateEndStr, String userUpdate) {
		try {
			Date dateStrat =UIUtil.isEmptyOrNull(dateStratStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateStratStr+" 00:00:00");
			Date dateEnd = UIUtil.isEmptyOrNull(dateEndStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateEndStr+" 23:59:59");
			
			Calendar cal = Calendar.getInstance();
			if(dateStrat!=null){
				cal.setTime(dateStrat);
				cal.add(Calendar.DATE, -1);
				dateStrat = cal.getTime();
			}
			if(dateEnd!=null){
				cal.setTime(dateEnd);
				cal.add(Calendar.DATE, -1);
				dateEnd = cal.getTime();
			}
			
			return exchangeRateAutoHISTLogDAO.search(dateStrat, dateEnd, userUpdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
