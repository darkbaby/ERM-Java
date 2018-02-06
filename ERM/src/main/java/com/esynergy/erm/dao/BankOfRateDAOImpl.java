package com.esynergy.erm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.BankOfRate;

@Repository("bankOfRateDAO")
public class BankOfRateDAOImpl extends AbstractHiberbateDAO<Integer, BankOfRate> implements BankOfRateDAO {

	@SuppressWarnings("unchecked")
	@Override
	public BankOfRate getByExchangeRateHdrId(long id) {
			Criteria criteria = super.createEntityCriteria();
			String sql = " this_.FK_RATE_HDR_SEQ = ? ";
			criteria.add(Restrictions.sqlRestriction(sql,id,StandardBasicTypes.LONG));
			List<BankOfRate> l = criteria.list();
			if(!l.isEmpty()) 
				return l.get(0);
		return null;
	}

}
