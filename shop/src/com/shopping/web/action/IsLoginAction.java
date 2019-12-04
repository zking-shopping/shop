package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsLoginAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
		
		Object obj = request.getSession().getAttribute("member");
		if(obj!=null){
			String balanceGooods = request.getParameter("balanceGooods");
			if(balanceGooods!=null){
//				request.setAttribute("balanceGooods", balanceGooods);
				request.getSession().setAttribute("balanceGooods", balanceGooods);		
			}
			return "success";
		}
		return "error";
	}

}
