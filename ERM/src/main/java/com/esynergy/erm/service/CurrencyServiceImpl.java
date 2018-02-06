package com.esynergy.erm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.CurrencyDAO;
import com.esynergy.erm.model.ob.Currency;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {
	
	@Autowired
	private CurrencyDAO currencyService;

	public List<Currency> listAll() {
		return currencyService.listAllCurrency();
	}
	
	public Map<Long, Currency> listAllMap(){
		Map<Long, Currency> returnMap = new HashMap<Long, Currency>();
		List<Currency> curList = currencyService.listAllCurrency();
		if(curList != null) {
			for(Currency item : curList) {
				returnMap.put(item.getId(), item);
			}
		}
		return returnMap;
	}

	public Currency getById(long id) {
		return currencyService.getCurrencyById(id);
	}	
}
