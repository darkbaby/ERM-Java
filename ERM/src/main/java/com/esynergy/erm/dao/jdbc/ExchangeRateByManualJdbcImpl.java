package com.esynergy.erm.dao.jdbc;
 
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.ResultKeyListMapper;
import com.esynergy.common.dao.ResultStringListMapper;
import com.esynergy.erm.web.action.IPageContains;

@Repository("exchangeRateByManualJdbc")
public class ExchangeRateByManualJdbcImpl implements ExchangeRateByManualJdbc {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	
 
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
     }	
	
	public List<Long> getExchangeRateManualByRateDateAndCurrencyForDupCkl(Object[] parm) {
		String sql = " select hdr.PK_RATE_HDR_SEQ as id from ERM_EXCHANGE_RATE_HDR hdr " 
			 	   + " inner join ERM_EXCHANGE_RATE_DTL dtl "
				   + "    on dtl.FK_RATE_HDR_SEQ = hdr.PK_RATE_HDR_SEQ "
				   + "   and dtl.FK_BASE_CURRENCY = ? "
				   + "   and dtl.FK_PAIR_CURRENCY = ? "
				   + "   and dtl.PK_RATE_DTL_SEQ <> ?" 
				   + " where to_char(hdr.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')  = to_char(to_date(?,'"+IPageContains.FORMAT_DATE+"'),'"+IPageContains.FORMAT_DATE+"') "
				   + "   and hdr.PK_RATE_HDR_SEQ <> ? "; 
		List<Long> l = jdbcTemplateObject.query(sql,parm, new ResultKeyListMapper());
		return  l;
	 
	}

	public List<String> listAllUserUpdate() {
		String sql = " select distinct hdr.RECORD_CHANGE_USER txt from ERM_EXCHANGE_RATE_HDR hdr ";
		return jdbcTemplateObject.query(sql, new ResultStringListMapper());
	}

}
