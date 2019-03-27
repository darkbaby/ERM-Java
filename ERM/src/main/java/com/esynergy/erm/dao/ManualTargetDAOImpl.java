package com.esynergy.erm.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.ManualTarget;

@Repository("manualTargetDAO")
public class ManualTargetDAOImpl extends AbstractHiberbateDAO<Integer,ManualTarget> implements ManualTargetDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ManualTarget> listAllNotExpire() {
		SimpleDateFormat DATE_FORMAT_ORACLE = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault());

		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		
		Criterion res1 = Restrictions.or(Restrictions.isNull("a.expiredDate"), 
				Restrictions.ge("a.expiredDate", new Date()));
//		criteria.add(Restrictions.and(Restrictions.eq("a.status", "A"), res1));

		return super.executeDetachedCriteria(criteria);
//		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ManualTarget> listAllActiveNotExpire() {
		SimpleDateFormat DATE_FORMAT_ORACLE = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault());

		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		
		Criterion res1 = Restrictions.or(Restrictions.isNull("a.expiredDate"), 
				Restrictions.ge("a.expiredDate", new Date()));
		criteria.add(Restrictions.and(Restrictions.eq("a.status", "A"), res1));

		return super.executeDetachedCriteria(criteria);
//		return criteria.list();
	}

	@Override
	public ManualTarget getByID(long id) {
		return super.getByKey(id);
	}

	@Override
	public List<ManualTarget> getByOwnerID(long id) {
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.createAlias("a.owner", "b");
		criteria.add(Restrictions.eq("b.id", id));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@Override
	public List<ManualTarget> searchByParam(long baseCurrencyID, long pairCurrencyID) {
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		if(baseCurrencyID != -1) {
			criteria.createAlias("a.baseCurrency", "b");
			criteria.add(Restrictions.eq("b.id", baseCurrencyID));
		}
		if(pairCurrencyID != -1) {
			criteria.createAlias("a.pairCurrency", "c");
			criteria.add(Restrictions.eq("c.id", pairCurrencyID));
		}

//		return criteria.list();
		return super.executeDetachedCriteria(criteria);

	}
	
	@Override
	public void saveManualTarget(ManualTarget o) {
		super.save(o);
	}
	
	@Override
	public void updateManualTarget(ManualTarget o) {
		super.update(o);
	}
}
