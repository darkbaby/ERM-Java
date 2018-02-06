package com.esynergy.erm.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.model.ob.AuthorizePermission;

public class ValidatorUtil implements ICommonContains {
	private static final String PWD_PATTERN = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";
	private static final String USER_ID_PATTERN = "^[a-zA-Z0-9]+$";
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
	private static final char SPACE = ' ';
	public static boolean checkString(String val,boolean isRequire) {
		String str = val==null?val:val.trim();
		if(isRequire && UIUtil.isEmptyOrNull(str)) 
			return true;
		return false;
	}
	public static boolean checkString(String val,boolean isRequire,long min,long max) {
		String str =val==null?val:val.trim();
		if(isRequire && UIUtil.isEmptyOrNull(str)) 
			return true;
		if(str.length() < min || str.length() > max)  
			return true;
		return false;
	}
	public static boolean checkAmount(String val,Double min,Double max,int decimazArg) {
 		String valStr = UIUtil.prepareConvertStringToNumber(val);
 		int y = valStr.indexOf(".");
 		if(y>=0) {
	 		String decimaz = valStr.substring(y-1, valStr.length());
	 		int lengthOfDecimal = decimaz.length();
	 		while(lengthOfDecimal-(decimazArg+1) <=0) {
	 			valStr = valStr+"0";
	 			lengthOfDecimal++;
	 		}
 		}
 		Pattern p=Pattern.compile("^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{"+decimazArg+"})?$");
 		Matcher m = p.matcher(valStr);
		if(!m.matches())  return true;
		
		Double valDouble = Double.parseDouble(valStr);
	    if(valDouble<min) return true;
	    if(valDouble>max) return true;

		return false;
	}
	public static boolean checkPwd(String val){
		Pattern  p = Pattern.compile(PWD_PATTERN);
		Matcher m = p.matcher(val);
		if(!m.matches())  return true;
		return false;
	}
	public static boolean checkUserId(String val){
		 Pattern pattern = Pattern.compile(USER_ID_PATTERN);
		Matcher mat = pattern.matcher(val);
		if(!mat.matches()) return true; 
		return false;
	}
	public static boolean findSpace(String val){
		if(!UIUtil.isEmptyOrNull(val)){
			for(int i=0;i<val.length();i++){
				if(val.charAt(i)==SPACE) return true;
			}
		}
		return false;
	}
	public static boolean checkEmail(String val){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher mat = pattern.matcher(val);
		if(!mat.matches())return true; 
		return false;
	}
	public static boolean checkPermission(IUser user,String function){
		if(user!=null){
    		AuthorizeGroup group = user.getAuthorizeGroup();
    		if(group!=null && group.getPermissionList()!=null && group.getPermissionList().size()>0){
    			for(AuthorizePermission per : group.getPermissionList()){
    				if( per.getAuthorizeFunction().getFunctionName().equals("Admin") ||
    						function.equals(per.getAuthorizeFunction().getFunctionName())){
    					return true;
    				}
    			}
    		}
		}
    	return false;
	}
	public static boolean checkPermission(IUser user,String function,String createUserName){
		if(!createUserName.equals(user.getLogOnId())){
			return false;
		}else{
			return checkPermission( user, function);
		}
		
		 
	}
	//Test
 	/*  public static void main(String[] arg) {
  
 		System.out.println(checkEmail("ampol@a.com"));
 		 
 		System.out.println(checkUserId("100199AaaZzxRtb"));
 		System.out.println(checkUserId("88_gg@//"));
 	 	 
 	String valStr ="9,999,999,999.99999";  
	 		System.out.println(checkAmount(valStr,0d,new Double(StringUIUtil.prepareConvertStringToNumber(valStr)),5)); 
		String x = null;
		String x2 = null;
		String x3 = null;
		String x4 = null;
		String x5 = null;
		String x6 = null;
		String x7 = null;
		String x8 = null;
		String x9 = null;
		x = null;
		x2 = "";
		x3 = "1";
		x4 = "1 ";
		x5="12345";
		x6="123456";
		x7 = "";
		x8 = "12";
		x9 = "123";
		System.out.println("===================================================================");
		System.out.println("true="+checkString(x,true,0,5)); 
		System.out.println("true="+checkString(x2,true,2,5)); 
		System.out.println("true="+checkString(x3,true,2,5)); 
		System.out.println("false="+checkString(x4,true,0,5));
		System.out.println("false="+checkString(x5,true,0,5));
		System.out.println("true="+checkString(x6,true,0,5));
		System.out.println("false="+checkString(x7,false,0,5));
		System.out.println("false="+checkString(x8,false,2,5));
		System.out.println("false="+checkString(x9,false,2,5));
		System.out.println("===================================================================");
	}  	 
	*/
}
