package com.esynergy.erm.model;

import java.util.Date;

import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.model.ob.Country;

public interface IUser {
	public long getId();
	public void setId(long id);
	public String getLogOnId();
	public void setLogOnId(String logOnId);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getPwd();
	public void setPwd(String pwd);
	public String getEmailAddress();
	public Country getCountry();
	public AuthorizeGroup getAuthorizeGroup();
	public void setEmailAddress(String emailAddress);
	public void setCountry(Country country);
	public void setAuthorizeGroup(AuthorizeGroup authorizeGroup);
	public String getCreatedUser();
	public String getLastUpdateUser();
	public Date getCreatedDate();
	public Date getLastUpdateDate();
	public void setCreatedUser(String createdUser);
	public void setLastUpdateUser(String lastUpdateUser);
	public void setCreatedDate(Date createdDate);
	public void setLastUpdateDate(Date lastUpdateDate);
	public String getRecordStatus();
	public void setRecordStatus(String recordStatus);
}
