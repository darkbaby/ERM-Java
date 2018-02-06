package com.esynergy.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.GenerateRateTimeDAO;
import com.esynergy.erm.model.ob.GenerateRateTime;

@Service("generateRateTimeService")
public class GenerateRateTimeServiceImpl implements GenerateRateTimeService {

	@Autowired
	private GenerateRateTimeDAO generateRateTimeDAO;
	
	@Override
	public void delete(GenerateRateTime o) {
		generateRateTimeDAO.deleteGenerateRateTime(o);
	}

}
