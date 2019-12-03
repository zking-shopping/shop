package com.shopping.test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.shopping.dao.ColorDao;
import com.shopping.dao.daoImpl.ColorDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.Color;
import com.shopping.pojo.Goods;

public class ColorDaoTest {
	@Test
	public void testColor(){
		Connection conn = DBHe.getConnection();
		ColorDao dao = new ColorDaoImpl();
		List<Color> list = dao.selectByGoodsId("1", conn);
		Color color = list.get(0);
		System.out.println(list.size());
		System.out.println(color.getGoodsColor());
	}
}
