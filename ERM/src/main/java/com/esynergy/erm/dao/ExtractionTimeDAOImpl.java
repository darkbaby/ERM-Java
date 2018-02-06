package com.esynergy.erm.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.ExtractionTime;

@Repository("extractionTimeDAO")
public class ExtractionTimeDAOImpl extends AbstractHiberbateDAO<Integer, ExtractionTime> implements ExtractionTimeDAO {

	private static final Logger logger = Logger.getLogger(ExtractionTimeDAOImpl.class); 
	private Session session;
	
	public void delete(ExtractionTime o) {
		super.delete(o);
	}
	
}
