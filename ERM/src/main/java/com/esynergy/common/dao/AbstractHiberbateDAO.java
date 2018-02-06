package com.esynergy.common.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.esynergy.erm.hbm.util.HibernateUtil;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		  try{
		  	Session session = HibernateUtil.getSession();
			session.clear();
		    t = (T)session.get(persistentClass, key);
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
	      return t;
	  }
	 
	  protected T save(T entity) {
		  Session session =  HibernateUtil.getSession();
		  session.clear();
	       try{
	    	 HibernateUtil.beginTransaction();
	    	 session.save(entity);
	    	 session.flush();
	    	 HibernateUtil.commitTransaction();
	       }catch(Exception ex){
	    	   HibernateUtil.rollbackTransaction();
	    	   ex.printStackTrace();
	       }
	       return entity;
	  }
	  
	  protected T update(T entity)  {
		  Session session =  HibernateUtil.getSession();
		  session.clear();
		  try{
			   HibernateUtil.beginTransaction();
			   session.update(entity);
			   session.flush();
			   HibernateUtil.commitTransaction();
		  }catch(Exception ex){
			  HibernateUtil.rollbackTransaction();
			  ex.printStackTrace();
		  }
	       return entity;
	  }
	 
	  protected void delete(T entity) {
		  Session session =  HibernateUtil.getSession();
		  session.clear();
		  try{
			  HibernateUtil.beginTransaction();
			  session.delete(entity);
			  HibernateUtil.commitTransaction();
		  }catch(Exception ex){
			  HibernateUtil.rollbackTransaction();
			  ex.printStackTrace();
		  }
	  }
	     
	  protected Criteria createEntityCriteria(){
		  Session session =  HibernateUtil.getSession();
		  session.clear();
	      return session.createCriteria(persistentClass,"a");
	  }
	  protected Query hibernateNameQuery(String hbmQuery){
		  Session session =  HibernateUtil.getSession();
		  session.clear();
		   return session.getNamedQuery(hbmQuery);
	  }
	  
	  @SuppressWarnings("unchecked")
	 protected List<T> hibernateQuery(String qStr){
		  Session session =  HibernateUtil.getSession();
		  session.clear();
		  Query q=  session.createQuery(qStr);
		  return q.list();
	  }
	  protected void hibernateQueryUpdate(String qStr,Map<String,Object> parm){
		  Session session =  HibernateUtil.getSession();
		  session.clear();
		  Query q=  session.createQuery(qStr);
		  populateParamNamdQ(q,parm);
		  q.executeUpdate();
	  }
	  protected void hibernateQueryListUpdate(List<Map<String,Object>> q){
		 try{
		  HibernateUtil.beginTransaction();
		  for(Map map:q){
			  String hql = (String)map.get("hql");
			  Map<String,Object> parm = (Map<String,Object> ) map.get("parm");
			  Query query=  HibernateUtil.getSession().createQuery(hql);
			  populateParamNamdQ(query,parm);
			  query.executeUpdate();
		  }
		  HibernateUtil.commitTransaction();
		 }catch(Exception e){
			 HibernateUtil.rollbackTransaction();
			 e.printStackTrace();
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
