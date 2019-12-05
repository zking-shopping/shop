package com.shopping.web.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.DetailOrderDao;
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.DetailOrderDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Member;
import com.shopping.pojo.Order;


public class PersonInfoAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		Member m = (Member)request.getSession().getAttribute("member");
		if(m==null){
			return "";
		}
		String state = request.getParameter("state");
		String id = m.getId();
		Connection conn = DBHelper.getConnection();
		OrderDao order = new OrderDaoImpl();//订单表
		DetailOrderDao detailOrder = new DetailOrderDaoImpl();//详细订单表
	    
		List<Order> orders = order.selectByMemberId(id ,null, conn);
        if(state!=null){
        	orders = order.selectByMemberId(id ,state, conn);
	    }
		ArrayList<Object> detailOrderss = new ArrayList<Object>();
		for (Order object : orders) {		
			
			List<DetailOrder> detailOrders = detailOrder.selectByOrderId(""+object.getId(), conn);
			detailOrderss.add(detailOrders);
		}
		
		list.add(orders);
		list.add(detailOrderss);
		DBHelper.closeConnection(conn);
		return list;
	}

}
