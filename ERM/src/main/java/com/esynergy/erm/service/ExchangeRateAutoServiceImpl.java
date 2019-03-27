package com.esynergy.erm.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.dao.ExchangeRateAutoDAO;
import com.esynergy.erm.dao.ExchangeRateDetailDAO;
import com.esynergy.erm.dao.ExtractionDAO;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.Extraction;
 

@Service("exchangeRateAutoService")
public class ExchangeRateAutoServiceImpl implements ExchangeRateAutoService{
	@Autowired ExchangeRateAutoDAO exchangeRateAutoDAO;
	@Autowired ExchangeRateDetailDAO exchangeRateDetailDAO;
    @Autowired ExtractionDAO extractionDAO;

	@Override
	public ExchangeRateAuto getById(long id) {
		return exchangeRateAutoDAO.getById(id);
	}

	@Override
	public void save(ExchangeRateAuto o) {
		o.setLastUpdateDate(new Date());
		 if(o.getId()==0){
			 o.setCreatedDate(new Date());
			 exchangeRateAutoDAO.saveExchangeRateAuto(o);
		 }else{
			 exchangeRateDetailDAO.saveExchangeRateAutoDetailList(o);
//			 exchangeRateAutoDAO.updateExchangeRateAuto(o);
		 }
	}

	@Override
	public List<IExchangeRate> listByDate(Date date) {
		List<IExchangeRate> reVal = exchangeRateAutoDAO.listByDate(date);
		for(IExchangeRate item : reVal) {
			item.setOwnerType("AUTO");
		}
		return reVal;
	}

	@Override
	public List<IExchangeRate> search(String dateStartStr, String dateEndStr,
			String bankName, long baseCID, long pairCID) {
		try {
			Date dateStart =UIUtil.isEmptyOrNull(dateStartStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateStartStr+" 00:00:00");
			Date dateEnd = UIUtil.isEmptyOrNull(dateEndStr)?null:ICommonContains.DATE_FORMAT_ORACLE.parse(dateEndStr+" 23:59:59");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateStart);
			dateStart = cal.getTime();
			cal.setTime(dateEnd);
			dateEnd = cal.getTime();
			
			List<IExchangeRate> reVal = exchangeRateAutoDAO.search(dateStart, dateEnd, bankName, baseCID, pairCID);
			for(IExchangeRate item : reVal) {
				item.setOwnerType("AUTO");
			}
			
			return reVal;
		
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ExchangeRateAuto getByExtractionId(long id){
		Extraction  extraction = extractionDAO.getExtractionById(id);
		long bankId = extraction.getBank()!=null?extraction.getBank().getId():0;
		return exchangeRateAutoDAO.getByBankId(bankId);
	}

	 
}
