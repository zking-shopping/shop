package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Cart;

public class CartDaoTest {
	@Test
	public void testCart(){
		Connection conn = DBHe.getConnection();
		Cart c = new Cart();
		c.setId(1);
		c.setNumber(100);
		CartDao dao = new CartDaoImpl();
		Boolean res = dao.update("changeNumber", c, conn);
		System.out.println(res);
	}
}
