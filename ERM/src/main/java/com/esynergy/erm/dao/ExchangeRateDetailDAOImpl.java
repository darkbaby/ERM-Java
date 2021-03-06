package com.esynergy.erm.dao;

 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SharedSessionBuilder;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.SqlASTFactory;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.hbm.util.HibernateUtil;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.web.action.IPageContains;

@Repository("exchangeRateDetailDAO")
public class ExchangeRateDetailDAOImpl extends AbstractHiberbateDAO<Integer, ExchangeRateDetail> implements ExchangeRateDetailDAO {
	private static final Logger logger = Logger.getLogger(ExchangeRateDetailDAOImpl.class); 

	public ExchangeRateDetail insertExchangeRateDetail(ExchangeRateDetail o) {
		logger.debug("----------insertExchangeRateDetail--------");
		return super.save(o);
	}

	public ExchangeRateDetail getExchangeRateDetailById(long id) {
		logger.debug("----------getExchangeRateDetailById--------");
		return super.getByKey(id);
	}
 
	public ExchangeRateDetail updateExchangeRateDetail(ExchangeRateDetail o) {
		logger.debug("----------updateExchangeRateDetail--------");
		return super.update(o);
	}

	@SuppressWarnings("unchecked")
	public List<ExchangeRateDetail> listAllExchangeRateDetail() {
		logger.debug("----------listAllExchangeRateDetail--------");
		return super.hibernateNameQuery("HQL.listAllExchangeRateDetail");
	}

	public void deleteExchangeRateDetail(ExchangeRateDetail o) {
		logger.debug("----------deleteExchangeRateDetail ID : "+o.getId());
		super.delete(o);
	}
	public long getCountByDate(Date date){
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		criteria.createAlias("a.ExchangeRateManual", "b");
		criteria.add(Restrictions.eq("b.createdDate", date));
		criteria.setProjection(Projections.rowCount());
		String l = super.executeSingleDetachedCriteria(criteria).toString();
		if(l!=null){
			return Long.parseLong(l);
		}
		return 0;
	}

	public void saveExchangeRateAutoDetailList(ExchangeRate o) {
		Session session = HibernateUtil.getSession();
		session.clear();
		Transaction tran = session.beginTransaction();
		try{
			  for(ExchangeRateDetail e:o.getExchangeRateDetails()){
				  Map<String,Object> parm=new HashMap<String,Object>();
					parm.put("buyingRateParm", e.getBuyingRate());
					parm.put("sellingRateParm", e.getSellingRate());
					parm.put("lastUpdateUserParm", e.getLastUpdateUser());
					parm.put("lastUpdateDateParm", e.getLastUpdateDate());
					parm.put("idParm", e.getId());
					String sql=" update  ExchangeRateDetail  "
							+ "  set buyingRate = :buyingRateParm , "
								 + " sellingRate = :sellingRateParm , "
								 + " lastUpdateDate = :lastUpdateDateParm , "
								 + " lastUpdateUser = :lastUpdateUserParm "
							+ "  where id= :idParm ";
				  Query query=  session.createQuery(sql);
				  populateParamNamdQ(query,parm);
				  query.executeUpdate();
			  }
			  
			  Map<String,Object> parm=new HashMap<String,Object>();
				parm.put("lastUpdateUserParm", o.getLastUpdateUser());
				parm.put("lastUpdateDateParm", o.getLastUpdateDate());
				parm.put("refDateParm", o.getRefDate());
				parm.put("idParm", o.getId());
			  String sql = "update ExchangeRate "
					  + "set lastUpdateUser = :lastUpdateUserParm , "
					  + "lastUpdateDate = :lastUpdateDateParm , "
					  + "refDate = :refDateParm "
					  + "where id = :idParm";
			  Query query=  session.createQuery(sql);
			  populateParamNamdQ(query,parm);
			  query.executeUpdate();
			  
			  HibernateUtil.commitTransaction(tran);
		}catch(Exception e){
			HibernateUtil.rollbackTransaction(tran);
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<ExchangeRateDetail> search(long pairCurrencyId,
			long baseCurrencyId, long countryOfBankId, String type, Date date){
		DetachedCriteria criteria = super.createDetachedCriteria();

//		Criteria criteria = super.createEntityCriteria();
		
		if(pairCurrencyId!=0){
			String sql = " this_.FK_PAIR_CURRENCY = ? ";
			criteria.add(Restrictions.sqlRestriction(sql, pairCurrencyId, StandardBasicTypes.LONG));
		}
		if(baseCurrencyId!=0){
			String sql = " this_.FK_BASE_CURRENCY = ? ";
			criteria.add(Restrictions.sqlRestriction(sql, baseCurrencyId, StandardBasicTypes.LONG));
		}
		if(countryOfBankId!=0){
			String sql = " this_.FK_RATE_HDR_SEQ in ( select hdr_.PK_RATE_HDR_SEQ "
													+ "from ERM_EXCHANGE_RATE_HDR hdr_ "
													  + " inner join ERM_EXCHANGE_RATE_BANK ref_"
													  	+ " on  ref_.FK_RATE_HDR_SEQ = hdr_.PK_RATE_HDR_SEQ "
													  + " inner join ERM_SETTING_BANK bank_"
													  	+ " on    ref_.FK_SET_BANK_SEQ = bank_.PK_SET_BANK_SEQ "
													    + " and   bank_.FK_COUNTRY_SEQ = ? )";
			criteria.add(Restrictions.sqlRestriction(sql, countryOfBankId, StandardBasicTypes.LONG));
		}
		if(!UIUtil.isEmptyOrNull(type)){
			String sql = " this_.FK_RATE_HDR_SEQ in ( select hdr_.PK_RATE_HDR_SEQ "
													+ " from ERM_EXCHANGE_RATE_HDR hdr_ "
													+ " where hdr_.TYPE=? )";
			criteria.add(Restrictions.sqlRestriction(sql, type, StandardBasicTypes.STRING));
		}
//		reteDateCondition(criteria,new Date());
		reteDateCondition(criteria,date);
//		return criteria.list();
		return super.executeDetachedCriteria(criteria);
	}
	
	private void reteDateCondition(DetachedCriteria criteria,Date date){
		String sql = "  this_.PK_RATE_DTL_SEQ in ( select max(dtl_.pk_rate_dtl_seq) "
												+ " from ERM_EXCHANGE_RATE_HDR hdr_   "
												+ " inner join erm_exchange_rate_dtl dtl_"
												+ " on dtl_.fk_rate_hdr_seq = hdr_.pk_rate_hdr_seq "
												+ " left outer join  erm_exchange_rate_bank bnk_ref_ "
												+ " on hdr_.pk_rate_hdr_seq = bnk_ref_.fk_rate_hdr_seq "
												+ " where to_char(hdr_.RATE_DATE,'"+IPageContains.FORMAT_DATE+"') = ? and hdr_.record_add_user <> ? "
												+ " group by bnk_ref_.fk_set_bank_seq,dtl_.fk_base_currency,dtl_.fk_pair_currency ) ";
		criteria.add(Restrictions.sqlRestriction(sql,
				new Object[] {IPageContains.DATE_FORMAT.format(date), "system(paste)"},
				new Type[] {StandardBasicTypes.STRING, StandardBasicTypes.STRING}));
		criteria.createAlias("a.firstCurrency", "fc");
		criteria.createAlias("a.pairCurrency", "pc");
		criteria.addOrder(Order.asc("fc.code")).addOrder(Order.asc("pc.code"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExchangeRateDetail> listByRateDate(Date date) {
		DetachedCriteria criteria = super.createDetachedCriteria();

//		 Criteria criteria =  super.createEntityCriteria();
		 reteDateCondition(criteria,date);
		 
//		return criteria.list();
		 return super.executeDetachedCriteria(criteria);
	}

}
