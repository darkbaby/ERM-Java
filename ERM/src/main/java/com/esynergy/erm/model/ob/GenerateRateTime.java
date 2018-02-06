package com.esynergy.erm.model.ob;

public class GenerateRateTime {
	
	private long id;
	private GenerateRate generateRate;
	private long generateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public GenerateRate getGenerateRate() {
		return generateRate;
	}
	public void setGenerateRate(GenerateRate generateRate) {
		this.generateRate = generateRate;
	}
	public long getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(long generateTime) {
		this.generateTime = generateTime;
	}
	
}
