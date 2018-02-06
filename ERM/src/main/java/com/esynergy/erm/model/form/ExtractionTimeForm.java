package com.esynergy.erm.model.form;

public class ExtractionTimeForm {

	private long id;
	private String extractionTime;
	private String extractionTimeLabel;
	private String chk;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExtractionTime() {
		return extractionTime;
	}

	public void setExtractionTime(String extractionTime) {
		this.extractionTime = extractionTime;
	}
	public String getExtractionTimeLabel() {
		return extractionTimeLabel;
	}

	public void setExtractionTimeLabel(String extractionTimeLabel) {
		this.extractionTimeLabel = extractionTimeLabel;
	}
	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}
	
}
