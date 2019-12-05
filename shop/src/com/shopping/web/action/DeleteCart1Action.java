package com.shopping.web.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCart1Action extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		  String forward = null;
		    String goodsId = request.getParameter("goodsId");
		    String id = "goodsToCar"+goodsId+"1";
		    Cookie[] cookies = request.getCookies();
		    for (Cookie cookie : cookies) {
				 if(cookie.getName().equals(id)){
					 cookie.setMaxAge(0);
				 }
			}
		    forward = "success";
          return forward;
	}

}
