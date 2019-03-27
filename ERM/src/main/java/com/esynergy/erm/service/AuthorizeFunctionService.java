package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.AuthorizeFunction;

public interface AuthorizeFunctionService {
	public List<AuthorizeFunction> listAll();
	public AuthorizeFunction getById(long id);
}
