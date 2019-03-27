import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.sampled.Line;

import java.util.Set;
import java.util.UUID;

public class Test {

	 
	public static void main(String[] arg) throws Exception {
		System.out.println("start");
		
		String newLine = System.getProperty("line.separator");
		
		Writer writer = null;
		writer = new BufferedWriter(new OutputStreamWriter(new 
				FileOutputStream("D:\\workspace\\python-batch\\python-scraping\\filename.txt"),"utf-8"));
		writer.write("https://www.bankofindia.co.in/english/ListCardRate.aspx");
		writer.write(newLine);
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(2)");
		writer.write(",");
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(4)");
		writer.write(",");
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(5)");
		writer.write(newLine);
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(2)");
		writer.write(",");
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(4)");
		writer.write(",");
		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(5)");
//		writer.write(newLine);
//		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(2)");
//		writer.write(",");
//		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(4)");
//		writer.write(",");
//		writer.write("#ctl00_ContentPlaceHolder1_dgmod > tbody > tr:nth-child(3) > td:nth-child(5)");
		writer.close();
		
		Process p = null;
		try {
//			final String uuid = UUID.randomUUID().toString().replace("-", "");
//		    System.out.println("uuid = " + uuid);
			
			
//			String pythonDirectory = "D:\\workspace\\python-batch\\python-scraping\\";
//			ProcessBuilder process = new ProcessBuilder("python3", 
//					pythonDirectory + "JavaInterfaceCall.py", pythonDirectory + "filename.txt");
//			process.redirectErrorStream(true);
//			p = process.start();
//						
//			BufferedReader stdInput = new BufferedReader(new 
//				     InputStreamReader(p.getInputStream()));
//			String s = null;
//			while ((s = stdInput.readLine()) != null) {
//			    System.out.println(s);
//			}
			
//			String cmd = "python3 " + pythonDirectory + "JavaInterfaceCall.py " + pythonDirectory + "filename.txt ";
//			process = Runtime.getRuntime().exec(cmd);
			
//			BufferedReader stdInput = new BufferedReader(new 
//				     InputStreamReader(process.getInputStream()));
//
//			BufferedReader stdError = new BufferedReader(new 
//			     InputStreamReader(process.getErrorStream()));
//		
//			System.out.println("Here is the standard output of the command:\n");
//			String s = null;
//			while ((s = stdInput.readLine()) != null) {
//			    System.out.println(s);
//			}
//
//			System.out.println("Here is the standard error of the command (if any):\n");
//			while ((s = stdError.readLine()) != null) {
//			    System.out.println(s);
//			}
			
//			p.waitFor();
				
//			System.out.println(p.exitValue());
		}
		catch (Exception e) {
		    e.printStackTrace();  
		}
		finally {
			p.destroy();
		}
		
		
		System.out.println("end");
	}
	
}
 
