package com.esynergy.erm.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.FileUploadDAO;
import com.esynergy.erm.model.ob.FileUpload;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {
	@Autowired
	FileUploadDAO fileUploadDAO;
	
 	public void update(FileUpload o) {
 		fileUploadDAO.updateFileUpload(o);
	}
	public void delete(FileUpload o) {
		fileUploadDAO.deleteFileUpload(o);
	}
}
