package com.esynergy.erm.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 








import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.dao.ExchangeRateManualDAO;
import com.esynergy.erm.dao.ExchangeRateDetailDAO;
import com.esynergy.erm.dao.FileUploadDAO;
import com.esynergy.erm.dao.jdbc.ExchangeRateByManualJdbc;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateManual;
 

@Service("exchangeRateManualService")
public class ExchangeRateManualServiceImpl implements ExchangeRateManualService {
	@Autowired private ExchangeRateManualDAO exchangeRateManualDAO;
	@Autowired private ExchangeRateDetailDAO exchangeRateManualDetailDAO;
 	@Autowired private ExchangeRateByManualJdbc exchangeRateByManualJdbc ;
	@Autowired private FileUploadDAO fileUploadERManualDAO;
 	
	public ExchangeRateManual save(ExchangeRateManual o) {
		   o.setLastUpdateDate(new Date());
		   if(o.getId()==0) {
			   o.setCreatedDate(new Date());
			   o.setCreatedUser(o.getLastUpdateUser());
		 	   exchangeRateManualDAO.insertExchangeRateManual(o);
		   }else{
			   exchangeRateManualDAO.updateExchangeRateManual(o);   
		   }
		 
		 return o;   
	}

	public List<Long> getExchangeRateManualByRateDateAndCurrencyForDupChk(Object[] parm) {
		 
		return exchangeRateByManualJdbc.getExchangeRateManualByRateDateAndCurrencyForDupCkl(parm);
	}

	public ExchangeRateManual getById(long id) {
		return exchangeRateManualDAO.getExchangeRateManualById(id);
	}

	public List<String> listAllUserUpdate() {
		return exchangeRateByManualJdbc.listAllUserUpdate();
	}

	public List<IExchangeRate> listExchangeRateManualByLastUpdateUser(String arg) {
		return exchangeRateManualDAO.listExchangeRateManualByLastUpdateUser(arg);
	}

	@Override
	public List<IExchangeRate> listExchangeRateManualByRateDate(
			Date strDate, Date endDate) {
		return exchangeRateManualDAO.listExchangeRateManualByRateDate(strDate, endDate);
	}

	@Override
	public List<IExchangeRate> listExchangeRateManualByRateDateAndLastUpdateUser(
			Date strDate, Date endDate, String lastUpdateUser) {
		return exchangeRateManualDAO.listExchangeRateManualByRateDateAndLastUpdateUser(strDate, endDate, lastUpdateUser);
	}

	@Override
	public void remove(ExchangeRateManual o) {
		exchangeRateManualDAO.deleteExchangeRateManual(o);
		
	}
	public List<IExchangeRate> listExchangeRateManualByRateDate(Date date) {
		List<IExchangeRate> reVal = exchangeRateManualDAO.listExchangeRateManualByRateDate(date);
		for(IExchangeRate item : reVal) {
			item.setOwnerType("MANUAL");
		}
		
		return reVal;
	}
	public List<IExchangeRate> search(String dateStratStr, String dateEndStr,String userUpdate){
		  
		try {
			Date dateStrat = UIUtil.isEmptyOrNull(dateStratStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateStratStr+" 00:00:00");
			Date dateEnd = UIUtil.isEmptyOrNull(dateEndStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateEndStr+" 23:59:59");
			
			List<IExchangeRate> reVal = exchangeRateManualDAO.search(dateStrat, dateEnd, userUpdate);
			for(IExchangeRate item : reVal) {
				item.setOwnerType("MANUAL");
			}
			
			return  reVal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<IExchangeRate> search(String dateStratStr, String dateEndStr,long baseCurrencyID, long pairCurrencyID){
		  
		try {
			Date dateStrat = UIUtil.isEmptyOrNull(dateStratStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateStratStr+" 00:00:00");
			Date dateEnd = UIUtil.isEmptyOrNull(dateEndStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateEndStr+" 23:59:59");
			
			List<IExchangeRate> reVal = exchangeRateManualDAO.search(dateStrat, dateEnd, baseCurrencyID, pairCurrencyID);
			for(IExchangeRate item : reVal) {
				item.setOwnerType("MANUAL");
			}
			
			return  reVal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
