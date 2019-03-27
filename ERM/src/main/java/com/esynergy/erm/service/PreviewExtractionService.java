package com.esynergy.erm.service;

import java.util.List;

public interface PreviewExtractionService {

	public void initialProcessBuilder();
	public void setPythonDirectory(String pd);
	public void createInputFile(List<String> descriptionCssSelectors);
	public void callPythonPhantomJS();
	public void readOutputFile();
	public String getFieldValue(int line, int position);
	public String getErrorFromFile();
	public boolean getIsSuccessful();
	public void deleteOutputFile();
	
}
