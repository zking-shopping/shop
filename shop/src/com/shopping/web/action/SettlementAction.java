package com.shopping.web.action;

import java.sql.Connection;
import java.sql.SQLException;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.DetailOrderDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.DetailOrderDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.SimpleGoodsDto;
import com.shopping.pojo.Cart;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Order;
import com.shopping.util.DateHelper;
import com.shopping.util.OrderNumberHelper;
import com.shopping.util.ParsingObject;

public class SettlementAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
  		//获值addressId=43&goods0={"id":"1","num":"2"}&goods1={"id":"3","num":"1"}&goodsNum=2&total=374
  		//获得会员的id并生成一个随机的订单号
		Member m = (Member)request.getSession().getAttribute("member");
  		String orderNumber = OrderNumberHelper.getOrderNumber();
		String memberId = m.getId();
	  	int goodsNum = Integer.parseInt(request.getParameter("goodsNum"));
	  	
	  	Connection conn = DBHelper.getConnection();
	  	OrderDao od = new OrderDaoImpl();
	  	GoodsDao gd = new GoodsDaoImpl();
	  	CartDao cd = new CartDaoImpl();
	  	DetailOrderDao dod = new DetailOrderDaoImpl();
		//生成一条订单
	  	Order order = new Order();
	  	try {
			conn.setAutoCommit(false);
	  		if(goodsNum!=0){
	  	  		//给订单加属性
	  			order.setMemberId(memberId);
	  	  		order.setAddressId(Integer.parseInt(request.getParameter("addressId")));
	  	  		order.setOrderNumber(orderNumber);
	  	  		order.setTotal(request.getParameter("total"));
	  	  		order.setTime(DateHelper.getComplexDate());
	  	  		order.setState("1");
	  	  		order.setDel("false");
	  			od.insert(order, conn);
	  			//获得订单号
	  			int orderId = od.selectIdByMemberAndOrderNumber(order, conn);
	  			order.setId(orderId);
	  			if(orderId!=-1){
		  			//生成详细的订单
		  			for (int i = 0; i < goodsNum; i++) {
						String goods = request.getParameter("goods"+i);
						SimpleGoodsDto sdg = ParsingObject.getGoodsObject(goods);
						int goodsId = sdg.getGoodsId();
						int number = sdg.getNumber();

						//查询商品信息
						Cart cart = new Cart();
						cart.setId(sdg.getId());
						System.out.println(sdg.getId());
						Cart idCart = (Cart) cd.selectById(cart, conn);
						
						//生成一条详细订单的信息
						DetailOrder dos = new DetailOrder();
						dos.setOrderId(orderId);
						dos.setGoodsId(goodsId);
						dos.setGoodsName(idCart.getGoodsName());
						dos.setUrl(idCart.getUrl());
						dos.setPrice(idCart.getPrice());
						dos.setNumber(String.valueOf(number));
						dos.setColorId(idCart.getColorId());
						dos.setGoodsColor(idCart.getGoodsColor());
						//插入详细订单表
						dod.insert(dos, conn);
					}
		  			conn.commit();
	  			}
	  		}
		} catch (SQLException e) {
			try {
				//回滚
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(conn);
		}
  		return order;
	}

}
