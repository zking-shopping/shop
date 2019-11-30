package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IntroductionAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
//		String src = request.getParameter("src");
//		int first = src.lastIndexOf("/");
//		int end = src.indexOf("-");
//		String index = src.substring(first, end);
//	    int num = Integer.parseInt(index);
	    //根据截取到的数字查询数据库获取相关的详情图片
	   return null;
	}
   
}
