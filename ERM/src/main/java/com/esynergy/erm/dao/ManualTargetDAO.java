package com.esynergy.erm.dao;

import java.util.List;

import javax.swing.ListModel;

import org.hibernate.sql.Update;

import com.esynergy.erm.model.ob.ManualTarget;

public interface ManualTargetDAO {

	public List<ManualTarget> listAllNotExpire();
	
	public List<ManualTarget> listAllActiveNotExpire();
	
	public ManualTarget getByID(long id);
	
	public List<ManualTarget> getByOwnerID(long id);
	
	public List<ManualTarget> searchByParam(long baseCurrencyID, long pairCurrencyID);
	
	public void saveManualTarget(ManualTarget o);
	
	public void updateManualTarget(ManualTarget o);
	
}
