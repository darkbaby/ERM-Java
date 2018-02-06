package com.esynergy.erm.service;

import java.util.List;
import java.util.Map;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.User;

public interface UserService {
	public IUser getUser(String userLogon,String pwd);
	public IUser getById(long id);
	public void save(User user);
	public void savePwd(User user);
	public List<IUser> list();
	public boolean getDupllicate(long id,String logonId);
	public List<IUser> search(Map<String,Object> parm);
	public boolean checkPwd(String pariPwd,IUser user);
	public IUser getByEmail(Map<String,Object> parm);
}
