package com.esynergy.erm.service;

import java.util.ArrayList;
import java.util.List;

public class ScrapExtractionRunable implements Runnable {
	
	private ScrapExtractionService scrapExtractionService;
	private String setting_hdr_id; 
	private String delegateUser;
	
//	public ScrapExtractionRunable(ScrapExtractionService scrapExtractionService) {
//		ScrapExtractionService seService = (ScrapExtractionService) ctx.getBean("scrapExtractionService");
//		this.scrapExtractionService = scrapExtractionService;
//	}
	
	public ScrapExtractionRunable(ScrapExtractionService scrapExtractionService, String setting_hdr_id,
			String delegateUser) {
		this.scrapExtractionService = scrapExtractionService;
		this.setting_hdr_id = setting_hdr_id;
		this.delegateUser = delegateUser;
	}
	
	@Override
	public void run() {
		try {
//			Thread.sleep(30000);
//			if(setting_hdr_id != null) {
			List<String> params = new ArrayList<String>();
			params.add(setting_hdr_id);
			params.add(delegateUser);
			scrapExtractionService.callPythonPhantomJS(params);
//			}
//			else {
//				scrapExtractionService.callPythonPhantomJS();
//			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
