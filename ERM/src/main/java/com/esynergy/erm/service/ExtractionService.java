package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;

import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.ExtractionDetail;
import com.esynergy.erm.model.ob.Extraction;

public interface ExtractionService {
	public Extraction save(Extraction o);
	public Extraction update(Extraction o);
	public void delete(Extraction o);
	public List<Extraction> listAll(); 
	public List<Extraction> listAllActive(); 
	public List<Extraction> search(String bankName,long countryId,long TypeOfSetting,String status);
	public Extraction getById(long id);
	public List<Extraction> listFailByDate(Date date);
	public boolean isExistURL(String URL);
}
