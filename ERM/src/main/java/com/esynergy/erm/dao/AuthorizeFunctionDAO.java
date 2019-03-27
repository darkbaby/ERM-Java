package com.esynergy.erm.dao;
import java.util.List;

import com.esynergy.erm.model.ob.AuthorizeFunction;

public interface AuthorizeFunctionDAO {
	public List<AuthorizeFunction> listAll();
	public AuthorizeFunction getById(long id);
}
