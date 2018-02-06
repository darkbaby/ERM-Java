package com.esynergy.erm.hbm.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

 

 
public class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>(); 
	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static Session getSession(){
		Session session = threadSession.get();
		try{
			if(session==null){
				session = sessionFactory.openSession();
				threadSession.set(session);
				logger.debug("--getSession()");
			}
		}catch(HibernateException ex){
			throw ex;
		}
		return session;
	}
	public static void closeSession(){
		try{
			Session session = (Session) threadSession.get();
			threadSession.set(null);
			if(session!=null && session.isOpen()){
				session.close();
				logger.debug("--closeSession()");
			}
		}catch(HibernateException ex){
			throw ex;
		}
	}
	public static void beginTransaction(){
		Transaction tx =(Transaction) threadTransaction.get();
		try{
			if(tx==null){
				tx=getSession().beginTransaction();
				threadTransaction.set(tx);
				logger.debug("--beginTransaction()");
			}
		}catch(HibernateException ex){
				throw ex;
		}
	}
    public static void commitTransaction(){
    	Transaction tx = (Transaction) threadTransaction.get();
    	logger.debug("--commitTransaction");
		try{
			 if(tx!=null && !tx.wasCommitted() && !tx.wasRolledBack()){
				tx.commit();
				logger.debug("--Commited! Transaction");
			 }
			 threadTransaction.set(null);
			 
		}catch(HibernateException ex){
			rollbackTransaction();
			throw ex;
		}finally{
			closeSession();
		}
    }
	
	public static void rollbackTransaction(){
		Transaction tx = (Transaction) threadTransaction.get();
		logger.debug("--rollbackTransaction()");
		try{
			if(tx!=null && !tx.wasCommitted() && !tx.wasRolledBack()){
				tx.rollback();
				logger.debug("--Can't records data");
			}
		}catch(HibernateException ex){
			throw ex;
		}finally{
			closeSession();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();        
    }
}