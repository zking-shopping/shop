package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.MemberDao;
import com.shopping.dao.MemberStatisticsDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.MemberStatisticsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.util.DateHelper;

public class coreMemberServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public coreMemberServlet() {
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
		String totalMember = request.getParameter("totalMember");
		String coreMember = request.getParameter("coreMember");
		String secondMember = request.getParameter("secondMember");
		String edgeMember = request.getParameter("edgeMember");
		String month = request.getParameter("month");
		String coreTime = request.getParameter("coreTime");
		String coreCost = request.getParameter("coreCost");
		String edgeTime = request.getParameter("edgeTime");
		String edgeCost = request.getParameter("edgeCost");
		Connection conn = DBHelper.getConnection();
		MemberDao dao = new MemberDaoImpl();
		MemberStatisticsDao msdao = new MemberStatisticsDaoImpl();
		int coreTimeNum;
		int coreCostNum;
		int edgeTimeNum;
		int edgeCostNum;
		Date date = new Date();
		String now = String.valueOf((date.getMonth() + 1));
		if(coreTime == null || coreTime.length() == 0){
			coreTime = "1800";
			coreTimeNum = 1800;
		}else{
			coreTimeNum = Integer.parseInt(coreTime);
		}
		if(coreCost == null || coreCost.length() == 0){
			coreCost = "1000";
			coreCostNum = 1000;
		}else{
			coreCostNum = Integer.parseInt(coreCost);
		}
		if(edgeTime == null || edgeTime.length() == 0){
			edgeTime = "600";
			edgeTimeNum = 600;
		}else{
			edgeTimeNum = Integer.parseInt(edgeTime);
		}
		if(edgeCost == null || edgeCost.length() == 0){
			edgeCost = "200";
			edgeCostNum = 200;
		}else{
			edgeCostNum = Integer.parseInt(edgeCost);
		}
		if(coreCostNum < edgeCostNum){
			coreCostNum = edgeCostNum;
		}
		if(coreTimeNum < edgeTimeNum){
			coreTimeNum = edgeTimeNum;
		}
		if(month == null || month.length() == 0){
			month = now;
		}
		if(Integer.valueOf(month) > 12){
			month = "12";
		}
		if(Integer.valueOf(month) < 1){
			month = "01";
		}
		if(Integer.valueOf(month) > Integer.valueOf(now)){
			month = now;
		}
		int totalMemberNum;
		int coreMemberNum;
		int secondMemberNum;
		int edgeMemberNum;
		int avgCost;
		int avgTime;
		String year = DateHelper.getSimpleYear();
		if(month.equals(now)){
			if(month.length() < 2){
				month = "0"+month;
			}
			totalMemberNum = dao.selectCount(conn);
			coreMemberNum = dao.selectCoreMember(coreTimeNum, coreCostNum, conn);
			edgeMemberNum = dao.selectEdgeMember(edgeTimeNum, edgeCostNum, conn);
			secondMemberNum = totalMemberNum - coreMemberNum - edgeMemberNum;
			avgCost = dao.selectAvgCost(conn);
			avgTime = dao.selectAvgTime(conn);
		}else{
			if(month.length() < 2){
				month = "0"+month;
			}
			totalMemberNum = msdao.selectCount(year+month, conn);
			coreMemberNum = msdao.selectCoreMember(year+month, coreTimeNum, coreCostNum, conn);
			edgeMemberNum = msdao.selectEdgeMember(year+month, edgeTimeNum, edgeCostNum, conn);
			secondMemberNum = totalMemberNum - coreMemberNum - edgeMemberNum;
			avgCost = msdao.selectAvgCost(year+month, conn);
			avgTime = msdao.selectAvgTime(year+month, conn);
		}
		HttpSession session = request.getSession();
		session.setAttribute("totalMember", totalMemberNum);
		session.setAttribute("coreMember", coreMemberNum);
		session.setAttribute("secondMember", secondMemberNum);
		session.setAttribute("edgeMember", edgeMemberNum);
		session.setAttribute("month", month);
		session.setAttribute("coreTime", coreTimeNum);
		session.setAttribute("coreCost", coreCostNum);
		session.setAttribute("edgeTime", edgeTimeNum);
		session.setAttribute("edgeCost", edgeCostNum);
		session.setAttribute("avgCost", avgCost);
		session.setAttribute("avgTime", avgTime);
		DBHelper.closeConnection(conn);
		response.sendRedirect("/shop/admin/coreMember.jsp");
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
