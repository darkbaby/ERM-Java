package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.GenerateRate;

public interface GenerateRateService {

	public GenerateRate save(GenerateRate o);
	public GenerateRate update(GenerateRate o);
	public void remove(GenerateRate o);
	public GenerateRate getByID(long id);
	public List<GenerateRate> listAll();
}
