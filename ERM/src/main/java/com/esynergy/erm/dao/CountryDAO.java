package com.esynergy.erm.dao;

import java.util.List;

import com.esynergy.erm.model.ob.Country;

 
public interface CountryDAO {
	public List<Country> listAllCountry();
	public Country getCountryById(long id);
}
