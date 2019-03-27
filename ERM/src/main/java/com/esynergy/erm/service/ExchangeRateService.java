package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.CurrencyPairs;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateVeiwView;

public interface ExchangeRateService {
	public List<ExchangeRateVeiwView> getByDate(Date date);
	public IExchangeRate getById(long id);
	public List<ExchangeRateVeiwView> search(long pairCurrId,long baseCurrId,long countryOfBankId,String type,Date date);
	public List<CurrencyPairs> getRemainByDate(Date date);
}
