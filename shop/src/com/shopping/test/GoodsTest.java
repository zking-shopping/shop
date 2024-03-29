
package com.shopping.test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Goods;

public class GoodsTest {
	@Test
	public void testGoodsBySort(){
		Connection conn = DBHe.getConnection();
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.selectBySort("1", conn);
		System.out.println(list.get(0));
		Goods g = list.get(1);
		System.out.println(g.getGoodsName());
		System.out.println(list.size());
	}
	
	@Test
	public void testAllGoods(){
		Connection conn = DBHe.getConnection();
		GoodsDao dao = new GoodsDaoImpl();
		List<Object> list = dao.selectAll(new Goods(), conn);
		System.out.println(list.size());
		Goods g = (Goods)list.get(0);
		System.out.println(g.getGoodsName());
	}
	
	@Test
	public void testSelectAllGoods(){
		Connection conn = DBHe.getConnection();
		GoodsDao dao = new GoodsDaoImpl();
		List<Goods> list = dao.selectAllNoDel(conn);
		System.out.println(list.size());
		Goods g = (Goods)list.get(0);
		System.out.println(g.getGoodsName());
	}
}
