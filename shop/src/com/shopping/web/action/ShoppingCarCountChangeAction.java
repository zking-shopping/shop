package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Member;
import com.shopping.web.form.ShoppingCarCountChangeForm;

public class ShoppingCarCountChangeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		Connection conn = DBHelper.getConnection();
		
		String getCount = request.getParameter("count");             	
     	String getId = request.getParameter("id");
		int count = Integer.parseInt(getCount);		
		int id = Integer.parseInt(getId);
		
		Member m = (Member) request.getSession().getAttribute("member");
		if(m==null){
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().length()>10){
					if(getId.equalsIgnoreCase(cookie.getName().substring(10))){
						
						String value = cookie.getValue();
						cookie.setValue(value.split("=-=")[0]+"=-="+count+"=-="+value.split("=-=")[2]);			
						response.addCookie(cookie);
						return null;
					}
				}
				
			}
		}
		
		Cart c = new Cart();
		c.setId(id);		
		c.setNumber(count);		
		CartDao gd = new CartDaoImpl();
		gd.update("changeNumber", c, conn);		
		DBHelper.closeConnection(conn);
		return null;
	}

}
