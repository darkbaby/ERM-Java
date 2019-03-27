package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.Bank;

@Repository("bankDAO")
public class BankDAOImpl extends AbstractHiberbateDAO<Integer,Bank> implements BankDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getBankWithName(String name) {

//		Criteria cri = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		Criterion res1 = Restrictions.eq("a.bankName", name);
		criteria.add(res1);
//		return cri.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getBankWithShortName(String shortName) {
		
//		Criteria cri = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		Criterion res1 = Restrictions.eq("a.bankShortName", shortName);
		criteria.add(res1);
//		return cri.list();
		return super.executeDetachedCriteria(criteria);
	}
}
