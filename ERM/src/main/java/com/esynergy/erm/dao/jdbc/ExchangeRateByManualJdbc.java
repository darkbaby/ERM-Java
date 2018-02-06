package com.esynergy.erm.dao.jdbc;

import java.util.List;

public interface ExchangeRateByManualJdbc {
	public List<Long> getExchangeRateManualByRateDateAndCurrencyForDupCkl(Object[] parm);
	public List<String> listAllUserUpdate();
}
