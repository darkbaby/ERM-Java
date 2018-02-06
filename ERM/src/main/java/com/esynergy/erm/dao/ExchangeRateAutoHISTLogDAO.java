package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;

public interface ExchangeRateAutoHISTLogDAO {
	public ExchangeRateAutoHISTLog getById(long id);
	public List<ExchangeRateAutoHISTLog> getByDate(Date date);
	public List<ExchangeRateAutoHISTLog> search(Date dateStrat,Date dateEnd,String bankName,String logStatust);
	public List<ExchangeRateAutoHISTLog> search(Date dateStrat,Date dateEnd,String userUpdate);
}
