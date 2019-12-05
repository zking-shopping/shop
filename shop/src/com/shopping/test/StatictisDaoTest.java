package com.shopping.test;

import java.sql.Connection;

import org.junit.Test;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.MemberStatisticsDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.MemberStatisticsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Admin;
import com.shopping.pojo.Goods;
import com.shopping.pojo.GoodsStatistics;
import com.shopping.pojo.Member;
import com.shopping.pojo.MemberStatistics;
import com.shopping.util.DateHelper;
import com.shopping.util.TimeHelper;

public class StatictisDaoTest {

//	public void testMemberEnter(){
//		long startTime = TimeHelper.getTime();
//		session.setAttribute("startTime", startTime);
//	}
//	
//	public void testMemberOut(){
//		Connection conn = DBHelper.getConnection();
//		long startTime = session.getAttribute("startTime");
//		long endTime = TimeHelper.getTime();
//		MemberDao dao = new MemberDaoImpl();
//		Member a = session.getAttribute("member");
//		int time = TimeHelper.duration(startTime, endTime);
//		String nowDate = DateHelper.getSimpleDate();
//		int oldTime = Integer.valueOf(a.getTime());
//		int newTime = oldTime+time;
//		if(nowDate.equals(a.getDate())){
//			a.setTime(String.valueOf(newTime));
//			dao.update("updateTime", a, conn);
//		}else{
//			String oldTime = a.getTime();
//			String oldCost = a.getCost();
//			MemberStatisticsDao statisticsDao = new MemberStatisticsDaoImpl();
//			MemberStatistics ms = new MemberStatistics();
//			ms.setMembeId(a.getId());
//			ms.setTime(a.getTime());
//			ms.setCost(a.getCost());
//			statisticsDao.insert(ms, conn);
//			a.setDate(DateHelper.getSimpleDate());
//			a.setCost("0");
//			a.setTime(String.valueOf(time));
//			dao.update("updateStatistics", a, conn);
//		}
//	}
//	
//	public void testGoodsClick(){
//		Connection conn = DBHelper.getConnection();
//		GoodsStatisticsDao goodsStatisticsDao = new GoodsStatisticsImpl();
//		Goods goods = new Goods();
//		goods.setId(id);
//		goodsStatisticsDao.update("addClickNumber", goods, conn);
//	}
//	
//	public void testGoodsBuy(){
//		//int total;	总价
//		//int		单价
//		//list		商品集合
//		
//		Connection conn = DBHelper.getConnection();
//		String time = DateHelper.getSimpleDate();
//		//goodsId
//		GoodsDao goodsDao = new GoodsDaoImpl();
//		GoodsStatisticsDao goodsStatistics = new GoodsStatisticsImpl();
//		Goods goods = new Goods();
//		goods.setId(goodsId);
//		Goods g = (Goods) goodsDao.selectById(goods, conn);
//		GoodsStatistics gs = new GoodsStatistics();
//		if(time.equals(g.getTime())){
//			//updateBuyNumber(int, Connetion conn)）
//			gs.setGoodsId(g.getId());
//			gs.setBuyNumber(buyNumber);
//			gs.setClickNumber(clickNumber);
//			gs.setTime(time);
//			goodsStatistics.update("updateBuyNumber", o, conn);
//			
//			
//		}else{
//			goods.setTime(time);
//			goodsDao.update("updateTime", goods, conn);
//			gs.setGoodsId(goods.getId());
//			gs.setBuyNumber();
//			gs.setClickNumber(0);
//			gs.setTime(time);
//			goodsStatistics.insert(gs, conn);
//		}
//		
//		Member a = session.getAttribute("member");
//		if(time.equals(a.getTime()){
//			a.setCost(cost);
//			//更新Member表
//		}else{
//			//插入sta
//			//更新Member
//		}
//	}
}
