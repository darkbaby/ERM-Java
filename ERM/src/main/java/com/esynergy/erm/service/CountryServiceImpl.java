package com.esynergy.erm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.CountryDAO;
import com.esynergy.erm.model.ob.Country;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDAO countryDao;

	public List<Country> listAll() {
		return countryDao.listAllCountry();
	}
	
	public Map<Long, Country> listAllMap(){
		Map<Long, Country> returnMap = new HashMap<Long, Country>();
		List<Country> counList = countryDao.listAllCountry();
		if(counList != null) {
			for(Country item : counList) {
				returnMap.put(item.getId(), item);
			}
		}
		return returnMap;
	}
	
	public Country getById(long id) {
		return countryDao.getCountryById(id);
	}
		
}
