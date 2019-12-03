package com.shopping.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;

public class JurFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String pageNumber = (String) request.getParameter("pageNumber");
		String pageSize = (String) request.getParameter("pageSize");
		if(pageNumber == null || pageNumber.length() == 0){
			pageNumber = "1";
		}
		if(pageSize == null || pageSize.length() == 0){
			pageSize = "20";
		}
		int number = Integer.valueOf(pageNumber);
		int size = Integer.valueOf(pageSize);
		if(number < 1){
			number = 1;
		}
		if(size < 1){
			size = 20;
		}
		request.setAttribute("pageNumber", number);
		request.setAttribute("pageSize", size);
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
