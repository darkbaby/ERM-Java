package com.esynergy.erm.model.ob;

import javax.persistence.Entity;

@Entity
public class ExtractionTime {
	private long id;
	private long extractionTime;
	private Extraction extraction;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getExtractionTime() {
		return extractionTime;
	}
	public void setExtractionTime(long extractionTime) {
		this.extractionTime = extractionTime;
	}
	public Extraction getExtraction() {
		return extraction;
	}
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}
	
}
