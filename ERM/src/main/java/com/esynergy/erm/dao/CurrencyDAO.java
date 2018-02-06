package com.esynergy.erm.dao;

import java.util.List;

import com.esynergy.erm.model.ob.Currency;

 
public interface CurrencyDAO {
	public List<Currency> listAllCurrency();
	public List<Currency> listWhitOutById(long id);
	public Currency getCurrencyById(long id);
}
