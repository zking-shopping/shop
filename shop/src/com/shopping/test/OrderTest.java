package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Order;

public class OrderTest {
	@Test
	public void testOrderDelete(){
		Connection conn = DBHe.getConnection();
		OrderDao dao = new OrderDaoImpl();
		Order o = new Order();
		o.setId(1);
		o.setDel("true");
		Boolean res = dao.update("deleteOne", o, conn);
		System.out.println(res);
	}
	
	@Test
	public void testOrderUpdateState(){
		Connection conn = DBHe.getConnection();
		OrderDao dao = new OrderDaoImpl();
		Order o = new Order();
		o.setId(1);
		o.setState("3");
		Boolean res = dao.update("updateState", o, conn);
		System.out.println(res);
	}
}
