package com.esynergy.erm.model.ob;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GenerateRate {
	
	private long id;
	private String fileName;
	private String status;
	private Set<GenerateRateDetail> generateRateDetails = new HashSet<GenerateRateDetail>();
	private Set<GenerateRateTime> generateRateTimes = new HashSet<GenerateRateTime>();
	private Set<GenerateRateFile> generateRateFiles = new HashSet<GenerateRateFile>();
	private String addUser;
	private String changeUser;
	private Date addDate;
	private Date changeDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public Set<GenerateRateDetail> getGenerateRateDetails() {
		return generateRateDetails;
	}
	public void setGenerateRateDetails(Set<GenerateRateDetail> generateRateDetails) {
		this.generateRateDetails = generateRateDetails;
	}
	public Set<GenerateRateTime> getGenerateRateTimes() {
		return generateRateTimes;
	}
	public void setGenerateRateTimes(Set<GenerateRateTime> generateRateTimes) {
		this.generateRateTimes = generateRateTimes;
	}
	public Set<GenerateRateFile> getGenerateRateFiles() {
		return generateRateFiles;
	}
	public void setGenerateRateFiles(Set<GenerateRateFile> generateRateFiles) {
		this.generateRateFiles = generateRateFiles;
	}
}
