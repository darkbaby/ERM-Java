package com.esynergy.erm.dao;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.web.action.IPageContains;

@Repository("exchangeRateAutoHISTLogDAO")
public class ExchangeRateAutoHISTLogDAOImpl extends AbstractHiberbateDAO<Integer, ExchangeRateAutoHISTLog> implements ExchangeRateAutoHISTLogDAO{

	@Override
	public ExchangeRateAutoHISTLog getById(long id) {
		return super.getByKey(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ExchangeRateAutoHISTLog> getByDate(Date date) {
		 Criteria criteria = super.createEntityCriteria();
		 criteria.add(Restrictions.sqlRestriction("to_char(RECORD_ADD_DATE,'"+IPageContains.FORMAT_DATE+"')=?", ICommonContains.DATE_FORMAT.format(date), StandardBasicTypes.STRING));
		 criteria.addOrder(Order.desc("a.id"));
		 return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<ExchangeRateAutoHISTLog> search(Date dateStrat,Date dateEnd,String bankName,String logStatust) {
		 Criteria criteria = super.createEntityCriteria();
		 criteria.add(Restrictions.ge("a.createdDate", dateStrat));
		 criteria.add(Restrictions.le("a.createdDate", dateEnd));
		 if(!UIUtil.isEmptyOrNull(bankName)){
			 criteria.createAlias("a.extraction", "e");
			 criteria.createAlias("e.bank","b");
			 criteria.add(Restrictions.ilike("b.bankName", "%"+bankName+"%",MatchMode.ANYWHERE));
		 }
		 if(!UIUtil.isEmptyOrNull(logStatust)){
			 criteria.add(Restrictions.eq("logStatus", logStatust));
		 }
		 criteria.addOrder(Order.desc("a.id"));
		 return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<ExchangeRateAutoHISTLog> search(Date dateStrat,Date dateEnd,String userUpdate) {
		 Criteria criteria = super.createEntityCriteria();
		 if(dateStrat==null && dateEnd==null && UIUtil.isEmptyOrNull(userUpdate)){
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(new Date());
			 cal.add(Calendar.DATE, -1);
			 return this.getByDate(cal.getTime());
		 }
		 
		 if(dateStrat!=null)  criteria.add(Restrictions.ge("a.createdDate", dateStrat));
		 if(dateEnd!=null) criteria.add(Restrictions.le("a.createdDate", dateEnd));
		 if(!UIUtil.isEmptyOrNull(userUpdate)){
			 criteria.add(Restrictions.eq("a.lastUpdateUser", userUpdate));
		 }
		  
		 criteria.addOrder(Order.desc("a.id"));
		 return criteria.list();
	}

}
