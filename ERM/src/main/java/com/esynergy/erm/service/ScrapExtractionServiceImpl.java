package com.esynergy.erm.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.web.action.MandatoryAsset;

@Service("scrapExtractionService")
//@Scope("prototype")
public class ScrapExtractionServiceImpl implements ScrapExtractionService {
	
	private boolean running;
	private String osName;
	private String pythonDirectory;
	
	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@PostConstruct
	private void initialScrapExtraction() {
		running = false;
		osName = System.getProperty("os.name");
		
		if(UIUtil.isEmptyOrNull(pythonDirectory)) {
			String pd = mandatoryAsset.getPythonDirectory();
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
	
	public void callPythonPhantomJS(List<String> params) {
		running = true;
		Process p = null;
		
		params.add(0, pythonDirectory + "Main.py");
		
		try {
			ProcessBuilder process = null;
			if(osName.contains("Windows")) {
				params.add(0, "python3");
				process = new ProcessBuilder(params);
//				process = new ProcessBuilder("python3", 
//						pythonDirectory + "Main.py", setting_hdr_id);
			}
			else {
				params.add(0, "/usr/bin/python3");
				process = new ProcessBuilder(params);
//				process = new ProcessBuilder("/usr/bin/python3",
//						pythonDirectory + "Main.py", setting_hdr_id);
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
			System.out.println(e.getMessage() + " (Java callPythonPhantomJS)");
		    e.printStackTrace();  
		}
		finally {
			if(p != null) {
				p.destroy();
			}
			running = false;
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
