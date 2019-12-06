package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.db.DBHe;
import com.shopping.pojo.GoodsStatistics;
import com.shopping.util.DateHelper;

public class StaTest {
	@Test
	public void testGoods(){
		Connection conn = DBHe.getConnection();
		GoodsStatisticsDao gs = new GoodsStatisticsImpl();
		GoodsStatistics gstatistics = new GoodsStatistics();
		gstatistics.setGoodsId(0);
		gstatistics.setTime(DateHelper.getSimpleDate());
		GoodsStatistics goodsStatistics = (GoodsStatistics) gs.select("selectByGD", gstatistics, conn);
		System.out.println(goodsStatistics.getGoodsId());
		System.out.println(goodsStatistics.getBuyNumber());
	}
	
	@Test
	public void testGoodsS(){
		Connection conn = DBHe.getConnection();
		GoodsStatisticsDao gs = new GoodsStatisticsImpl();
		GoodsStatistics gstatistics = new GoodsStatistics();
		gstatistics.setGoodsId(0);
		gstatistics.setTime(DateHelper.getSimpleDate());
		gstatistics.setBuyNumber(3000);
		Boolean res2 = gs.update("updateBuyNumber", gstatistics, conn);
		System.out.println("res2"+res2);
	}
}
