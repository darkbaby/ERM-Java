package com.esynergy.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.GenerateRateDetailDAO;
import com.esynergy.erm.model.ob.GenerateRateDetail;

@Service("generateRateDetailService")
public class GenerateRateDetailServiceImpl implements GenerateRateDetailService {

	@Autowired
	GenerateRateDetailDAO generateRateDetailDAO;
	
	@Override
	public void delete(GenerateRateDetail o) {
		generateRateDetailDAO.delete(o);
		
	}
	
}
