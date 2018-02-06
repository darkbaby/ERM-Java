package com.esynergy.erm.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;

public class UIUtil {
	public static boolean isEmptyOrNull(String str) {
		
		if(str==null || str.trim().length()==0 || str.isEmpty() || str.length()==0 || str.equals("")) {
			return true;
		}
		return false;
	}
	public static String prepareConvertStringToNumber(String val) {
		return val.replaceAll(",", "");
	}
	public static Set<Object> duplicateList(List<Object> list){
		Set<Object> rs = new HashSet<Object>();
		Set<Object> tmpSet = new HashSet<Object>();
		for(Object ob:list) {
			if(!tmpSet.add(ob))  
				rs.add(ob);
		}
		return rs;
	}
	public static Date setZoroTime(Date date){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.HOUR,0);
		now.set(Calendar.MINUTE,0);
		now.set(Calendar.SECOND,0);
		return now.getTime();
	}
	//Test
 	/*public static void main(String[] arg) {
 		System.out.println(getStrRandom(8));
 		System.out.println(getStrRandom(8));
 		System.out.println(getStrRandom(8));
 		System.out.println(getStrRandom(8));
 		System.out.println(getStrRandom(8));
 		for(char c='a';c<='z';c++){
 			System.out.print(c);
 		}
		String x = null;
		String x2 = null;
		String x3 = null;
		String x4 = null;
		x2 = "";
		x3 = " ";
		x4 = "1 ";
		System.out.println("===================================================================");
		System.out.println(isEmptyOrNull(x));
		System.out.println(isEmptyOrNull(x2));
		System.out.println(isEmptyOrNull(x3));
		System.out.println(isEmptyOrNull(x4));
		System.out.println("===================================================================");
	
 	} */
 	public static String getStrRandom(int length){
 	        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
 	        StringBuilder salt = new StringBuilder();
 	        Random rnd = new Random();
 	        while (salt.length() < length) { // length of the random string.
 	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
 	            salt.append(SALTCHARS.charAt(index));
 	        }
 	        String saltStr = salt.toString();
 	        return saltStr;

 	}
 	public static String getSHA3(String arg){
 		DigestSHA3 _cha3 = new SHA3.Digest512();
		byte[] _1stDigest = _cha3.digest(arg.getBytes());
		return Hex.toHexString(_1stDigest);
 	}
}
