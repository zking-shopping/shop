package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Member;

public class MemberDaoTest {
	@Test
	public void testSelectForLogin(){
		Connection conn = DBHe.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setUsername("aaa");
		m.setPassword("aaa");
		Member member = (Member) dao.select("selectForLogin", m, conn);
		System.out.println(member.getTime());
	}
	
}
