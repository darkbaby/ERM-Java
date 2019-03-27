package com.esynergy.erm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
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
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "2"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllGenerateRateTimeField() {
		logger.debug("----------listAllGenerateRateTime--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "2"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllExtractionType() {
		logger.debug("----------listAllExtractionType--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "1"));
		criteria.addOrder(Order.asc("a.sortOrder"));
		criteria.add(Restrictions.eq("a.recordStatus", "A"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllExtractionDate() {
		logger.debug("----------listAllExtractionDate--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "3"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllPageType() {
		logger.debug("----------listAllPageType--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "4"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllCurrencyType() {
		logger.debug("----------listAllCurrencyType--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "5"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllStatus() {
		logger.debug("----------listAllStatus--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "6"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllFormatDate() {
		logger.debug("----------listAllFormatDate--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "7"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> listAllValue() {
		logger.debug("----------listAllValue--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "9"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CodeValue getPythonPath() {
		logger.debug("----------getPythonPath--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "8"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		criteria.setFirstResult(0);
//		criteria.setMaxResults(1);
		List temp = super.executeDetachedCriteria(criteria);
		CodeValue reValue = (CodeValue)temp.get(0);
		return reValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CodeValue getManualPath() {
		logger.debug("----------getManualPath--------");
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.eq("a.codeSet", "10"));
		criteria.addOrder(Order.asc("a.sortOrder"));
//		criteria.setFirstResult(0);
//		criteria.setMaxResults(1);
		List temp = super.executeDetachedCriteria(criteria);
		CodeValue reValue = (CodeValue)temp.get(0);
		return reValue;
	}
}
