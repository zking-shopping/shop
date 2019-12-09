package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.AdminDao;
import com.shopping.dao.daoImpl.AdminDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Admin;

public class AdminDaoTest {
	@Test
	public void testSelectForLogin(){
		Connection conn = DBHe.getConnection();
		AdminDao dao = new AdminDaoImpl();
		Admin a = new Admin();
		a.setUsername("gyh");
		a.setPassword("123");
		Admin admin = (Admin) dao.select("selectForLogin", a, conn);
		System.out.println(admin.getId());
	}
}
