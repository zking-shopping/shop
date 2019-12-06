package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Member;
import com.shopping.util.DateHelper;
import com.shopping.util.TimeHelper;
import com.shopping.util.UUIDHelper;

public class MemberDaoTest {
	@Test
	public void testSelectForLogin(){
		Connection conn = DBHe.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setUsername("aaa");
		m.setPassword("aaa");
		Member member = (Member) dao.select("selectForLogin", m, conn);
	}
	
	@Test
	public void testInsert(){
		Connection conn = DBHe.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setUsername("aaa");
		m.setPassword("aaa");
		m.setCost("55");
		m.setDate("sda");
		m.setDel("false");
		m.setId(UUIDHelper.getUUID());
		m.setName("sdffdas");
		m.setPhoneNumber("445");
		m.setStatistics("sdfasd");
		m.setTime(DateHelper.getSimpleDate());
		Boolean res = dao.insert(m, conn);
		System.out.println(res);
	}
	
	@Test
	public void testUpdateStatistics(){
		Connection conn = DBHe.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setId("3c1dc732-422d-4639-8d07-aa7e2c445bb0");
		m.setCost("1");
		m.setDate(DateHelper.getSimpleDate());
		m.setTime("1");
		Boolean b = dao.update("updateStatistics", m, conn);
		long lo = 1575557094150l;
		int i = TimeHelper.duration(lo, TimeHelper.getTime());
		System.out.println(i);
		System.out.println(b);
	}
}
