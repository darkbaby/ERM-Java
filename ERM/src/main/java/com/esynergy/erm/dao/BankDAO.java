package com.esynergy.erm.dao;

import java.util.List;

import com.esynergy.erm.model.ob.Bank;

public interface BankDAO {

	public List<Bank> getBankWithName(String name);
	
	public List<Bank> getBankWithShortName(String shortName);
	
}
