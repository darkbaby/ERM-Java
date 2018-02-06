package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.AuthorizeGroup;

public interface AuthorizeGroupService {
	public List<AuthorizeGroup> listAll();
	public AuthorizeGroup getById(long id);
}
