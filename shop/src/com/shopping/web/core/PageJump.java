package com.shopping.web.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageJump {
	
	public void forword(HttpServletRequest request, HttpServletResponse response,String forwordPage,String jumptype) throws IOException, ServletException{
		if(jumptype=="true"){
			response.sendRedirect(forwordPage);
		}
		else{
			request.getRequestDispatcher(forwordPage).forward(request, response);
		}
	}

}
