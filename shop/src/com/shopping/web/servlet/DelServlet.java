package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;

public class DelServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Connection conn = DBHelper.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setId(req.getParameter("id"));
		m.setDel("true");
		Boolean res = dao.update("deleteOne", m, conn);
		DBHelper.closeConnection(conn);
		JSONObject.fromObject(res);
		PrintWriter out = resp.getWriter();
		out.write(res.toString());
		out.flush();
		out.close();
	}
}
