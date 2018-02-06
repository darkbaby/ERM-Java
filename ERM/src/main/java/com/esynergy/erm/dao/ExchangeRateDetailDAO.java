package com.esynergy.erm.dao;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
 

public interface ExchangeRateDetailDAO {
	public ExchangeRateDetail insertExchangeRateDetail(ExchangeRateDetail o);
	public ExchangeRateDetail updateExchangeRateDetail(ExchangeRateDetail o);
	public void deleteExchangeRateDetail(ExchangeRateDetail o);
	public ExchangeRateDetail getExchangeRateDetailById(long id);
	public List<ExchangeRateDetail> listAllExchangeRateDetail();
	public long getCountByDate(Date date);
//	public void saveExchangeRateAutoDetailList(Set<ExchangeRateDetail> o);
	public void saveExchangeRateAutoDetailList(ExchangeRate o);
	public List<ExchangeRateDetail> listByRateDate(Date date);
	public List<ExchangeRateDetail> search(long pairCurrencyId,
			long baseCurrencyId, long countryOfBankId, String type);
}
