package com.esynergy.erm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.ExchangeRateDetailDAO;
import com.esynergy.erm.model.ob.ExchangeRateDetail;

@Service("exchangeRateManualDetailService")
public class ExchangeRateDetailServiceImpl implements ExchangeRateDetailService {
	@Autowired  
	private ExchangeRateDetailDAO exchangeRateDetailDAO;
	
	public void delete(ExchangeRateDetail o) {
		exchangeRateDetailDAO.deleteExchangeRateDetail(o);
	}
   
	public long getCountByDate(Date date) {
		return exchangeRateDetailDAO.getCountByDate(date);
	}
}
