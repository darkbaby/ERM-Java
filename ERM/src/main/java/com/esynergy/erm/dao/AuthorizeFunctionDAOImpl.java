package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.AuthorizeFunction;
import com.esynergy.erm.web.action.IPageContains;

@Repository("authorizeFunctionDAO")
public class AuthorizeFunctionDAOImpl extends AbstractHiberbateDAO<Integer, AuthorizeFunction> implements AuthorizeFunctionDAO {

	@SuppressWarnings("unchecked")
	public List<AuthorizeFunction> listAll() {
		DetachedCriteria criteria = super.createDetachedCriteria();
		criteria.add(Restrictions.eq("status", IPageContains.RECORD_STS_ACTIVE));
		return super.executeDetachedCriteria(criteria);
	}

	@Override
	public AuthorizeFunction getById(long id) {
		return super.getByKey(id);
	}

 
	 
}
