package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.ManualTargetDAO;
import com.esynergy.erm.model.ob.ManualTarget;

@Service("manualTargetService")
public class ManualTargetServiceImpl implements ManualTargetService {

	@Autowired
	private ManualTargetDAO manualTargetDAO;
	
	@Override
	public List<ManualTarget> listAllNotExpire() {
		
		return manualTargetDAO.listAllNotExpire();
	}
	
	@Override
	public List<ManualTarget> listAllActiveNotExpire() {
		
		return manualTargetDAO.listAllActiveNotExpire();
	}

	@Override
	public ManualTarget getByID(long id) {
		return manualTargetDAO.getByID(id);
	}

	@Override
	public List<ManualTarget> getByOwnerID(long id) {
		return manualTargetDAO.getByOwnerID(id);
	}

	@Override
	public List<ManualTarget> searchByParam(long baseCurrencyID, long pairCurrencyID) {

		return manualTargetDAO.searchByParam(baseCurrencyID, pairCurrencyID);
	}
	
	public void save(ManualTarget o) {
		manualTargetDAO.saveManualTarget(o);
	}

	@Override
	public void update(ManualTarget o) {
		manualTargetDAO.updateManualTarget(o);
		
	}
}
