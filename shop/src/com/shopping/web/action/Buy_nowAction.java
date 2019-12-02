package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Buy_nowAction extends ActionFather{
	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
		int id = Integer.parseInt(request.getParameter("id"));
		
		return forward;
	}
      
}
