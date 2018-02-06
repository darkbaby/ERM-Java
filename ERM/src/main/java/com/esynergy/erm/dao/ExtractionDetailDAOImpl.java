package com.esynergy.erm.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.ExtractionDetail;

@Repository("extractionDetailDAO")
public class ExtractionDetailDAOImpl extends AbstractHiberbateDAO<Integer, ExtractionDetail> implements ExtractionDetailDAO {

	private static final Logger logger = Logger.getLogger(ExtractionDetailDAOImpl.class); 
	private Session session;
	
	@Override
	public void delete(ExtractionDetail o) {
		// TODO Auto-generated method stub
		super.delete(o);
	}

}
