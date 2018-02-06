package com.esynergy.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.ExtractionTimeDAO;
import com.esynergy.erm.model.ob.ExtractionTime;

@Service("extractionTimeService")
public class ExtractionTimeServiceImpl implements ExtractionTimeService {

	@Autowired
	private ExtractionTimeDAO extractionTimeDao;
	
	@Override
	public void delete(ExtractionTime o) {
		extractionTimeDao.delete(o);
	}

}
