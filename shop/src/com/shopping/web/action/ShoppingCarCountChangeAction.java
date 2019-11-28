package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;

import com.shopping.dao.daoImpl.CartDaoImpl;

import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;

import com.shopping.web.form.ShoppingCarCountChangeForm;

public class ShoppingCarCountChangeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		Connection conn = DBHelper.getConnection();
		
		ShoppingCarCountChangeForm scccf = (ShoppingCarCountChangeForm)o;
		int count = Integer.parseInt(scccf.getCount());		
		int id = Integer.parseInt(scccf.getId());
		System.out.println(id+"--id");
		Cart c = new Cart();
		c.setId(id);		
		c.setNumber(count);		
		CartDao gd = new CartDaoImpl();
		gd.update("changeNumber", c, conn);		
		DBHelper.closeConnection(conn);
		return "";
	}

}
