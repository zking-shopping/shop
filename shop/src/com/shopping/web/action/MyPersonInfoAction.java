package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;

public class MyPersonInfoAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		Member member = (Member)request.getSession().getAttribute("member");
		Connection conn = DBHelper.getConnection();
		MemberDao m = new MemberDaoImpl();
		Object obj = m.selectById(member, conn);		
		return obj;
	}

}
