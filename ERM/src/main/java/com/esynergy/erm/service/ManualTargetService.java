package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.ManualTarget;

public interface ManualTargetService {

	public List<ManualTarget> listAllNotExpire();
	
	public List<ManualTarget> listAllActiveNotExpire();
	
	public ManualTarget getByID(long id);
	
	public List<ManualTarget> getByOwnerID(long id);

	public List<ManualTarget> searchByParam(long baseCurrencyID, long pairCurrencyID);
	
	public void save(ManualTarget o);
	
	public void update(ManualTarget o);
}
