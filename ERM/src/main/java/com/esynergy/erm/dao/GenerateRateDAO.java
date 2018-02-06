package com.esynergy.erm.dao;

import java.util.List;

import com.esynergy.erm.model.ob.GenerateRate;

public interface GenerateRateDAO {

	public GenerateRate saveGenerateRate(GenerateRate o);
	public GenerateRate updateGenerateRate(GenerateRate o);
	public void removeGenerateRate(GenerateRate o);
	public GenerateRate getByID(long id);
	public List<GenerateRate> listAll();
}
