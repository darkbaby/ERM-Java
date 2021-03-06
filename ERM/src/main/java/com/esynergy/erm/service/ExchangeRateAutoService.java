package com.esynergy.erm.service;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
 
public interface ExchangeRateAutoService {
	public ExchangeRateAuto getById(long id);
	public void save(ExchangeRateAuto o);
	public List<IExchangeRate> listByDate(Date date);
	public List<IExchangeRate> search(String dateStartStr, String dateEndStr,
			String bankName, long baseCurrencyId, long pairCurrencyId ) ;
	public ExchangeRateAuto getByExtractionId(long id);
 
}
