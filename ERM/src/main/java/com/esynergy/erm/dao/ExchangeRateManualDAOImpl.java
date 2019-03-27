package com.esynergy.erm.dao;

 
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
 








import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.hbm.util.HibernateUtil;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.web.action.IPageContains;
 

@Repository("exchangeRateManualDAO")
public class ExchangeRateManualDAOImpl extends AbstractHiberbateDAO<Integer, ExchangeRateManual> implements ExchangeRateManualDAO {
	private static final Logger logger = Logger.getLogger(ExchangeRateManual.class); 
	private Session session;
 
	public ExchangeRateManual insertExchangeRateManual(ExchangeRateManual o) {
		logger.debug("----------insertExchangeRateManual--------");
		return super.save(o);
	}

	public ExchangeRateManual getExchangeRateManualById(long id) {
		logger.debug("----------getExchangeRateManualById--------");
//		session = HibernateUtil.getSession();
		//ExchangeRateManual current =(ExchangeRateManual) session.load(ExchangeRateManual.class, id);
//		ExchangeRateManual reVal = (ExchangeRateManual) session.load(ExchangeRateManual.class, id);
		return super.getByKey(id);
//		return reVal;
	}
     
//	public void saveExchangeRateManual(ExchangeRateManual o){
//		
//		session = HibernateUtil.getSession();
//		session.clear();
//		Transaction tx =session.beginTransaction();
//		try{
//			if(o.getId()==0){
//				session.save(o);
//			}else{
//				session.update(o);
//			}
//			session.flush();
//			tx.commit();
//		}catch(Exception e){
//			tx.rollback();
//			e.printStackTrace();
//		}
//	}
	 
	public ExchangeRateManual updateExchangeRateManual(ExchangeRateManual o) {
		logger.debug("----------updateExchangeRateManual--------");
	    super.update(o);
		return o;
	}

	@SuppressWarnings("unchecked")
	public List<IExchangeRate> listAllExchangeRateManual() {
		logger.debug("----------listAllExchangeRateManual--------");
		
		return super.hibernateNameQuery("HQL.listAllExchangeRateManual");
	}
	
	public void deleteExchangeRateManual(ExchangeRateManual o) {
		logger.debug("----------deleteExchangeRateManual ID : "+o.getId());
		super.delete(o);
	}

	@SuppressWarnings("unchecked")
	public List<IExchangeRate> listExchangeRateManualByLastUpdateUser(String arg) {
		logger.debug("----------listExchangeRateManualByLastUpdateUser Arg : "+arg);
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.lastUpdateUser", arg));
		criteria.addOrder(Order.desc("a.rateDate"));
//		return  criteria.list();
		return super.executeDetachedCriteria(criteria);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<IExchangeRate> listExchangeRateManualByRateDate(
			Date strDate, Date endDate)    {
		logger.debug("----------listExchangeRateManualByRateDate  "); 
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Expression.ge("a.rateDate", strDate));
		criteria.add(Expression.le("a.rateDate", endDate));
	/*	criteria.add(Restrictions.between("rateDate", strDate, endDate));*/
		/*criteria.add(Restrictions.ge("rateDate", strDate, endDate));*/
		criteria.addOrder(Order.desc("a.rateDate"));
		
//		List<IExchangeRate> exchangeRates = criteria.list();
		List<IExchangeRate> exchangeRates = super.executeDetachedCriteria(criteria);
		return exchangeRates;
//		return   criteria.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<IExchangeRate> listExchangeRateManualByRateDateAndLastUpdateUser(
			Date strDate, Date endDate, String lastUpdateUser) {
		logger.debug("----------listExchangeRateManualByRateDateAndLastUpdateUser  "); 
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.add(Expression.ge("a.rateDate", strDate));
		criteria.add(Expression.le("a.rateDate", endDate));
		criteria.add(Restrictions.eq("a.lastUpdateUser", lastUpdateUser));
		criteria.addOrder(Order.desc("a.rateDate"));
		
//		List<IExchangeRate> exchangeRates = criteria.list();
		List<IExchangeRate> exchangeRates = super.executeDetachedCriteria(criteria);

		return exchangeRates;
//		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<IExchangeRate> listExchangeRateManualByRateDate(Date date){
//		Criteria criteria = super.createEntityCriteria();
		DetachedCriteria criteria = super.createDetachedCriteria();

		criteria.add(Restrictions.sqlRestriction("to_char(rate_Date,'"+IPageContains.FORMAT_DATE+"')=? order by RECORD_CHANGE_DATE DESC"
					, IPageContains.DATE_FORMAT.format(date)
					,StandardBasicTypes.STRING));
		
//		List<IExchangeRate> exchangeRates = criteria.list();
		List<IExchangeRate> exchangeRates = super.executeDetachedCriteria(criteria);
		
		return exchangeRates;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<IExchangeRate> search(long pairCurrencyId,
			long baseCurrencyId){
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		if(pairCurrencyId!=0){
			String sql = "this_.PK_RATE_HDR_SEQ in (select b1_.FK_RATE_HDR_SEQ "
					+ "  from ERM_EXCHANGE_RATE_DTL b1_ "
					+ " where b1_.FK_PAIR_CURRENCY=?)"
					+ " order by RECORD_CHANGE_DATE DESC";
			criteria.add(Restrictions.sqlRestriction(sql, pairCurrencyId, StandardBasicTypes.LONG));
		}
		if(baseCurrencyId!=0){
			String sql = "this_.PK_RATE_HDR_SEQ in (select b1_.FK_RATE_HDR_SEQ "
					+ " from ERM_EXCHANGE_RATE_DTL b1_ "
					+ " where b1_.FK_BASE_CURRENCY=?) "
					+ " order by RECORD_CHANGE_DATE DESC ";
			criteria.add(Restrictions.sqlRestriction(sql, baseCurrencyId, StandardBasicTypes.LONG));
		}
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}
	public List<IExchangeRate> search(
				Date strDate, Date endDate,String userUpdate)    {
			logger.debug("----------listExchangeRateManualByRateDate  "); 
			DetachedCriteria criteria = super.createDetachedCriteria();

//			Criteria criteria = super.createEntityCriteria();
			if(strDate==null && endDate==null && UIUtil.isEmptyOrNull(userUpdate)){
				return this.listExchangeRateManualByRateDate(new Date());
			}
			if(strDate!=null){
				criteria.add(Expression.ge("a.rateDate", strDate));
			}
			if(endDate!=null){
				criteria.add(Expression.le("a.rateDate", endDate));
			}
			if(!UIUtil.isEmptyOrNull(userUpdate)){
				criteria.add(Restrictions.eq("a.lastUpdateUser", userUpdate));
			}
			criteria.addOrder(Order.desc("a.lastUpdateDate"));
//			List<IExchangeRate> exchangeRates = criteria.list();	
			List<IExchangeRate> exchangeRates = super.executeDetachedCriteria(criteria);
			return exchangeRates;
	}
	
	public List<IExchangeRate> search(
			Date strDate, Date endDate,long baseCurrencyId,
			long pairCurrencyId )    {
		logger.debug("----------listExchangeRateManualByRateDate  "); 
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		if(strDate!=null){
			criteria.add(Expression.ge("a.rateDate", strDate));
		}
		if(endDate!=null){
			criteria.add(Expression.le("a.rateDate", endDate));
		}
				
//		criteria.createAlias("a.exchangeRateDetails", "b");
//		criteria.createAlias("b.firstCurrency", "c");
//		criteria.createAlias("b.pairCurrency", "d");
//		if(pairCurrencyId!=0){
//			criteria.add(Restrictions.eqOrIsNull("d.id", pairCurrencyId));
//		}
//		if(baseCurrencyId!=0){
//			criteria.add(Restrictions.eqOrIsNull("c.id", baseCurrencyId));
//		}
		
		criteria.addOrder(Order.desc("a.lastUpdateDate"));
//		List<IExchangeRate> exchangeRates = criteria.list();	
		List<IExchangeRate> exchangeRates = super.executeDetachedCriteria(criteria);

		return exchangeRates;

	}
}
