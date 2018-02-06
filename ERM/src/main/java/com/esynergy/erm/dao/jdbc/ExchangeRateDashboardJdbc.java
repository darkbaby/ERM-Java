package com.esynergy.erm.dao.jdbc;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.ob.CurrencyPairs;


public interface ExchangeRateDashboardJdbc {
	public long getCountSuccessExchangeRateManualByAddDate(Object[] parm);
	public long getCountFailExchangeRateManualByAddDate(Object[] parm);
	public long getCountSuccessExchangeRateAutoByAddDate(Object[] parm);
	public long getCountRemainingExchangeRateAutoByAddDate();
	public List<CurrencyPairs> getRemainingExchangeRateManualByAddDate(Object[] parm);
	public List<Long> getExtractionLogHistIdFailByDate(Date date);
	public List<Long> searchExchangerate(long pairCurrId,long baseCurrId,long countryOfBankId,String type,String status);
}
