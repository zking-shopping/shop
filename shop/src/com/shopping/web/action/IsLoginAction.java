package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsLoginAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		Object obj = request.getSession().getAttribute("member");
		if(obj!=null){
			String balanceGooods = request.getParameter("balanceGooods");
//			request.getSession().setAttribute("balanceGooods", balanceGooods);
			return "success";
		}
		return "error";
	}

}
