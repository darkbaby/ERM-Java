package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;

public interface ExchangeRateAutoHISTLogService {
	public List<ExchangeRateAutoHISTLog> getFailByDate(Date date);
	public List<ExchangeRateAutoHISTLog> listByDate(Date date);
	public List<ExchangeRateAutoHISTLog> search(String dateStrat,String dateEnd,String bankName,String logStatust);
	public List<ExchangeRateAutoHISTLog> search(String dateStrat,String dateEnd,String userUpdate);
}
