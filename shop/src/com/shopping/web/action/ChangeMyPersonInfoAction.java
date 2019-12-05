package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.web.form.ChangeMyPersonInfoForm;

public class ChangeMyPersonInfoAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		ChangeMyPersonInfoForm cmpif = (ChangeMyPersonInfoForm)o;
		MemberDao md = new MemberDaoImpl();
		Connection conn = DBHelper.getConnection();
		Boolean flag = md.updatePersonInfo(cmpif, conn);
		String s = "flag";
		if(flag){
			s = "true";
		}
		return s;
	}

}
