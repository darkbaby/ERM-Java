package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.web.action.IPageContains;

@Repository("authorizeGroupDAO")
public class AuthorizeGroupDAOImpl extends AbstractHiberbateDAO<Integer, AuthorizeGroup> implements AuthorizeGroupDAO {

	@SuppressWarnings("unchecked")
	public List<AuthorizeGroup> listAll() {
		DetachedCriteria criteria = super.createDetachedCriteria();
		criteria.add(Restrictions.eq("status", IPageContains.RECORD_STS_ACTIVE));
		return super.executeDetachedCriteria(criteria);
	}

	@Override
	public AuthorizeGroup getById(long id) {
		return super.getByKey(id);
	}

 
	 
}
