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
	public List<ExchangeRateAuto> search(Date dateStart, Date dateEnd,
			String bankName, int status) ;
	public ExchangeRateAuto getByExtractionId(long id);
 
}
