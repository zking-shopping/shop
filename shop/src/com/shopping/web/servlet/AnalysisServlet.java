package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.TypeDao;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.dao.daoImpl.TypeDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Type;
import com.shopping.util.DateHelper;

public class AnalysisServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AnalysisServlet() {
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
		String time = request.getParameter("time");
		String now = DateHelper.getSimpleDate();
		if(time == null || time.length() == 0){
			time = now;
		}
		if(Integer.valueOf(time) > Integer.valueOf(now)){
			time = now;
		}
		Connection conn = DBHelper.getConnection();
		GoodsStatisticsDao goodsStatisticsDao = new GoodsStatisticsImpl();
		TypeDao typeDao = new TypeDaoImpl();
		List<Object> list = typeDao.selectAll(new Type(), conn);
		Double max = 0d;
		Double avg = 0d;
		Double min = 0d;
		String maxKey = null;
		String minKey = null;
		double countClick = 0d;
		double countBuy = 0d;
		Boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			Type t = (Type) list.get(i);
			double click = goodsStatisticsDao.selectClickSumBySort(String.valueOf(t.getId()), time, conn);
			double buy = goodsStatisticsDao.selectBuySumBySort(String.valueOf(t.getId()), time, conn);
			countClick += click;
			countBuy += buy;
			if(buy == 0){
				continue;
			}
			Double result = click/buy;
			if(flag){
				max = result;
				min = result;
				maxKey = t.getName();
				minKey = t.getName();
				flag = false;
			}
			if(result > max){
				max = result;
				maxKey = t.getName();
			}
			if(result < min){
				min = result;
				minKey = t.getName();
			}
		}
		if(countBuy != 0){
			avg = countClick/countBuy;
		}
		DBHelper.closeConnection(conn);
		request.setAttribute("max", String.format("%.1f", max));
		request.setAttribute("avg", String.format("%.1f", avg));
		request.setAttribute("min", String.format("%.1f", min));
		request.setAttribute("maxKey", maxKey);
		request.setAttribute("minKey", minKey);
		request.setAttribute("time", time);
		request.getRequestDispatcher("/admin/analysis.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}

}
