package com.esynergy.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.ExtractionDetailDAO;
import com.esynergy.erm.model.ob.ExtractionDetail;


@Service("extractionDetailService")
public class ExtractionDetailServiceImpl implements ExtractionDetailService {

	@Autowired
	private ExtractionDetailDAO ExtractionDetailDAO;
	
	@Override
	public void delete(ExtractionDetail o) {
		ExtractionDetailDAO.delete(o);
	}

}
