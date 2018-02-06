package com.esynergy.erm.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.web.action.IPageContains;

@Repository("userDAO")
public class UserDAOImpl extends AbstractHiberbateDAO<Integer, User> implements UserDAO {

	@Override
	public IUser getById(long id) {
		return super.getByKey(id);
	}
	@SuppressWarnings("unchecked")
	public IUser getByLogOnId(String logOnId) throws GenericJDBCException{
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.eq("logOnId", logOnId));
		/*criteria.add(Restrictions.eq("pwd", pwd));*/
		//criteria.add(Restrictions.eq("recordStatus", IPageContains.RECORD_STS_ACTIVE));
		List<IUser> l = criteria.list();
		if(l!=null && (!l.isEmpty()) && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	@Override
	public void saveUser(User o) {
		super.save(o);
		
	}
	@Override
	public void updateUser(User o) {
		super.update(o);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IUser> listAll() {
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.not(Restrictions.eq("recordStatus", IPageContains.RECORD_STS_DELETE)));
		//criteria.add(Restrictions.eq("recordStatus",IPageContains.RECORD_STS_ACTIVE));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IUser> getDupllicate(long id, String logonId) {
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.not(Restrictions.eq("id",id )));
		criteria.add(Restrictions.eq("logOnId", logonId));
		criteria.add(Restrictions.not(Restrictions.eq("recordStatus", IPageContains.RECORD_STS_DELETE)));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IUser> search(Map<String, Object> parm) {
		Criteria criteria = super.createEntityCriteria();
		criteria.add(Restrictions.not(Restrictions.eq("recordStatus", IPageContains.RECORD_STS_DELETE)));
		if(parm==null){
			return criteria.list();
		}else{
			String logOnId = (String)parm.get(IPageContains.ATTR_USER_LOGON_ID);
			String firstName = (String)parm.get(IPageContains.ATTR_USER_FIRST_NAME);
			String lastName = (String)parm.get(IPageContains.ATTR_USER_LAST_NAME);
			String emailAddr = (String)parm.get(IPageContains.ATTR_USER_EMAIL_ADDR);
			long countryId = (Long)parm.get(IPageContains.ATTR_USER_COUNTRY_ID);
			long groupId = (Long)parm.get(IPageContains.ATTR_USER_GROUP_ID);
			if(!UIUtil.isEmptyOrNull(logOnId)){
				criteria.add(Restrictions.ilike("logOnId", "%"+logOnId+"%",MatchMode.ANYWHERE));
			}
			if(!UIUtil.isEmptyOrNull(firstName)){
				criteria.add(Restrictions.ilike("firstName", "%"+firstName+"%",MatchMode.ANYWHERE));
			}
			if(!UIUtil.isEmptyOrNull(lastName)){
				criteria.add(Restrictions.ilike("lastName", "%"+lastName+"%",MatchMode.ANYWHERE));
			}
			if(!UIUtil.isEmptyOrNull(emailAddr)){
				criteria.add(Restrictions.ilike("emailAddress", "%"+emailAddr+"%",MatchMode.ANYWHERE));
			}
			if(countryId>0){
				criteria.add(Restrictions.sqlRestriction(" this_.LOCATION=?", countryId, StandardBasicTypes.LONG) );
			}
			if(groupId>0){
				criteria.add(Restrictions.sqlRestriction(" this_.FK_GROUP_SEQ=?", groupId, StandardBasicTypes.LONG));
			}
			return criteria.list();
		}
		 
	}
	@SuppressWarnings("unchecked")
	@Override
	public IUser getByEmail(Map<String, Object> parm) {
		
		if(parm!=null){
			Criteria criteria = super.createEntityCriteria();
			criteria.add(Restrictions.not(Restrictions.eq("recordStatus", IPageContains.RECORD_STS_DELETE)));
			String emailStr = (String) parm.get(IPageContains.ATTR_USER_EMAIL_ADDR);
			criteria.add(Restrictions.eq("emailAddress", emailStr));
			List l = criteria.list();
		    if(l!=null && l.size()>0){
		    	return (IUser)l.get(0);
		    }
		}
		return null;
	}
 
 
}
