package com.esynergy.erm.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.UIUtil;

@Service("previewExtractionService")
@Scope("prototype")
public class PreviewExtractionServiceImpl implements PreviewExtractionService {
	
	private List<String[]> resultFromFile;
	private String errorFromFile = "";
	private boolean isSuccessful;
	
	private String newLine = "";
	private String osName = System.getProperty("os.name");
	private String pythonDirectory = "";
	private String uuid;
	
	public void initialProcessBuilder() {
		if(osName.contains("Windows")) {
			newLine = "\r\n";
		}
		else {
			newLine = "\n";
		}
		isSuccessful = false;
		resultFromFile = new ArrayList<String[]>();
		uuid = "tempJvInfCall_" + UUID.randomUUID().toString().replace("-", ""); 

	}
	
	public void setPythonDirectory(String pd) {
		if(UIUtil.isEmptyOrNull(pythonDirectory)) {
			if(osName.contains("Windows")) {
				if(pd.endsWith("\\")) {
					pythonDirectory = pd;
				}
				else {
					pythonDirectory = pd + "\\";
				}
			}
			else {
				if(pd.endsWith("/")) {
					pythonDirectory = pd;
				}
				else {
					pythonDirectory = pd + "/";
				}
			}
		}
	}
	
	public void createInputFile(List<String> descriptionCssSelectors) {
		try {
			Writer writer = null;
			writer = new BufferedWriter(new OutputStreamWriter(new 
					FileOutputStream(pythonDirectory + uuid + ".txt"),"utf-8"));
			
			for (String item : descriptionCssSelectors) {
				 writer.write(item);
				 writer.write(newLine);
			}
			writer.close();			
		}
		catch (Exception e) {
			errorFromFile = e.getMessage() + " (Java createInputFile)";
			e.printStackTrace();
		}
	}
	
	public void callPythonPhantomJS() {
		Process p = null;
		try {
			ProcessBuilder process = null;
			if(osName.contains("Windows")) {
				process = new ProcessBuilder("python3", 
						pythonDirectory + "JavaInterfaceCall.py", pythonDirectory + uuid + ".txt");
			}
			else {
				process = new ProcessBuilder("/usr/bin/python3",
						pythonDirectory + "JavaInterfaceCall.py", pythonDirectory + uuid + ".txt");
			}
			
			process.redirectErrorStream(true);
			p = process.start();
			
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(p.getInputStream()));
			String s = null;
			while ((s = stdInput.readLine()) != null) {
//				System.out.println(s);
			}
			stdInput.close();
				
			p.waitFor();
			
			System.out.println(p.exitValue());
		}
		catch (Exception e) {
			errorFromFile = e.getMessage() + " (Java callPythonPhantomJS)";
		    e.printStackTrace();  
		}
		finally {
			if(p != null) {
				p.destroy();
			}
		}
	}
	
	public void readOutputFile() {
		
		try {
			List<String> result = new ArrayList<String>();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new 
					FileInputStream(pythonDirectory + uuid + ".txt"), "utf-8"));
			
			String s = null;
			while((s = reader.readLine()) != null) {
				result.add(s);
			}
			reader.close();
			
			if(result.size() == 0) {
				isSuccessful = false;
			}
			else if(result.get(0).equalsIgnoreCase("0")){
				isSuccessful = true;
				for(int i=0;i<result.size();i++) {
					if(i == 0) {
						continue;
					}
					else {
						String[] lineItem = result.get(i).split(",", -1);
						resultFromFile.add(lineItem);
					}
				}
			}
			else {
				isSuccessful = false;
				if(UIUtil.isEmptyOrNull(errorFromFile)) {
					if(result.size() > 1) {
						errorFromFile = result.get(1);
					}
					else {
						errorFromFile = "something went wrong.1";
					}
				}
			}
		}
		catch (Exception e) {
			errorFromFile = e.getMessage() + " (Java readOutputFile)";
			e.printStackTrace();
		}
	}
	
	public String getFieldValue(int line, int position) {
		if(!isSuccessful) {
			return "something went wrong.2";
		}
		
		if(line > resultFromFile.size()) {
			return "something went wrong.3";
		}
		else if(position > resultFromFile.get(line-1).length) {
			return "something went wrong.4";
		}
		else {
			return resultFromFile.get(line-1)[position-1];
		}
	}
	
	public String getErrorFromFile() {
		if(isSuccessful) {
			return "something went wrong.5";
		}
		else {
			return errorFromFile;
		}
	}
	
	public boolean getIsSuccessful() {
		return isSuccessful;
	}
	
	public void deleteOutputFile() {
		File outputFile = new File(pythonDirectory + uuid + ".txt");
		if(outputFile.isFile()) {
//			outputFile.delete();
		}
	}
}
