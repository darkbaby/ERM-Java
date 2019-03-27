package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.GenerateRate;

@Repository("generateRateDAO")
public class GenerateRateDAOImpl extends AbstractHiberbateDAO<Integer, GenerateRate> implements GenerateRateDAO {

	@Override
	public GenerateRate saveGenerateRate(GenerateRate o) {
		return super.save(o);
	}
	
	@Override
	public GenerateRate updateGenerateRate(GenerateRate o) {
		return super.update(o);
	}

	@Override
	public GenerateRate getByID(long id) {
		return super.getByKey(id);
	}
	
	public List<GenerateRate> listAll() {
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);

	}

	@Override
	public void removeGenerateRate(GenerateRate o) {
		super.delete(o);
	}
	
}
