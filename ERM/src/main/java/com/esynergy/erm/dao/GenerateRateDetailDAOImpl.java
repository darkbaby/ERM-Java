package com.esynergy.erm.dao;

import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.GenerateRateDetail;

@Repository("generateRateDetailDAO")
public class GenerateRateDetailDAOImpl extends AbstractHiberbateDAO<Integer, GenerateRateDetail> implements GenerateRateDetailDAO {

	@Override
	public void delete(GenerateRateDetail o) {
		super.delete(o);
	}

}
