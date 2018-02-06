package com.esynergy.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultId1Id2ListMapper implements RowMapper<Row> {

	 public Row mapRow(ResultSet rs, int rowNum) throws SQLException {
		Row row = new Row();
		row.setColumn1(rs.getLong("id1"));
		row.setColumn2(rs.getLong("id2"));
		return row;
	}
 

}
