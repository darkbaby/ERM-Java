package com.esynergy.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.esynergy.erm.model.ob.CurrencyPairs;

public class ResultCurrencyPairListMapper implements RowMapper<CurrencyPairs> {

	public CurrencyPairs mapRow(ResultSet rs, int rowNum) throws SQLException {
		CurrencyPairs pairs = new CurrencyPairs();
		pairs.setBaseCode(rs.getString("base_code"));
		pairs.setPairCode(rs.getString("pair_code"));
		return pairs;
	}
}
