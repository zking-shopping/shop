package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Member;

public class DeleteCart2Action extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		        String forward = null;
		        int id = Integer.parseInt(request.getParameter("id"));
		        //根据goodsId删除对应的用户的购物车商品
		       CartDao cd = new CartDaoImpl();
		       Connection conn = DBHelper.getConnection();
		        Cart cart = new Cart();
		        cart.setId(id);
		        System.out.println(id+"=========商品id");
		        cd.deleteById(cart, conn);
		        DBHelper.closeConnection(conn);
		        forward = "success";
		        return forward;
	}

}
