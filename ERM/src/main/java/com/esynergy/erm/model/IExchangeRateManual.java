package com.esynergy.erm.model;

import com.esynergy.erm.model.ob.ExchangeRateManual;

public interface IExchangeRateManual extends IFileUpload {
	public ExchangeRateManual getExchangeRateManual();
	public void setExchangeRateManual(ExchangeRateManual exchangeRateManual);
}
