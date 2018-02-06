package com.esynergy.erm.service;

import java.util.List;
import java.util.Map;

import com.esynergy.erm.model.ob.Currency;

public interface CurrencyService {
	public List<Currency> listAll();
	public Map<Long, Currency> listAllMap();
	public Currency getById(long id);
}
