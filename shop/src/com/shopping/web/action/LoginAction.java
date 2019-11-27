package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;
import com.shopping.web.form.FormFather;

public class LoginAction extends ActionFather{

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
		String forward = null;
		MemberDao md = new MemberDaoImpl();
        Connection conn = DBHelper.getConnection();
  		MemberDao dao = new MemberDaoImpl();
  		Member m = new Member();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		m.setUsername(request.getParameter("username"));
  		m.setPassword(request.getParameter("password"));
  		//根据名字和密码获得的member全部信息
  		Member member = (Member) dao.select("selectForLogin", m, conn);
  		try {
			PrintWriter out = response.getWriter();
			if(member.getPassword()!=null){
	  			request.getSession().setAttribute("member", member);
	  			forward = "success";
	  		}else{
	  			forward = "error";
	  		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		return forward;
	}

}
