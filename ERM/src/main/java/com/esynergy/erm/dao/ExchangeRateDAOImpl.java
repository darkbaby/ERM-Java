package com.esynergy.erm.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.ob.ExchangeRate;
import com.esynergy.erm.web.action.IPageContains;

@SuppressWarnings("deprecation")
@Repository("exchangeRateDAO")
public class ExchangeRateDAOImpl extends AbstractHiberbateDAO<Integer, ExchangeRate> implements ExchangeRateDAO {
	private static final Logger logger = Logger.getLogger(ExchangeRateDAOImpl.class); 
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ExchangeRate> getByRateDate(Date date) {
		logger.debug("--------------getByRateDate----------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.sqlRestriction("to_char(rate_Date,'"+IPageContains.FORMAT_DATE+"')=?", IPageContains.DATE_FORMAT.format(date),StandardBasicTypes.STRING));
		
		/*return super.hibernateQuery("from ExchangeRate a where to_char(a.rateDate,'DD/MM/YYYY')='"
				+IPageContains.DATE_FORMAT.format(date)+"'");*/
		return criteria.list();
	}

	@Override
	public IExchangeRate getbyId(long id) {
		logger.debug("--------------getbyId----------");
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("a.id", id));
		criteria.createAlias("a.exchangeRateDetails", "b");
		criteria.addOrder(Order.asc("b.id"));
		if(criteria.list()!=null && criteria.list().size()>0)
			return (IExchangeRate)criteria.list().get(0);
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<IExchangeRate> search(long pairCurrencyId, long baseCurrencyId,String type) {
		Criteria criteria = super.createEntityCriteria();
		 
		if(pairCurrencyId!=0){
			criteria.createAlias("a.pairCurrency", "cp");
			criteria.add(Expression.eq("cp.id", pairCurrencyId));
		}
		
		if(baseCurrencyId!=0){
			criteria.createAlias("a.firstCurrency", "cf");
			criteria.add(Expression.eq("cf.id", baseCurrencyId));
		}
		
		if(!UIUtil.isEmptyOrNull(type)){
			criteria.createAlias("a.exchangeRateDetails", "dtl");
			criteria.add(Restrictions.eq("dtl.type", type));
		}
	/*	if(countryOfBankId!=0){
			criteria.createAlias("a.exchangeRateDetails", "dtl");
			criteria.createAlias("dtl.ownerByAuto", "auto");
			criteria.createAlias("auto.exchangeRateAuto", "auhdr");
			criteria.createAlias("auhdr.bank", "aubk");
			criteria.createAlias("aubk.country", "bkc");
			criteria.add(Expression.eq("bkc.id", countryOfBankId));
		}*/
		//criteria.add(criterion) 
		return criteria.list();
	}

}
