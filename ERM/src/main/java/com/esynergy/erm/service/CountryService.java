package com.esynergy.erm.service;

import java.util.List;
import java.util.Map;

import com.esynergy.erm.model.ob.Country;

public interface CountryService {
	public List<Country> listAll();
	public Map<Long,Country> listAllMap();
	public Country getById(long id);
}
