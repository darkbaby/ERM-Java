package com.esynergy.erm.model.form;

import java.util.ArrayList;
import java.util.List;

public class GenerateRateForm {

	private long id;
	private String profileName;
	private List<Long> detailFormRemoveList = new ArrayList<Long>();
	private List<GenerateRateTimeForm> timeFormList = new ArrayList<GenerateRateTimeForm>();
	private List<GenerateRateDetailForm> detailFormList = new ArrayList<GenerateRateDetailForm>();
	private List<GenerateRateFileForm> fileFormList = new ArrayList<GenerateRateFileForm>();
	
	private GenerateRateSearchForm generateRateSearchForm = new GenerateRateSearchForm();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public List<GenerateRateTimeForm> getTimeFormList() {
		return timeFormList;
	}
	public void setTimeFormList(List<GenerateRateTimeForm> timeFormList) {
		this.timeFormList = timeFormList;
	}
	public void addTimeFormList(GenerateRateTimeForm timeForm) {
		this.timeFormList.add(timeForm);
	}
	public List<GenerateRateDetailForm> getDetailFormList() {
		return detailFormList;
	}
	public void setDetailFormList(List<GenerateRateDetailForm> detailFormList) {
		this.detailFormList = detailFormList;
	}
	public void addDetailFormList(GenerateRateDetailForm detailForm) {
		this.detailFormList.add(detailForm);
	}
	public List<GenerateRateFileForm> getFileFormList() {
		return fileFormList;
	}
	public void setFileFormList(List<GenerateRateFileForm> fileFormList) {
		this.fileFormList = fileFormList;
	}
	public void addFileFormList(GenerateRateFileForm fileForm) {
		this.fileFormList.add(fileForm);
	}
	public GenerateRateSearchForm getGenerateRateSearchForm() {
		return generateRateSearchForm;
	}
	public void setGenerateRateSearchForm(GenerateRateSearchForm generateRateSearchForm) {
		this.generateRateSearchForm = generateRateSearchForm;
	}
	public List<Long> getDetailFormRemoveList() {
		return detailFormRemoveList;
	}
	public void setDetailFormRemoveList(List<Long> detailFormRemoveList) {
		this.detailFormRemoveList = detailFormRemoveList;
	}
	
	
}