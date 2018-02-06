package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.Bank;

@Repository("bankDAO")
public class BankDAOImpl extends AbstractHiberbateDAO<Integer,Bank> implements BankDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getBankWithName(String name) {

		Criteria cri = super.createEntityCriteria();
		Criterion res1 = Restrictions.eq("a.bankName", name);
		cri.add(res1);
		return cri.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getBankWithShortName(String shortName) {
		
		Criteria cri = super.createEntityCriteria();
		Criterion res1 = Restrictions.eq("a.bankShortName", shortName);
		cri.add(res1);
		return cri.list();
	}
}
