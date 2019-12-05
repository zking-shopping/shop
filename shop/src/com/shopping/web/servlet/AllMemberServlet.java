package com.shopping.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;

public class AllMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminPageNumber = request.getParameter("adminPageNumber");
		String adminPageSize = request.getParameter("adminPageSize");
		if(adminPageNumber == null || adminPageNumber.length() == 0){
			adminPageNumber = "1";
		}
		if(adminPageSize == null || adminPageSize.length() == 0){
			adminPageSize = "5";
		}
		int number = Integer.parseInt(adminPageNumber);
		int size = Integer.parseInt(adminPageSize);
		if(number < 1){
			number = 1;
		}
		if( size <= 0){
			size = 3;
		}
		Connection conn = DBHelper.getConnection();
		MemberDao dao = new MemberDaoImpl();
		int max = dao.selectMax(size, conn);
		if( number > max){
			number = max;
		}
		List<Member> allMember = dao.selectByPages(number, size, conn);
		request.setAttribute("allMember", allMember);
		request.setAttribute("adminPageNumber", number+"");
		request.setAttribute("adminPageSize", size+"");
		request.setAttribute("max", max);
		DBHelper.closeConnection(conn);
	    request.getRequestDispatcher("/admin/allMember.jsp").forward(request, response);
	}
}
