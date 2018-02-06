package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.ManualTarget;

@Repository("manualTargetDAO")
public class ManualTargetDAOImpl extends AbstractHiberbateDAO<Integer,ManualTarget> implements ManualTargetDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ManualTarget> listAll() {
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.status", "A"));
		return criteria.list();
	}
	
	
	
}
