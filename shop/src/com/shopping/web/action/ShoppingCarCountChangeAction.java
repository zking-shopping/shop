package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.web.form.FormFather;
import com.shopping.web.form.ShoppingCarCountChangeForm;

public class ShoppingCarCountChangeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
		Connection conn = DBHelper.getConnection();
		
		ShoppingCarCountChangeForm scccf = (ShoppingCarCountChangeForm)ff;
		int count = Integer.parseInt(scccf.getCount());
		
		int id = Integer.parseInt(scccf.getId());
		
		
		Cart c = new Cart();
		c.setId(id);
		
		c.setNumber(count);
		
		CartDao gd = new CartDaoImpl();
		
		gd.updateExId(c, conn);
		DBHelper.closeConnection(conn);
		return "";
	}

}
