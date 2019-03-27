package com.esynergy.erm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.AbstractHiberbateDAO;
import com.esynergy.erm.model.ob.Country;
 
@Repository("countryDAO")
public class CountryDAOImpl extends AbstractHiberbateDAO<Integer, Country> implements CountryDAO {
	 
	private static final Logger logger = Logger.getLogger(Country.class); 
	
	@SuppressWarnings("unchecked")
	public List<Country> listAllCountry() {
		logger.debug("----------listAllCountry--------");
		return super.hibernateNameQuery("HQL.listAllCountry");
	}

	public Country getCountryById(long id) {
		logger.debug("----------getCountryById is "+id); 
		return super.getByKey(id);
	}

}
