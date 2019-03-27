package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.ExtractionDetail;
import com.esynergy.erm.model.ob.Extraction;

@Repository("extractionDAO")
public class ExtractionDAOImpl extends AbstractHiberbateDAO<Integer, Extraction> implements ExtractionDAO {
	private static final Logger logger = Logger.getLogger(Extraction.class); 
	private Session session;
	
	public Extraction insertExtraction(Extraction o) {
		logger.debug("----------insertExtractionHeader--------");
		return super.save(o);
	}
	
	@SuppressWarnings("unchecked")
	public List<Extraction> listAllExtraction() {
		logger.debug("----------listAllExtraction--------");

		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.deleteFlag", (long)0));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
		
//		return super.hibernateNameQuery("HQL.listAllExtraction").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Extraction> listAllExtractionActive() {
		logger.debug("----------listAllExtractionActive--------");
		
//		Criteria criteria = super.createEntityCriteria();
//		criteria.addOrder(Order.desc("a.id"));
//		return criteria.list();
		
		return super.hibernateNameQuery("HQL.listAllExtractionActive");
	}

	@Override
	public Extraction updateExtraction(Extraction o) {
		logger.debug("----------updateExtractionHeader--------");
		return super.update(o);
	}
	
	@Override
	public void deleteExtraction(Extraction o) {
		o.setDeleteFlag(1);
		Extraction re = super.update(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Extraction> search(String bankName, long countryId,
			long TypeOfSetting, String status) {
		logger.debug("----------search--------");
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.deleteFlag", (long)0));
		criteria.createAlias("a.bank", "b");
		if(!UIUtil.isEmptyOrNull(bankName)){
			criteria.add(Restrictions.ilike("b.bankName", "%"+bankName+"%",MatchMode.ANYWHERE));
		}
		if(countryId > 0){
			criteria.createAlias("b.country", "c");
			criteria.add(Restrictions.eq("c.id", countryId));
		}
		if(TypeOfSetting > 0){
			criteria.add(Restrictions.eq("a.extractionType", TypeOfSetting));
		}
		if(!status.equals("-1")){
			criteria.add(Restrictions.eq("a.status", status));
		}
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);

	}

	@Override
	public Extraction getExtractionById(long id) {
		logger.debug("----------getExtractionById IS : "+id);
	 
		return super.getByKey(id);
	}
	@Override
	public List<Extraction> listAllExtractionByURL(String url) {
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.bankURL",url));
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);

	}

	@Override
	public List<Extraction> listFailByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
