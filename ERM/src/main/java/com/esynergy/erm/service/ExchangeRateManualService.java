package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;
 





import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateManual;

public interface ExchangeRateManualService {
	public ExchangeRateManual save(ExchangeRateManual o);
	public void remove(ExchangeRateManual o);
	public List<Long> getExchangeRateManualByRateDateAndCurrencyForDupChk(Object[] parm);
	public ExchangeRateManual getById(long id);
    public List<String> listAllUserUpdate();
    public List<IExchangeRate> listExchangeRateManualByLastUpdateUser(String arg);
    public List<IExchangeRate> listExchangeRateManualByRateDate(Date strDate,Date endDate);
    public List<IExchangeRate> listExchangeRateManualByRateDateAndLastUpdateUser(Date strDate,Date endDate,String lastUpdateUser);
    public List<IExchangeRate> listExchangeRateManualByRateDate(Date date);
    public List<IExchangeRate> search(String dateStratStr, String dateEndStr,String userUpdate);
}
