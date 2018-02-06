package com.esynergy.erm.service;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface SeleniumService {

//		public String getPreviewData(String[] detail);
//		public String getPreviewData2(String currency, String[] detail);
		public void scrapPage(String url);
		public void initialWebDriver();
		public void quitWebDriver();
		public void dividePageResult(String tag);
		public boolean isSuccessfullyScrap();
		public String getFieldData(String cssSelector,boolean isNumberFormat);
		public String getFieldData(String currency, int range);
}
