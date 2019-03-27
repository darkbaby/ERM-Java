package com.esynergy.erm.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class GlobalMessagesListener implements ServletContextListener {
	 
	  private static final String DEFAULT_RESOURCE = "global";

	  public void contextInitialized(ServletContextEvent arg0) {
	    LocalizedTextUtil.addDefaultResourceBundle(DEFAULT_RESOURCE);
	  }

	  public void contextDestroyed(ServletContextEvent arg0) {
	  }
}