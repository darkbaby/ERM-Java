package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
 
public interface ExchangeRateAutoDAO {
	public ExchangeRateAuto getById(long id);
	public void saveExchangeRateAuto(ExchangeRateAuto o);
	public void updateExchangeRateAuto(ExchangeRateAuto o);
	public List<IExchangeRate> listByDate(Date date);
	public List<IExchangeRate> search(Date startDate, Date endDate,
			String bankName, long baseCurrencyId, long pairCurrencyId);
//	public List<IExchangeRate> search(long pairCurrencyId, long baseCurrencyId,
//			long countryOfBankId);
	public ExchangeRateAuto getByBankId(long id);
}
