package com.esynergy.erm.model.ob;

import java.util.Date;

public class GenerateRateFile {
	private long id;
	private String url;
	private String name;
	private Date addDate;
	private GenerateRate generateRate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public GenerateRate getGenerateRate() {
		return generateRate;
	}
	public void setGenerateRate(GenerateRate generateRate) {
		this.generateRate = generateRate;
	}
}
