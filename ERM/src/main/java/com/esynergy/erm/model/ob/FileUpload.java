package com.esynergy.erm.model.ob;

import java.io.File;

import com.esynergy.erm.model.IFileUpload;

public class FileUpload implements IFileUpload {
	private long id;
	private String url;
	private String name;
	private File file;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
