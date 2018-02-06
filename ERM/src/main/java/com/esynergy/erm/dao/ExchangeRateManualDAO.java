package com.esynergy.erm.dao;
import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateManual;
public interface ExchangeRateManualDAO {
	public ExchangeRateManual insertExchangeRateManual(ExchangeRateManual o);
	public ExchangeRateManual updateExchangeRateManual(ExchangeRateManual o);
	public void saveExchangeRateManual(ExchangeRateManual o);
	public ExchangeRateManual getExchangeRateManualById(long id);
	public List<IExchangeRate> listAllExchangeRateManual();
	public List<IExchangeRate> listExchangeRateManualByLastUpdateUser(String arg);
	public void deleteExchangeRateManual(ExchangeRateManual o);
    public List<IExchangeRate> listExchangeRateManualByRateDate(Date strDate,Date endDate);
    public List<IExchangeRate> listExchangeRateManualByRateDate(Date date);
    public List<IExchangeRate> listExchangeRateManualByRateDateAndLastUpdateUser(Date strDate,Date endDate,String lastUpdateUser);
    public List<IExchangeRate> search(long pairCurrencyId,long baseCurrencyId);
    public List<IExchangeRate> search(Date strDate, Date endDate,String userUpdate);
}
