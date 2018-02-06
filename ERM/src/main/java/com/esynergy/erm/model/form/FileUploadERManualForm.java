package com.esynergy.erm.model.form;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.esynergy.erm.model.IFileUpload;

public class FileUploadERManualForm  implements IFileUpload,Cloneable{
	private long id;
	private String url;
	private String name;
	private File file;
	private Map<String,Object> map = new HashMap<String,Object>();
 
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
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public void putMap(String name,Object obj){
		this.map.put(name, obj);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
