package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.CodeValueDao;
import com.esynergy.erm.model.ob.CodeValue;

@Service("codeValueService")
public class CodeValueServiceImpl implements CodeValueService {

	@Autowired
	private CodeValueDao codeValueDao;
	
	@Override
	public List<CodeValue> listAllExtractionTimeField() {		
		return codeValueDao.listAllExtractionTimeField();
	}
	
	@Override
	public List<CodeValue> listAllGenerateRateTimeField(){
		return codeValueDao.listAllGenerateRateTimeField();
	}

	@Override
	public List<CodeValue> listAllExtractionType() {
		return codeValueDao.listAllExtractionType();

	}

	@Override
	public List<CodeValue> listAllExtractionDate() {
		return codeValueDao.listAllExtractionDate();

	}

	@Override
	public List<CodeValue> listAllPageType() {
		return codeValueDao.listAllPageType();

	}

	@Override
	public List<CodeValue> listAllCurrencyType() {
		return codeValueDao.listAllCurrencyType();

	}

	@Override
	public List<CodeValue> listAllStatus() {
		return codeValueDao.listAllStatus();

	}

	@Override
	public List<CodeValue> listAllFormatDate() {
		return codeValueDao.listAllFormatDate();

	}
	
	@Override
	public List<CodeValue> listAllValue() {
		return codeValueDao.listAllValue();

	}
	
	@Override
	public CodeValue getPythonPath() {
		return codeValueDao.getPythonPath();
	}
	
	@Override
	public CodeValue getManualPath() {
		return codeValueDao.getManualPath();
	}
}
