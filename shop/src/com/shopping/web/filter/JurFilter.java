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
		String pages = (String) request.getParameter("page");
		String pageSizes = (String) request.getParameter("pageSize");
		if(pages == null || pages.length() == 0){
			pages = "1";
		}
		if(pageSizes == null || pageSizes.length() == 0){
			pageSizes = "20";
		}
		int page = Integer.valueOf(pages);
		int pageSize = Integer.valueOf(pageSizes);
		if(page < 1){
			page = 1;
		}
		if(pageSize < 1){
			pageSize = 20;
		}
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
