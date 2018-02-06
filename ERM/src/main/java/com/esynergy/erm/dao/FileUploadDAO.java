package com.esynergy.erm.dao;

import com.esynergy.erm.model.ob.FileUpload;


public interface FileUploadDAO {
	public void insertFileUpload(FileUpload o);
	public FileUpload updateFileUpload(FileUpload o);
	public void deleteFileUpload(FileUpload o);
}
