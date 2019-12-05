package com.shopping.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;


public class LoginMapListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		ServletContext application = arg0.getServletContext();
		application.removeAttribute("loginMap");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		Map<String, HttpSession> loginMap = new HashMap<String, HttpSession>();
	    ServletContext application = arg0.getServletContext();
	    application.setAttribute("loginMap", loginMap);
	    
	    
	}

}
