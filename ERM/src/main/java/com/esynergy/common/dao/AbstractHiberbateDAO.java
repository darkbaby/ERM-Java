package com.esynergy.common.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.parsing.QualifierEntry;

import com.esynergy.erm.hbm.util.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractHiberbateDAO<PK extends Serializable,T> {
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AbstractHiberbateDAO.class);
	
	@SuppressWarnings("unchecked")
	public AbstractHiberbateDAO(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
 
	@SuppressWarnings("unchecked")
	protected T getByKey(long key)   {
		T t = null;
		Session session = HibernateUtil.getSession();
		session.clear();
		try{
			t = (T)session.get(persistentClass, key);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return t;
	}
	 
	protected T save(T entity) {
		Session session =  HibernateUtil.getSession();
		session.clear();
		try{
			Transaction tran = session.beginTransaction();
			session.save(entity);
			session.flush();
			HibernateUtil.commitTransaction(tran);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return entity;
	}
	  
	protected T update(T entity)  {
		Session session =  HibernateUtil.getSession();
		session.clear();
		try{
			Transaction tran = session.beginTransaction();
			session.update(entity);
			session.flush();
			HibernateUtil.commitTransaction(tran);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return entity;
	}
	 
	protected void delete(T entity) {
		Session session =  HibernateUtil.getSession();
		session.clear();
		try{
			Transaction tran = session.beginTransaction();
			session.delete(entity);
			session.flush();
			HibernateUtil.commitTransaction(tran);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}
	   
	protected DetachedCriteria createDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(persistentClass, "a");
		return detachedCriteria;
	}
	
	protected List executeDetachedCriteria(DetachedCriteria criteria) {
		Session session =  HibernateUtil.getSession();
		session.clear();
		List returnedList = criteria.getExecutableCriteria(session).list();
		HibernateUtil.closeSession(session);
		return returnedList;
	}
	
	protected Object executeSingleDetachedCriteria(DetachedCriteria criteria) {
		Session session =  HibernateUtil.getSession();
		session.clear();
		Object returnedList = criteria.getExecutableCriteria(session).uniqueResult();
		HibernateUtil.closeSession(session);
		return returnedList;
	}
	  
//	  protected Criteria createEntityCriteria(){
//		  Session session =  HibernateUtil.getSession();
//		  session.clear();
//		  Criteria criteria = session.createCriteria(persistentClass,"a");
//	      return criteria;
//	  }
	  
	protected List hibernateNameQuery(String hbmQuery, Map<String,Object> parameters){
		Session session =  HibernateUtil.getSession();
		
		session.clear();
	
		try {
			
			Query q = session.getNamedQuery(hbmQuery);
			
			if(parameters != null) {
				for(Map.Entry<String, Object> entry:parameters.entrySet()) {
					q.setParameter(entry.getKey(), entry.getValue());
				}
			}
			
			return q.list();
		
		}
		 
		finally {
			
			HibernateUtil.closeSession(session);
		
		}
	  
	}
	
	protected List hibernateNameQuery(String hbmQuery){
		Session session =  HibernateUtil.getSession();
		
		session.clear();
	
		try {
		
			Query q = session.getNamedQuery(hbmQuery);
			
			return q.list();
		
		}
		 
		finally {
			
			HibernateUtil.closeSession(session);
		
		}
	  
	}
	  
	@SuppressWarnings("unchecked")
	protected List<T> hibernateQuery(String qStr){
		Session session =  HibernateUtil.getSession();
		session.clear();
		try {
			Query q = session.createQuery(qStr);
			return q.list();
		}
		finally {
			HibernateUtil.closeSession(session);
		} 
	}
	  
	protected void hibernateQueryUpdate(String qStr,Map<String,Object> parm){
		Session session =  HibernateUtil.getSession();  
		session.clear();
		  
		try {
			Transaction tran = session.beginTransaction();
		
			Query q=  session.createQuery(qStr);
			
			populateParamNamdQ(q,parm);
			
			q.executeUpdate();
			  
			HibernateUtil.commitTransaction(tran);
		}
		  
		finally {
			
			HibernateUtil.closeSession(session);
		
		}
		  
	  
	}
	  
	protected void hibernateQueryListUpdate(List<Map<String,Object>> q){
		Session session = HibernateUtil.getSession();
		session.clear();
		try{

			Transaction tran = session.beginTransaction();
		
			for(Map map:q){
			
				String hql = (String)map.get("hql");
			 
				Map<String,Object> parm = (Map<String,Object> ) map.get("parm");
			 
				Query query=  HibernateUtil.getSession().createQuery(hql);
			 
				populateParamNamdQ(query,parm);
			  
				query.executeUpdate();
		 
			}
		 
			HibernateUtil.commitTransaction(tran);
		 
		}catch(Exception e){
			
			e.printStackTrace();
		
		}
		finally {	
			HibernateUtil.closeSession(session);
		
		}
		 
	  
	}
	  
	 
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	protected void populateParamNamdQ(Query q,Map<String,Object> parm) {
		
		Iterator iterator = parm.keySet().iterator();
		
		for(Map.Entry<String, Object> entry:parm.entrySet()){
		
			q.setParameter(entry.getKey(),entry.getValue());
		 
		}
	 
	}
 
}
