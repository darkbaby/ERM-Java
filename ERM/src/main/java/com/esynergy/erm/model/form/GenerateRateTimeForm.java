package com.esynergy.erm.model.form;

public class GenerateRateTimeForm {

	private long id;
	private String generateTime;
	private String generateTimeLabel;
	private String chk;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public String getGenerateTimeLabel() {
		return generateTimeLabel;
	}
	public void setGenerateTimeLabel(String generateTimeLabel) {
		this.generateTimeLabel = generateTimeLabel;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
}
