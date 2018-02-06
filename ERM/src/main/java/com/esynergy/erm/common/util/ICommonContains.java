package com.esynergy.erm.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public interface ICommonContains {
	public final static String FORMAT_DATE = "yyyy/MM/dd"; 
	public final static String FORMAT_DATE_ORACLE = "yyyy/MM/dd hh:mm:ss"; 
	public final static SimpleDateFormat DATE_FORMAT_ORACLE = new SimpleDateFormat(FORMAT_DATE_ORACLE,Locale.getDefault());
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT_DATE,Locale.getDefault());
	public final static DecimalFormat EXCHANGE_RATE_FORMAT = new DecimalFormat("#,##0.00000");
	public final static DecimalFormat VALUE_RATE_FORMAT = new DecimalFormat("#,##0.00");
	public final static String SYSTEM_DATE_TXT = DATE_FORMAT.format(new Date());
	
}
