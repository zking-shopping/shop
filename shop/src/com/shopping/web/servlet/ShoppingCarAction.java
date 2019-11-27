package com.shopping.web.servlet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.web.action.ActionFather;
import com.shopping.web.form.FormFather;

public class ShoppingCarAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
		System.out.println("enter shoppingcaraction");
		Connection conn = DBHelper.getConnection();
		CartDao cd = new CartDaoImpl();
		ArrayList<Cart> list = (ArrayList<Cart>)cd.selectByMemberId("", conn);
        DBHelper.closeConnection(conn);
        System.out.println("end shoppingcaraction");
		return list;
	}

}
