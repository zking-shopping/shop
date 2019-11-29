package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;


public class ShoppingCarDeleteGoodsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		String id = request.getParameter("id").substring(1);
      	String[] ids = id.split(",");
		
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
