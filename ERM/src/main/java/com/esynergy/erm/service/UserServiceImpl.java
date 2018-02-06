package com.esynergy.erm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.dao.UserDAO;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired UserDAO userDAO;
	
	private String getSHA3(String pwd,String _2stChar){
		DigestSHA3 _cha3 = new SHA3.Digest512();
		byte[] _1stDigest = _cha3.digest(pwd.getBytes());
		String _1stCode = UIUtil.getSHA3(pwd)+_2stChar;
		String endCode = UIUtil.getSHA3(_1stCode);
		return endCode;
	}
	
	@Override
	public IUser getUser(String userLogon, String pwd) {
		IUser u = userDAO.getByLogOnId(userLogon);
		if(u!=null){
			String endCode = getSHA3(pwd,String.valueOf(u.getLogOnId()));
			if(endCode.equals(u.getPwd())) 
				return u;
			return null;
		}
		return null;
	}

	@Override
	public IUser getById(long id) {
		return userDAO.getById(id);
	}

	@Override
	public void save(User o) {
		o.setLastUpdateDate(new Date());
		if(o.getId()==0){
			o.setCreatedDate(new Date());
			o.setCreatedUser(o.getLastUpdateUser());
			String pwdCode = getSHA3(o.getPwd(),String.valueOf(o.getLogOnId()));
			o.setPwd(pwdCode);
			userDAO.saveUser(o);
		}else{
			IUser userCurrent = userDAO.getById(o.getId());
			o.setLogOnId(userCurrent.getLogOnId());
			o.setPwd(userCurrent.getPwd());
			userDAO.updateUser(o);
		}
	}

	@Override
	public List<IUser> list() {
		return userDAO.listAll();
	}

	@Override
	public boolean getDupllicate(long id, String logonId) {
		List<IUser> l = userDAO.getDupllicate(id, logonId);
		return l!=null && l.size()>0?true:false;
	}

	@Override
	public List<IUser> search(Map<String, Object> parm) {
		return userDAO.search(parm);
	}

	@Override
	public boolean checkPwd(String pariPwd, IUser user) {
		String pairCode = getSHA3(pariPwd,String.valueOf(user.getLogOnId()));
		if(pairCode.equals(user.getPwd())) return true;
		return false;
	}

	@Override
	public void savePwd(User o) {
			o.setLastUpdateDate(new Date());
			String pwdCode = getSHA3(o.getPwd(),String.valueOf(o.getLogOnId()));
			o.setPwd(pwdCode);
			userDAO.updateUser(o);
	}

	@Override
	public IUser getByEmail(Map<String, Object> parm) {
		return userDAO.getByEmail(parm);
	}
}
