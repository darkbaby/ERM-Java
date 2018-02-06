package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRateAuto;
import com.esynergy.erm.web.action.IPageContains;

@Repository("exchangeRateAutoDAO")
public class ExchangeRateAutoDAOImpl extends AbstractHiberbateDAO<Integer, ExchangeRateAuto> implements ExchangeRateAutoDAO {
	private static final Logger logger = Logger.getLogger(ExchangeRateAuto.class); 

	@Override
	public ExchangeRateAuto getById(long id) {
		logger.debug("----------getById IS "+id);
		return super.getByKey(id);
	}

	@Override
	public void saveExchangeRateAuto(ExchangeRateAuto o) {
		logger.debug("----------saveExchangeRateAuto---------");
		super.save(o);
	}

	@Override
	public void updateExchangeRateAuto(ExchangeRateAuto o) {
		logger.debug("----------updateExchangeRateAuto---------");
		super.update(o);
	}
    
	@SuppressWarnings("unchecked")
	public ExchangeRateAuto getByBankId(long id){
		Criteria criteria = super.createEntityCriteria();
		criteria.createAlias("a.bankOfRate", "b");
		criteria.createAlias("b.bank", "c");
		criteria.add(Restrictions.eq("c.id", id));
		List<ExchangeRateAuto> l = criteria.list();
		if(l!=null && l.size()>0) 
			return l.get(0);
		return null;
	 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IExchangeRate> listByDate(Date date) {
		 Criteria criteria = super.createEntityCriteria();
		 criteria.add(Restrictions.sqlRestriction("to_char(RATE_DATE,'"+IPageContains.FORMAT_DATE+"')=?",IPageContains.DATE_FORMAT.format(date),StandardBasicTypes.STRING));
		 return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IExchangeRate> search(long pairCurrencyId,
			long baseCurrencyId, long countryOfBankId) {
		    
		    Criteria criteria = super.createEntityCriteria();
		    
		    criteria.add(Restrictions.sqlRestriction("to_char(this_.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')=?", IPageContains.SYSTEM_DATE_TXT, StandardBasicTypes.STRING));
		    
		     if(baseCurrencyId!=0){
		    	String sql = "this_.PK_RATE_HDR_SEQ in (select b1_.FK_RATE_HDR_SEQ "
														+ "from ERM_EXCHANGE_RATE_DTL b1_ "
														+ "where b1_.FK_BASE_CURRENCY=?)";
		    	criteria.add(Restrictions.sqlRestriction(sql, baseCurrencyId, StandardBasicTypes.LONG));
		    }
		    if(pairCurrencyId!=0){
		    	String sql = "this_.PK_RATE_HDR_SEQ in (select b1_.FK_RATE_HDR_SEQ "
		    											+ "from ERM_EXCHANGE_RATE_DTL b1_ "
		    											+ "where b1_.FK_PAIR_CURRENCY=?)";
		    	criteria.add(Restrictions.sqlRestriction(sql, pairCurrencyId, StandardBasicTypes.LONG));
		    }
		    if(countryOfBankId!=0){
		    	String sql = "this_.PK_RATE_HDR_SEQ in (select ref_.FK_RATE_HDR_SEQ "
		    										  + " from  ERM_EXCHANGE_RATE_BANK ref_"
		    										  + " inner join ERM_SETTING_BANK bank_"
		    										  + " on    ref_.FK_SET_BANK_SEQ = bank_.PK_SET_BANK_SEQ "
		    										  + " and   bank_.FK_COUNTRY_SEQ = ? )";
		    	criteria.add(Restrictions.sqlRestriction(sql, countryOfBankId, StandardBasicTypes.LONG));
		    }
		    criteria.addOrder(Order.asc("a.id"));
		    
		return criteria.list();
	}

 
	
}
