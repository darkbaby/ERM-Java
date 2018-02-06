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
	public List<ManualTarget> listAll() {
		
		return manualTargetDAO.listAll();
	}
	
	
	
}
