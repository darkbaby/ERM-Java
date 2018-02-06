package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.ExtractionDAO;
import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.ExtractionDetail;
import com.esynergy.erm.model.ob.Extraction;

@Service("extractionService")
public class ExtractionServiceImpl implements ExtractionService {
	
	@Autowired 
	private ExtractionDAO extractionDAO;
	
	public Extraction save(Extraction o) {
		extractionDAO.insertExtraction(o);
		return o;
	}

	@Override
	public List<Extraction> listAll() {
		return extractionDAO.listAllExtraction();
	}
	
	@Override
	public List<Extraction> listAllActive() {
		return extractionDAO.listAllExtractionActive();
	}

	@Override
	public Extraction update(Extraction o) {
		extractionDAO.updateExtraction(o);
		return o;
	}
	
	@Override
	public void delete(Extraction o) {
//		extractionDAO.deleteExtraction(o);
	}

	@Override
	public List<Extraction> search(String bankName, long countryId,
			long TypeOfSetting, String status) {
		return extractionDAO.search(bankName, countryId, TypeOfSetting, status);
	}

	@Override
	public Extraction getById(long id) {
		return extractionDAO.getExtractionById(id);
	}

	@Override
	public boolean isExistURL(String url) {
	
		List<Extraction> extractionList = extractionDAO.listAllExtractionByURL(url);
		if(extractionList.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Extraction> listFailByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}	
}
