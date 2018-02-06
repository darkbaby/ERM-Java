package com.esynergy.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esynergy.erm.dao.BankDAO;
import com.esynergy.erm.model.ob.Bank;

@Service("bankService")
public class BankServiceImpl implements BankService {
	
	@Autowired
	private BankDAO bankDAO;

	@Override
	public List<Bank> getBankWithName(String name){
		
		return bankDAO.getBankWithName(name);
	}
	
	@Override
	public List<Bank> getBankWithShortName(String shortName){
		
		return bankDAO.getBankWithShortName(shortName);
	}
}
