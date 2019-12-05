package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Order;

public class ImmediatePaymentAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");

	  	String id = request.getParameter("id");
	  	Connection conn = DBHelper.getConnection();
	  	OrderDao od = new OrderDaoImpl();
	  	Order order = new Order();
	  	order.setId(Integer.parseInt(id));
	  	order.setState("2");
	  	od.update("updateState", order, conn);
	  	
	  	DBHelper.closeConnection(conn);
		return null;
	}

}
