package com.esynergy.erm.dao;

import java.util.List;
import java.util.Map;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.User;

public interface UserDAO {
	public IUser getById(long id);
	public IUser getByLogOnId(String logOnId);
	public void  saveUser(User user);
	public void  updateUser(User user);
	public List<IUser> listAll();
	public List<IUser> getDupllicate(long id,String logonId);
	public List<IUser> search(Map<String,Object> parm);
	public IUser getByEmail(Map<String,Object> parm);
	 
}
