package com.esynergy.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultNumberMapper implements RowMapper<Long> {

	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getLong("num");
	}


}
