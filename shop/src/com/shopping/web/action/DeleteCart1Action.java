package com.shopping.web.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCart1Action extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		  String forward = null;
		    String id = request.getParameter("id");
		     id = "goodsToCar"+id+"1";
		     
		    
		    Cookie[] cookies = request.getCookies();
		    for (Cookie cookie : cookies) {
				 if(id.equals(cookie.getName())){
					 System.out.println("需要删除的购物==========="+id);
					 cookie.setMaxAge(0);
					 response.addCookie(cookie);
				 }
			}
		    forward = "success";
          return forward;
	}

}
