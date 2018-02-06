package com.esynergy.erm.model.form;

import java.util.List;

public class ExchangeRateManualSearchForm {
	 
	private String  rateDateStart;
	private String  rateDateEnd;
	private String  updateBy;
	private List<String> userUpdateList;
	public String getRateDateStart() {
		return rateDateStart;
	}
	public void setRateDateStart(String rateDateStart) {
		this.rateDateStart = rateDateStart;
	}
	public String getRateDateEnd() {
		return rateDateEnd;
	}
	public void setRateDateEnd(String rateDateEnd) {
		this.rateDateEnd = rateDateEnd;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public List<String> getUserUpdateList() {
		return userUpdateList;
	}
	public void setUserUpdateList(List<String> userUpdateList) {
		this.userUpdateList = userUpdateList;
	}
 
}
