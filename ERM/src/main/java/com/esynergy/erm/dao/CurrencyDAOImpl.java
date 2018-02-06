package com.esynergy.erm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.Currency;
 
@Repository("currencyDAO")
public class CurrencyDAOImpl extends AbstractHiberbateDAO<Integer, Currency> implements CurrencyDAO {
	 
	private static final Logger logger = Logger.getLogger(Currency.class); 
	
	@SuppressWarnings("unchecked")
	public List<Currency> listAllCurrency() {
		logger.debug("----------listAllCurrency--------");
		return super.hibernateNameQuery("HQL.listAllCurrency").list();
	}

	public Currency getCurrencyById(long id) {
		logger.debug("----------getCurrencyById is "+id); 
		return super.getByKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> listWhitOutById(long id) {
		logger.debug("----------listWhitOutById is "+id); 
		Query q = super.hibernateNameQuery("HQL.listCurrencyWithOutId");
		q.setParameter("parmId", id);
		return q.list();
	}

}
