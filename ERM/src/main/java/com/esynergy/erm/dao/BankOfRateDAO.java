package com.esynergy.erm.dao;

import com.esynergy.erm.model.ob.BankOfRate;

public interface BankOfRateDAO {
	public BankOfRate getByExchangeRateHdrId(long id);
}
