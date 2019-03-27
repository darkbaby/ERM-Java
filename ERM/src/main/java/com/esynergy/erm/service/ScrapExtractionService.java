package com.esynergy.erm.service;

import java.util.List;

public interface ScrapExtractionService {

//	public void initialScrapExtraction();
//	public void initialScrapExtraction(String pd);
//	public void createInputFile(List<String> descriptionCssSelectors);
	public void callPythonPhantomJS(List<String> params);
//	public void readOutputFile();
//	public String getFieldValue(int line, int position);
//	public String getErrorFromFile();
	public boolean isRunning();
//	public void deleteOutputFile();
	
}
