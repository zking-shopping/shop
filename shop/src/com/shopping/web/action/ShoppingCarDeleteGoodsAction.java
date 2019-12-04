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


public class ShoppingCarDeleteGoodsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		String getId = request.getParameter("id");
		
		String id = getId;
		
		if(getId.contains(",")){
			
			id = getId.substring(1);
			
		}
		
      	String[] ids = id.split(",");
      	
      	Member m = (Member) request.getSession().getAttribute("member");
      	if(m==null){
      		Cookie[] cookies = request.getCookies();
      		for (int i = 0; i < ids.length; i++) {
      			
      			for (Cookie cookie : cookies) {
      				if(cookie.getName().length()>10){
      					if(ids[i].equalsIgnoreCase(cookie.getName().substring(10))){
        					
        					cookie.setMaxAge(0);
        					response.addCookie(cookie);
        				}
      				}
    				
    			}
			}
      		return null;
      	}
		
      	
		CartDao cd = new CartDaoImpl();
		Cart c = new Cart();
		
		Connection conn = DBHelper.getConnection();
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=""){
				c.setId(Integer.parseInt(ids[i]));
				System.out.println(ids[i]+"---");
				cd.deleteById(c, conn);
				c = null;
			}
			
		}
		DBHelper.closeConnection(conn);
		return null;
	}

}
