package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.GoodsStatisticsDto;

public class RankServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RankServlet() {
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
		String clickPage = request.getParameter("clickPage");
		String buyPage = request.getParameter("buyPage");
		if(clickPage == null || clickPage.length() == 0){
			clickPage = "1";
		}
		if(buyPage == null || buyPage.length() == 0){
			buyPage = "1";
		}
		int clickPageNumber = Integer.valueOf(clickPage);
		int buyPageNumber = Integer.valueOf(buyPage);
		if(clickPageNumber < 1){
			clickPageNumber = 1;
		}
		if(buyPageNumber < 1){
			buyPageNumber = 1;
		}
		if(clickPageNumber > 10){
			clickPageNumber = 10;
		}
		if(buyPageNumber > 10){
			buyPageNumber = 10;
		}
		Connection conn = DBHelper.getConnection();
		GoodsStatisticsDao dao = new GoodsStatisticsImpl();
		List<GoodsStatisticsDto> clickList = dao.selectSortByClick(clickPageNumber, 5, conn);
		request.setAttribute("GoodsStatisticsClickList", clickList);
		
		List<GoodsStatisticsDto> buyList = dao.selectSortByBuyNumber(buyPageNumber, 5, conn);
		request.setAttribute("GoodsStatisticsBuyNumberList", buyList);
		
		DBHelper.closeConnection(conn);
		
		request.setAttribute("clickPageNumber", clickPageNumber);
		request.setAttribute("buyPageNumber", buyPageNumber);
		request.getRequestDispatcher("/admin/rank.jsp").forward(request, response);
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
