package com.shopping.web.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.AddressDao;
import com.shopping.dao.DetailOrderDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.dao.daoImpl.DetailOrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;
import com.shopping.pojo.DetailOrder;

public class PersonInfoShowDetailsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {

		Connection conn = DBHelper.getConnection();
		List<Object> list = new ArrayList<Object>();
		//查到相关产品信息  并加到list集合中
		String id = request.getParameter("id");
		DetailOrderDao detailOrder = new DetailOrderDaoImpl();//详细订单表
		List<DetailOrder> detailOrders = detailOrder.selectByOrderId(id, conn);
		list.add(detailOrders);
		//查到相关收货人地址信息  并加到list集合中
		AddressDao ad = new AddressDaoImpl();
		Address a = new Address();
		a.setId(Integer.parseInt(id));
		Object obj = ad.selectById(a, conn);
		list.add(obj);
		
		DBHelper.closeConnection(conn);
		return list;
	}

}
