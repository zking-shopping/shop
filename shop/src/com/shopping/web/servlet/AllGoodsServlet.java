package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.ColorDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.ColorDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.GoodsDto;
import com.shopping.pojo.Color;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;

public class AllGoodsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AllGoodsServlet() {
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
			adminPageSize = "5";
		}
		Connection conn = DBHelper.getConnection();
		GoodsDao dao = new GoodsDaoImpl();
		ColorDao dao2 = new ColorDaoImpl();
		int max = dao.selectMax(size, conn);
		if( number > max){
			number = max;
		}
		List<GoodsDto> list = dao.selectGoodsDto(number, size, conn);
		
		request.setAttribute("allGoods", list);
		request.setAttribute("adminPageNumber", number+"");
		request.setAttribute("adminPageSize", size+"");
		request.setAttribute("max", max);
		DBHelper.closeConnection(conn);
	    request.getRequestDispatcher("/admin/allGoods.jsp").forward(request, response);
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
