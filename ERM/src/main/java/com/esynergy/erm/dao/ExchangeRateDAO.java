package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRate;

public interface ExchangeRateDAO {
	public List<ExchangeRate> getByRateDate(Date date);
	public IExchangeRate getbyId(long id);
	public List<IExchangeRate> search(long pairCurrencyId,long baseCurrencyId,String type );
}
