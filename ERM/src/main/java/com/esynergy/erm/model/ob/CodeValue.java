package com.esynergy.erm.model.ob;

public class CodeValue {
	
	private long id;
	private String codeSet;
	private String value;
	private String descriptionShort;
	private String descriptionLong;
	private long sortOrder;
	private String recordStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodeSet() {
		return codeSet;
	}
	public void setCodeSet(String codeSet) {
		this.codeSet = codeSet;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescriptionShort() {
		return descriptionShort;
	}
	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}
	public String getDescriptionLong() {
		return descriptionLong;
	}
	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}
	public long getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
}
