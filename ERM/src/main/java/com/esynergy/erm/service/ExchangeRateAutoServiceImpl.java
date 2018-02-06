package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return exchangeRateAutoDAO.listByDate(date);
	}

	@Override
	public List<ExchangeRateAuto> search(Date dateStart, Date dateEnd,
			String bankName, int status) {
		return null;//exchangeRateAutoDAO.search(dateStart, dateEnd, bankName, status);
	}
	
	public ExchangeRateAuto getByExtractionId(long id){
		Extraction  extraction = extractionDAO.getExtractionById(id);
		long bankId = extraction.getBank()!=null?extraction.getBank().getId():0;
		return exchangeRateAutoDAO.getByBankId(bankId);
	}

	 
}
