package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.ob.Extraction;

public interface ExtractionDAO {
	public Extraction insertExtraction(Extraction o);
	public Extraction updateExtraction(Extraction o);
	public void deleteExtraction(Extraction o);
	public List<Extraction> listAllExtraction();
	public List<Extraction> listAllExtractionActive();
	public List<Extraction> search(String bankName,long countryId,long TypeOfSetting,String status);
	public Extraction getExtractionById(long id);
	public List<Extraction> listAllExtractionByURL(String url);
	public List<Extraction> listFailByDate(Date date);
}

