package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            MemberDao md = new MemberDaoImpl();
            Connection conn = DBHelper.getConnection();
    		MemberDao dao = new MemberDaoImpl();
    		Member m = new Member();
    		m.setUsername(request.getParameter("username"));
    		m.setPassword(request.getParameter("password"));
    		Member member = (Member) dao.select("selectForLogin", m, conn);
    		System.out.println("密码是"+member.getPassword());
    		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
