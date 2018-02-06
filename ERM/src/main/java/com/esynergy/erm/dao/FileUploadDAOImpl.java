package com.esynergy.erm.dao;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.FileUpload;
 

@Repository("fileUploadDAO")
public class FileUploadDAOImpl extends AbstractHiberbateDAO<Integer, FileUpload> implements FileUploadDAO {
	private static final Logger logger = Logger.getLogger(FileUploadDAOImpl.class); 
	public FileUpload updateFileUpload(FileUpload o) {
		logger.debug("----------updateFileUpload ID is "+o.getId());
		return super.update(o);
	}
	public void deleteFileUpload(FileUpload o) {
		logger.debug("----------deleteFileUpload ID is "+o.getId());
		super.delete(o);
	}
	public void insertFileUpload(FileUpload o) {
		logger.debug("----------insertFileUpload--------");
		super.save(o);
	}


}
