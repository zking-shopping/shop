package com.shopping.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.DetailOrderDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.MemberStatisticsDao;
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.DetailOrderDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.MemberStatisticsDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Goods;
import com.shopping.pojo.GoodsStatistics;
import com.shopping.pojo.Member;
import com.shopping.pojo.MemberStatistics;
import com.shopping.pojo.Order;
import com.shopping.util.DateHelper;

public class ImmediatePaymentAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		HttpSession session = request.getSession();
  		Member member = (Member) session.getAttribute("member");
  		
  		Connection conn = DBHelper.getConnection();
  		try{
  			conn.setAutoCommit(false);
  			String id = request.getParameter("id");
  			OrderDao od = new OrderDaoImpl();
  			Order order = new Order();
  			order.setId(Integer.parseInt(id));
  			order.setState("2");
  			od.update("updateState", order, conn);
  			
  			Order order2 = (Order) od.selectById(order, conn);
  			DetailOrderDao dod = new DetailOrderDaoImpl();
  			DetailOrder detail = new DetailOrder();
  			List<Object> list = dod.selectAll(detail, conn);
  			GoodsDao gd = new GoodsDaoImpl();
  			MemberDao md = new MemberDaoImpl();
  			GoodsStatisticsDao gs = new GoodsStatisticsImpl();
  			MemberStatisticsDao msd = new MemberStatisticsDaoImpl();
  			if(list.size() > 0){
  				for (int i = 0; i < list.size(); i++) {
  					DetailOrder detailOrder = (DetailOrder) list.get(i);
  					int num = Integer.valueOf(detailOrder.getNumber());
  					Goods g = new Goods();
  					g.setId(detailOrder.getGoodsId());
  					Goods goods = (Goods) gd.selectById(g, conn);
  					if(DateHelper.getSimpleDate().equalsIgnoreCase(goods.getTime())){
  						GoodsStatistics gstatistics = new GoodsStatistics();
  						gstatistics.setGoodsId(goods.getId());
  						gstatistics.setTime(DateHelper.getSimpleDate());
  						GoodsStatistics goodsStatistics = (GoodsStatistics) gs.select("selectByGD", gstatistics, conn);
  						int lastNum = Integer.valueOf(goodsStatistics.getBuyNumber())+num;
  						gstatistics.setBuyNumber(lastNum);
  						Boolean res2 = gs.update("updateBuyNumber", goodsStatistics, conn);
  						System.out.println("res2"+res2);
  					}else{
  						goods.setTime(DateHelper.getSimpleDate());
  						Boolean res3 = gd.update("updateTime", goods, conn);
  						System.out.println(res3);
  						GoodsStatistics gstatistics = new GoodsStatistics();
  						gstatistics.setGoodsId(goods.getId());
  						gstatistics.setTime(DateHelper.getSimpleDate());
  						gstatistics.setBuyNumber(num);
  						Boolean res4 = gs.insert(gstatistics, conn);
  						System.out.println("res4"+res4);
  					}
  				}
  			}
  			if(DateHelper.getSimpleDate().equalsIgnoreCase(member.getDate())){
  				int newCost = Integer.valueOf(member.getCost()) + Integer.valueOf(order2.getTotal());
  				member.setCost(String.valueOf(newCost));
  				Boolean res1 = md.update("updateMemberCost", member, conn);
  				System.out.println("res1"+res1);
  			}else{
  				member.setDate(DateHelper.getSimpleDate());
  				Boolean res5 = md.update("updateDate", member, conn);
  				System.out.println(res5);
  				MemberStatistics mstatistics = new MemberStatistics();
  				mstatistics.setMembeId(member.getId());
  				mstatistics.setCost(order2.getTotal());
  				mstatistics.setTime("0");
  				Boolean res6 = msd.insert(mstatistics, conn);
  				System.out.println(res6);
  			}
			conn.commit();
  		}catch(Exception e){
  			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
  		}finally{
  			DBHelper.closeConnection(conn);
  		}
		return null;
	}

}
