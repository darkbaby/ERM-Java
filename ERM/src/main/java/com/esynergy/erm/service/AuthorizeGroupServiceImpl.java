package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.AuthorizeGroupDAO;
import com.esynergy.erm.model.ob.AuthorizeGroup;

@Service("authorizeGroupService")
public class AuthorizeGroupServiceImpl implements AuthorizeGroupService{
  @Autowired private AuthorizeGroupDAO authorizeGroupDAO;

@Override
public List<AuthorizeGroup> listAll() {
	return authorizeGroupDAO.listAll();
}

@Override
public AuthorizeGroup getById(long id) {
	return authorizeGroupDAO.getById(id);
}
  
}
