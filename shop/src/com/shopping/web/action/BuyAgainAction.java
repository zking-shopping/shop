package com.shopping.web.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.DetailOrderDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.DetailOrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Member;

public class BuyAgainAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		Connection conn = DBHelper.getConnection();
		Member m = (Member)request.getSession().getAttribute("member");
		if(m==null){
			return "error";
		}
		String memberId = m.getId();
		String id = request.getParameter("id");//得到订单id,在数据库通过订单id找到订单下的商品，并加入购物车
		DetailOrderDao detailOrder = new DetailOrderDaoImpl();//详细订单表
		List<DetailOrder> detailOrders = detailOrder.selectByOrderId(id, conn);
		System.out.println(detailOrders.size());
		CartDao cd = new CartDaoImpl();
		for (DetailOrder detailOrder2 : detailOrders) {
			Cart c = new Cart();
			c.setMemberId(memberId);
			c.setColorId(detailOrder2.getColorId());
			c.setGoodsColor(detailOrder2.getGoodsColor());
			c.setGoodsId(detailOrder2.getGoodsId());
			c.setGoodsName(detailOrder2.getGoodsName());
			c.setNumber(Integer.parseInt(detailOrder2.getNumber()));
			c.setPrice(detailOrder2.getPrice());
			c.setUrl(detailOrder2.getUrl());
			cd.insert(c, conn);
			c = null;
		}	
		
		DBHelper.closeConnection(conn);
		return "success";
	}

}
