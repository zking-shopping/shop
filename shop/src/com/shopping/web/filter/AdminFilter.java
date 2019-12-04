package com.shopping.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Admin;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		Admin a = (Admin) session.getAttribute("Admin");
		if(a.getId() == null){
			response.sendRedirect("/adminLogin.jsp");
			return ;
		}
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
