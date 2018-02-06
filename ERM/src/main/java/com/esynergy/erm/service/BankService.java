package com.esynergy.erm.service;

import java.util.List;

import com.esynergy.erm.model.ob.Bank;

public interface BankService {

	public List<Bank> getBankWithName(String name);
	
	public List<Bank> getBankWithShortName(String shortName);

}
