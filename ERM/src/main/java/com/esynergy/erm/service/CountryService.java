package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.Country;

public interface CountryService {
	public List<Country> listAll();
	public Country getById(long id);
}
