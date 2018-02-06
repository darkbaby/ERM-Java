package com.esynergy.erm.model;

import java.io.File;

public interface IFileUpload {
	public long getId();
	public void setId(long id);
	public String getUrl();
	public void setUrl(String url);
	public String getName();
	public void setName(String name);
	public File getFile();
	public void setFile(File file);
}
