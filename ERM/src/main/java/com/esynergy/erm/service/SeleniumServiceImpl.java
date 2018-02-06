package com.esynergy.erm.service;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;

interface CLibrary extends Library {
    public int chmod(String path, int mode);
}

@Service("seleniumService")
public class SeleniumServiceImpl implements SeleniumService {
	
	private WebDriver driver;

    private boolean isSuccessfullyScrap;
    
    private List<String> pageResultTag = new ArrayList<String>();
    
    public void initialWebDriver() {
//    	DesiredCapabilities DesireCaps = DesiredCapabilities.phantomjs();
    	DesiredCapabilities DesireCaps = new DesiredCapabilities();
//    	DesireCaps.setJavascriptEnabled(true);
    	DesireCaps.setCapability("takesScreenshot", false);
    	String projectPath = System.getProperty("user.dir");
    	String osName = System.getProperty("os.name");
//    	System.out.println(projectPath);
//    	System.out.println(osName);

    	try {
    		String path = this.getClass().getClassLoader().getResource("").getPath();
        	String fullPath = URLDecoder.decode(path, "UTF-8");
        	String pathArr[] = fullPath.split("/WEB-INF/classes/");
        	System.out.println(fullPath);
        	System.out.println(pathArr[0]);
        	projectPath = pathArr[0]; 
    	}
    	catch (UnsupportedEncodingException e) {
    		System.out.println(e.getMessage() + e.getStackTrace());
		}
    	

        File phantomjsEXE;
//        File pathToBinaryFirefox;
        if(osName.contains("Windows")) {
//        	phantomjsEXE  = new File(projectPath + "envi/phantomjs.exe");
//        	phantomjsEXE  = new File("envi/phantomjs.exe");
            DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
            		projectPath + "/envi/phantomjs.exe");
//            pathToBinaryFirefox = new File(projectPath + "/envi/geckodriver.exe");
//            System.setProperty("webdriver.gecko.driver", projectPath + "/envi/geckodriver.exe");
        }
        else {
        	CLibrary libc = (CLibrary) Native.loadLibrary("c", CLibrary.class);
        	libc.chmod(projectPath + "/envi/phantomjs", 0755);
            DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
            		projectPath +	"/envi/phantomjs");
            
//        	libc.chmod(projectPath + "/envi/geckodriver", 0755);
//            pathToBinaryFirefox = new File(projectPath + "/envi/geckodriver");
            //        	phantomjsEXE = new File("envi/phantomjs");
        }
        
//        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinaryFirefox);
//        FirefoxProfile firefoxProfile = new FirefoxProfile();    
        
//    	this.driver = new FirefoxDriver(); 
        
        System.out.println("phantom ja");
        this.driver = new PhantomJSDriver(DesireCaps);
        this.driver.manage().deleteAllCookies();
        this.driver.manage().window().setSize(new Dimension(1600, 900));
        System.out.println(this.driver.manage().window().getSize().toString());
        this.isSuccessfullyScrap = false;
        this.driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(500,TimeUnit.MILLISECONDS);
    }
    
    public void quitWebDriver() {
    	if(this.driver != null) {
        	this.driver.quit();
    	}
    }

    public void scrapPage(String url){
        try{
            this.driver.get(url);
//            System.out.println("start webdriver wait");
//            WebDriverWait wait = new WebDriverWait(this.driver, 20);
//            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
//            System.out.println("end webdriver wait");
//            System.out.println(element.getAttribute("innerHTML") + " & " + element.getText() );
//            Thread.sleep(5000);
            isSuccessfullyScrap = true;
        }
        catch (TimeoutException ex){
        	System.out.println("timeout triggered");
            isSuccessfullyScrap = false;
        }
        catch(Exception ex) {
        	System.out.println("thorw Exception");
            isSuccessfullyScrap = false;
        }
    }
    
    public String getFieldData(String css,boolean isNumberFormat) {
    	try {
    		if(css == null || css.trim().equalsIgnoreCase("")) {
    			return "";
    		}
    		else {
            	WebElement element = this.driver.findElement(By.cssSelector(css));
            	String result = element.getAttribute("innerHTML").trim().replace(",", "");
            	if(result.equalsIgnoreCase("")) {
            		return "";
//                	return "No Data";
            	}
            	else{
            		return result;
//            		if(isNumberFormat) {
//            			try {
//            				double testParse = Double.parseDouble(result);
//                    		return result;
//            			}
//            			catch (Exception e) {
//							return "Data Incorrect";
//						}
//            		}
//            		else {
//            			try {
//            				double testParse = Double.parseDouble(result);
//                    		return "Data Incorrect";
//            			}
//            			catch (Exception e) {
//							return result;
//						}
//            		}
            	}
    		}
    	}
    	catch (NoSuchElementException e) {
    		return "";
//    		return "No Data";
		}
    }
    
    public String getFieldData(String currency, int range) {
    	
//    	try {
//    		int testParse = Integer.parseInt(range);
//    	}
//    	catch (Exception e) {
//			return "Need number format";
//		}
    	
    	for (int i=0;i<pageResultTag.size();i++) {
			if(pageResultTag.get(i).equals(currency)) {
				return pageResultTag.get(i+range);		
			}
		}
    	
    	return "";
    }
    
    public String getPageResult(){
        if(isSuccessfullyScrap){
            return this.driver.getPageSource();
        }
        else{
            return "";
        }
    }

    public void dividePageResult(String tag){
        if(isSuccessfullyScrap){

            List<WebElement> listWebElement = this.driver.findElements(By.tagName(tag));

            System.out.println(listWebElement.size());

            String result = "";

            for(WebElement item : listWebElement){
                result = item.getText().trim();
                pageResultTag.add(result);
            }
        }
        else{
            this.pageResultTag = new ArrayList<String>();
        }
    }

	public boolean isSuccessfullyScrap() {
		return isSuccessfullyScrap;
	}

	public void setSuccessfullyScrap(boolean isSuccessfullyScrap) {
		this.isSuccessfullyScrap = isSuccessfullyScrap;
	}
}

