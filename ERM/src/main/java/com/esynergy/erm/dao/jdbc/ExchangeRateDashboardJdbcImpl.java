package com.esynergy.erm.dao.jdbc;

import java.util.Date;
 
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.esynergy.common.dao.ResultCurrencyPairListMapper;
import com.esynergy.common.dao.ResultNumberMapper;
import com.esynergy.erm.model.ob.CurrencyPairs;
import com.esynergy.erm.web.action.IPageContains;
 
 

@Repository("exchangeRateDashboardJdbc")
public class ExchangeRateDashboardJdbcImpl implements ExchangeRateDashboardJdbc {
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private static String SQL_EXC_RATE_SUCCESS_BY_RATE_DATE= " select  max(hdr.pk_rate_hdr_seq)num   " 
											 	       + " from  ERM_EXCHANGE_RATE_HDR hdr"
											 	       + " left outer join erm_exchange_rate_bank bnk_ref "
											 	       + " on hdr.pk_rate_hdr_seq = bnk_ref.fk_rate_hdr_seq ";
//	private static String SQL_MANUAL_EXC_RATE_SUCCESS_BY_RATE_DATE= " select   dtl.PK_RATE_DTL_SEQ num   " 
//																 	       + " from  ERM_EXCHANGE_RATE_DTL dtl"
//																 	       + " where dtl.FK_RATE_HDR_SEQ in"
//																 	       + "	(select hdr.pk_rate_hdr_seq "
//																 	       +   " from ERM_EXCHANGE_RATE_HDR hdr "
//																 	       +   " where to_char(hdr.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')  = ? "
//																 	       +   " and hdr.TYPE='"+IPageContains.ER_ORIGIN_MANUAL+"' ) ";
	
	private static String SQL_MANUAL_EXC_RATE_SUCCESS_BY_RATE_DATE= " select t1.pk_rate_dtl_seq num from (select   dtl.PK_RATE_DTL_SEQ, dtl.FK_BASE_CURRENCY, dtl.FK_PAIR_CURRENCY   " 
																 	       + " from  ERM_EXCHANGE_RATE_DTL dtl"
																 	       + " where dtl.FK_RATE_HDR_SEQ in"
																 	       + "	(select hdr.pk_rate_hdr_seq "
																 	       +   " from ERM_EXCHANGE_RATE_HDR hdr "
																 	       +   " where to_char(hdr.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')  = ? "
																 	       +   " and hdr.TYPE='"+IPageContains.ER_ORIGIN_MANUAL+"' ) ) t1 "
																 	       + " where t1.FK_BASE_CURRENCY || ' ' || t1.FK_PAIR_CURRENCY in (select FK_BASE_CURRENCY || ' ' || FK_PAIR_CURRENCY from ERM_MANUAL_TARGET) ";
	
    private static String SQL_RATE_DATE_CONDITION =  "   where to_char(hdr.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')  = ? ";
    private static String SQL_GROUP_BY_BANK        = " group by bnk_ref.fk_set_bank_seq";
	private static String SQL_MANUAL_REMAIN = "SELECT base_.currency_code as base_code,pair_.currency_code as pair_code"
											+" FROM erm_manual_target t "
											+ " left join erm_currency_mast base_"
											+ 		" on base_.pk_currency_id = t.fk_base_currency"
											+ " left join erm_currency_mast pair_ "
											+ 		" on pair_.pk_currency_id = t.fk_pair_currency"
											+" WHERE t.effective_date <= to_date(?,'"+IPageContains.FORMAT_DATE+"') "
											+ "and ( t.expired_date is null or t.expired_date > to_date(?,'"+IPageContains.FORMAT_DATE+"') )"
											+ "and t.fk_base_currency || ' ' || t.fk_pair_currency NOT IN ( SELECT d.FK_BASE_CURRENCY|| ' ' ||d.FK_PAIR_CURRENCY "
											                                                   +" FROM ERM_EXCHANGE_RATE_DTL d "
											                                                   +" where d.FK_RATE_HDR_SEQ in (select h.PK_RATE_HDR_SEQ  "
											                                                                              +" from ERM_EXCHANGE_RATE_HDR h "
											                                                                              +" where to_char(h.RATE_DATE,'"+IPageContains.FORMAT_DATE+"')=?)) ";
								 
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
     }	

	@Override	
	public long getCountSuccessExchangeRateManualByAddDate(Object[] parm) {
		String sql = SQL_MANUAL_EXC_RATE_SUCCESS_BY_RATE_DATE;
		List<Long> l = jdbcTemplateObject.query(sql,parm, new ResultNumberMapper());
		if(!l.isEmpty()){
			return l.size();
		}
		return  0;
	}

	@Override
	public long getCountFailExchangeRateManualByAddDate(Object[] parm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCountSuccessExchangeRateAutoByAddDate(Object[] parm) {
		String sql = SQL_EXC_RATE_SUCCESS_BY_RATE_DATE
				   + SQL_RATE_DATE_CONDITION
				   + " and hdr.TYPE = '"+IPageContains.ER_ORIGIN_AUTO+"' "
				   + SQL_GROUP_BY_BANK;
		List<Long> l = jdbcTemplateObject.query(sql,parm, new ResultNumberMapper());
		if(!l.isEmpty()){
			return l.size();
		}
		
		return  0;
	}

	@Override
	public long getCountRemainingExchangeRateAutoByAddDate() {
		String sql = " select count(h.PK_SET_HDR_SEQ) num from ERM_SETTING_HDR h " 
			 	   + " where h.RECORD_STATUS  ='"+IPageContains.RECORD_STS_ACTIVE+"'"
				   + "   and h.PK_SET_HDR_SEQ not in (select l.FK_SET_HDR_ID from ERM_HIST_LOG l) ";
		List<Long> l = jdbcTemplateObject.query(sql, new ResultNumberMapper());
		if(!l.isEmpty()){
			return l.get(0);
		}
		return  0;
	}

	@Override
	public List<Long> getExtractionLogHistIdFailByDate(Date date) {
//		 Object[] parm=new Object[1];
//		 parm[0] = IPageContains.DATE_FORMAT.format(date);
//		 String sql=" select count(l.pk_log_id), max(pk_log_id) num from ERM_HIST_LOG  l "
//				   	+" where  to_char(l.RECORD_ADD_DATE,'"+IPageContains.FORMAT_DATE+"')=? " 
//				   	+" and l.LOG_STATUS = 'FAIL' "
//                    /*+" and l.fk_set_hdr_id in (select e.FK_SETTING_HDR num from ERM_EXTRACT_REP e " 
//                                              +" where e.EXTRACT_ROUND = 3 )"*/
//                    +" having count(l.pk_log_id)>=3 "
//                    +" group by l.FK_SET_HDR_ID ";
		 
		 Object[] parm=new Object[3];
		 parm[0] = IPageContains.DATE_FORMAT.format(date);
		 parm[1] = IPageContains.DATE_FORMAT.format(date);
		 parm[2] = IPageContains.DATE_FORMAT.format(date);
		 String sql=" select max(t4.PK_LOG_ID) num from "
				   	+" (select t1.FK_SET_HDR_ID c1 " 
				   	+" from erm_hist_log t1 "
                    +" where to_char(t1.RECORD_ADD_DATE,'"+IPageContains.FORMAT_DATE+"')=? "
                    +" group by t1.FK_SET_HDR_ID "
                    +" MINUS "
                    +" select distinct t2.FK_SET_HDR_ID c1 "
                    +" from ERM_HIST_LOG t2 "
                    +" where to_char(t2.RECORD_ADD_DATE,'"+IPageContains.FORMAT_DATE+"')=? and t2.LOG_STATUS = 'SUCCESS') t3 "
                    +" left join erm_hist_log t4 "
                    +" on t3.c1 = t4.FK_SET_HDR_ID "
                    +" WHERE to_char(t4.RECORD_ADD_DATE,'"+IPageContains.FORMAT_DATE+"')=? and t4.LOG_STATUS = 'FAIL' " 
                    +" group by t4.FK_SET_HDR_ID "
                    ;
		 
		return jdbcTemplateObject.query(sql,parm, new ResultNumberMapper());
	}

	@Override
	public List<CurrencyPairs> getRemainingExchangeRateManualByAddDate(Object[] parm) {
		String sql = SQL_MANUAL_REMAIN;   
		List<CurrencyPairs> l = jdbcTemplateObject.query(sql,parm, new ResultCurrencyPairListMapper());
		if(!l.isEmpty()) 
			return l;
		return null;
	}

	@Override
	public List<Long> searchExchangerate(long pairCurrId, long baseCurrId,
			long countryOfBankId, String type, String status) {
		    
		return null;
	}

}
