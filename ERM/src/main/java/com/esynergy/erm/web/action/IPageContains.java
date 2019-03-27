package com.esynergy.erm.web.action;

import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.common.util.UIUtil;

public interface IPageContains extends ICommonContains {
	
	//public final String API_URL = "http://192.168.10.78:8080/erm";
	public final String API_URL =  "erm2";
	public final String MAIN_MENU_ATTR = "main_menu";
	public final String SUB_MENU_ATTR = "sub_menu";
	public final String SUB_MENU1_ATTR = "sub_menu1";
	
	public final String MENU_DASHBOARD = "dashboard";
	public final String MENU_EXCHANGE_RATE = "exchange_rate";
	public final String MENU_EXTRACTION_MANAGE = "extraction_manage";
	public final String MENU_GENERATE_RATE = "generate_rate";
	public final String MENU_USER_MANAGEMENT = "user_mange";
	
	public final String MENU_EXCHANGE_RATE_MANUAL = "exchange_rate_by_manual";
	public final String MENU_EXCHANGE_RATE_MANUAL_RATE_MANAGE = "exchange_rate_by_manual_rate_manage";
	public final String MENU_EXCHANGE_RATE_MANUAL_LIST_MAINTAIN = "exchange_rate_by_manual_list_maintain";
	public final String MENU_EXCHANGE_RATE_AUTO = "exchange_rate_by_auto"; 
	public final String MENU_EXCHANGE_RATE_AUTO_EXTRACTION_MANAGE = "exchange_rate_by_auto_manage";
	public final String MENU_EXCHANGE_RATE_AUTO_HISTLOG_SCRAPED = "exchange_rate_by_auto_histlog_scraped";
	public final String MENU_EXCHANGE_RATE_AUTO_VIEW_ADJUST = "exchange_rate_by_auto_view_adjust";
	public final String SESSION_CURRENCY_LIST = "session_currency_list";
	public final String SESSION_CURRENCY_MAP = "session_currency_map";
	public final String SESSION_COUNTRY_LIST = "session_country_list";
	public final String SESSION_FILE_UPLOAD_BY_MANUAL = "fileUploadERManualForm";
	public final String SESSION_FILE_UPLOAD_BY_AUTO = "fileUploadERAuto";
	public final String SESSION_EXTRACTION_TIME_LIST = "session_extraction_time_list";
	public final String SESSION_EXTRACTION_TYPE_LIST = "session_extraction_type_list";
	public final String SESSION_EXTRACTION_DATE_LIST = "session_extraction_date_list";
	public final String SESSION_PAGE_TYPE_LIST = "session_page_type_list";
	public final String SESSION_CURRENCY_TYPE_LIST = "session_currency_type_list";
	public final String SESSION_STATUS_LIST = "session_status_list";
	public final String SESSION_FORMAT_DATE_LIST = "session_format_date_list";
	public final String SESSION_VALUE_LIST = "session_value_list";
	public final String SESSION_PYTHON_DIRECTORY = "session_python_directory";
	public final String SESSION_MANUAL_DIRECTORY = "session_manual_directory";
	public final String SESSION_CURRENCY_LIST_API = "session_currency_list_api";
	public final String SESSION_CURRENCY_LIST_API_SELECTED = "session_currency_list_api_selected";
	public final String SESSION_GENERATE_RATE_TIME_LIST = "session_generate_rate_time_list";
	public final String SESSION_USER = "session_user";
	public final String SESSION_AUTHOGROUP_LIST = "session_authogroup_list";
	
	public final String CHK_ON = "on";
	public final String MAX_RATE_STR = "9,999,999,999.99999";
	public final String MIN_RATE_STR = "0";
	public final Double MAX_RATE =new Double(UIUtil.prepareConvertStringToNumber(MAX_RATE_STR));
	public final Double MIN_RATE =new Double(MIN_RATE_STR);
	public final int DECIMAL_RATE =5;
	public final long MAX_USER_ID = 16L;
	public final long MIN_USER_ID = 8L;
	public final long MAX_PERSON_NAME = 50L;
	public final long MIN_PERSON_NAME = 1L;
	public final long MAX_PWD = 16L;
	public final long MIN_PWD = 8L;
	
	public final String ER_ORIGIN_AUTO = "AUTO";
	public final String ER_ORIGIN_MANUAL = "MANUAL";
	public final String RECORD_STS_ACTIVE = "A";
	public final String RECORD_STS_INACTIVE = "S";
	public final String RECORD_STS_DELETE = "D";
	public final String RECORD_STS_ACTIVE_CONTAIN = "Active";
	public final String RECORD_STS_INACTIVE_CONTAIN  = "Inactive";
	public final String RECORD_STS_DELETE_CONTAIN  = "Delete";
	
	public final String AUTHO_GROUP_ADMIN = "Administrator";
	
	public final String ATTR_USER_LOGON_ID = "logOnId";
	public final String ATTR_USER_FIRST_NAME = "firstName";
	public final String ATTR_USER_LAST_NAME = "lastName";
	public final String ATTR_USER_EMAIL_ADDR = "emailAddr";
	public final String ATTR_USER_COUNTRY_ID = "countryId";
	public final String ATTR_USER_GROUP_ID = "groupId";
	
	public final String USER_API_KEY = UIUtil.getSHA3("USER_API_KEY");
	 
	
	
}
