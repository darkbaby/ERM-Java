package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.GenerateRateDAO;
import com.esynergy.erm.model.ob.GenerateRate;

@Service("generateRateService")
public class GenerateRateServiceImpl implements GenerateRateService {

	@Autowired
	private GenerateRateDAO generateRateDAO;
	
	@Override
	public GenerateRate save(GenerateRate o) {
		return generateRateDAO.saveGenerateRate(o);
	}

	@Override
	public GenerateRate getByID(long id) {
		return generateRateDAO.getByID(id);
	}

	@Override
	public GenerateRate update(GenerateRate o) {
		return generateRateDAO.updateGenerateRate(o);

	}

	@Override
	public List<GenerateRate> listAll() {
		return generateRateDAO.listAll();
	}

	@Override
	public void remove(GenerateRate o) {
		generateRateDAO.removeGenerateRate(o);
	}

}
