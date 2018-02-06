package com.esynergy.erm.dao;

import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.GenerateRateTime;

@Repository("generateRateTimeDAO")
public class GenerateRateTimeDAOImpl extends AbstractHiberbateDAO<Integer, GenerateRateTime> implements GenerateRateTimeDAO {

	@Override
	public void deleteGenerateRateTime(GenerateRateTime o) {
		super.delete(o);
	}

}
