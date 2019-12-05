package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.AdminDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.AdminDaoImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Admin;
import com.shopping.web.filter.AdminLoginFilter;

public class adminLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public adminLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBHelper.getConnection();
		String code = request.getParameter("code");
		if(AdminLoginFilter.code.equals(code)){
			AdminLoginFilter.code = null;
			AdminDao dao = new AdminDaoImpl();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Admin admin = new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			Admin ad = (Admin) dao.select("selectForLogin", admin, conn);
			if(ad.getId() != null){
				HttpSession session = request.getSession();
				session.setAttribute("Admin", ad);
				ServletContext application = request.getServletContext();
				Map<String, HttpSession> map = (Map<String, HttpSession>) application.getAttribute("loginMap");
				session.setAttribute("online", map.size());
				MemberDao mDao = new MemberDaoImpl();
				int allMember = mDao.selectMax(1, conn);
				session.setAttribute("allMember", allMember);
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				return ;
			}else{
				response.sendRedirect("/shop/adminLogin.jsp");
				return ;
			}
		}else{
			response.sendRedirect("/shop/adminLogin.jsp");
		}
		DBHelper.closeConnection(conn);
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
