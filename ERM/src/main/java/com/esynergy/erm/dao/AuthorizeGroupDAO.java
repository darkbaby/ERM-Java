package com.esynergy.erm.dao;
import java.util.List;

import com.esynergy.erm.model.ob.AuthorizeGroup;
public interface AuthorizeGroupDAO {
	public List<AuthorizeGroup> listAll();
	public AuthorizeGroup getById(long id);
}
