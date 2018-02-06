package com.esynergy.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultStringListMapper implements RowMapper<String> {

	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString("txt");
	}
 

}
