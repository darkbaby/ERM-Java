package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.CodeValue;

public interface CodeValueService {

	public List<CodeValue> listAllExtractionTimeField();
	public List<CodeValue> listAllGenerateRateTimeField();
	public List<CodeValue> listAllExtractionType();
	public List<CodeValue> listAllExtractionDate();
	public List<CodeValue> listAllPageType();
	public List<CodeValue> listAllCurrencyType();
	public List<CodeValue> listAllStatus();
	public List<CodeValue> listAllFormatDate();
	public List<CodeValue> listAllValue();
	public CodeValue getPythonPath();
	public CodeValue getManualPath();

}
