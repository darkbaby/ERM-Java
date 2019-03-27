package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.AuthorizeFunctionDAO;
import com.esynergy.erm.model.ob.AuthorizeFunction;

@Service("authorizeFunctionService")
public class AuthorizeFunctionServiceImpl implements AuthorizeFunctionService {

	@Autowired private AuthorizeFunctionDAO authorizeFunctionDAO;

	@Override
	public List<AuthorizeFunction> listAll() {
		return authorizeFunctionDAO.listAll();
	}

	@Override
	public AuthorizeFunction getById(long id) {
		return authorizeFunctionDAO.getById(id);
	}
	
}
