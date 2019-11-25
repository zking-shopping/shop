package com.shopping.test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.shopping.dao.AddressDao;
import com.shopping.dao.BaseDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;

public class AddressDaoTest {
	
	@Test
	public void testDao(){
		Connection conn = DBHe.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void selectAllTest(){
		Connection conn = DBHe.getConnection();
		AddressDao dao = new AddressDaoImpl();
		Address addr = new Address();
		List<Object> list = dao.selectAll(addr, conn);
		Address a = (Address) list.get(0);
		System.out.println(a.getCity());
	}
	
	@Test
	public void selectByIdTest(){
		Connection conn = DBHe.getConnection();
		AddressDao dao = new AddressDaoImpl();
		Address addr = new Address();
		addr.setId(1);
		Address a = (Address) dao.selectById(addr, conn);
		System.out.println(a.getCousignee());
	}
	
	@Test
	public void insertTest(){
		Connection conn = DBHe.getConnection();
		AddressDao dao = new AddressDaoImpl();
		Address addr = new Address();
		addr.setId(2);
		addr.setArea("aaa");
		addr.setCity("aaa");
		addr.setCousignee("aa");
		addr.setDetailAddress("aa");
		addr.setMemberId("gyh");
		addr.setPhoneNumber("aaa");
		addr.setProvinces("aaaa");
		Boolean res = dao.insert(addr, conn);
		System.out.println(res);
	}
	
	@Test
	public void deleteByIdTest(){
		Connection conn = DBHe.getConnection();
		AddressDao dao = new AddressDaoImpl();
		Address addr = new Address();
		addr.setId(2);
		Boolean res = dao.deleteById(addr, conn);
		System.out.println(res);
	}
	
	@Test
	public void updateExIdTest(){
		Connection conn = DBHe.getConnection();
		AddressDao dao = new AddressDaoImpl();
		Address addr = new Address();
		addr.setId(1);
		addr.setArea("aaa");
		addr.setCity("aaa");
		addr.setCousignee("aa");
		addr.setDetailAddress("aa");
		addr.setMemberId("gyh");
		addr.setPhoneNumber("aaa");
		addr.setProvinces("aaaa");
		Boolean res = dao.updateExId(addr, conn);
		System.out.println(res);
	}
	
}
