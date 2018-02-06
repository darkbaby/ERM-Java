package com.esynergy.erm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.CodeValue;

@Repository("codeValueDAO")
public class CodeValueDAOImpl extends AbstractHiberbateDAO<Integer, CodeValue> implements CodeValueDao {

	private static final Logger logger = Logger.getLogger(CodeValue.class); 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllExtractionTimeField() {
		logger.debug("----------listAllExtractionTime--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "2"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllGenerateRateTimeField() {
		logger.debug("----------listAllGenerateRateTime--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "2"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllExtractionType() {
		logger.debug("----------listAllExtractionType--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "1"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		criteria.add(Restrictions.eq("a.recordStatus", "A"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllExtractionDate() {
		logger.debug("----------listAllExtractionDate--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "3"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllPageType() {
		logger.debug("----------listAllPageType--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "4"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllCurrencyType() {
		logger.debug("----------listAllCurrencyType--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "5"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllStatus() {
		logger.debug("----------listAllStatus--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "6"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllFormatDate() {
		logger.debug("----------listAllFormatDate--------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.codeSet", "7"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		return criteria.list();
	}

	
	
}
