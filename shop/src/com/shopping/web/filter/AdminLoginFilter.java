package com.shopping.web.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements Filter{
	public static String code = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		HttpSession session = request.getSession();
		String[] str = {"1","2","3","4","5","6","7","8","9","0"};
		StringBuffer sb = new StringBuffer();
		Random r  = new Random();
		for (int i = 0; i < 4; i++) {
			int num = r.nextInt(str.length);
			sb.append(str[num]);
		}
		String newCode = sb.toString();
		session.setAttribute("AdminCode", newCode);
		code = newCode;
		arg2.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
