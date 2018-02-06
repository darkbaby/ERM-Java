package com.esynergy.erm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.CountryDAO;
import com.esynergy.erm.model.ob.Country;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDAO countryService;

	public List<Country> listAll() {
		return countryService.listAllCountry();
	}
	
	public Country getById(long id) {
		return countryService.getCountryById(id);
	}
		
}
