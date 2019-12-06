package com.shopping.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.ColorDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.ColorDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Color;
import com.shopping.pojo.Goods;
import com.shopping.pojo.GoodsStatistics;
import com.shopping.pojo.Pic;
import com.shopping.util.DateHelper;

public class DetailsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		 String forward = null;
		 int id = Integer.parseInt(request.getParameter("id"));
		 Connection conn = DBHelper.getConnection();
		 BetterGoods bd = new BetterGoods();
		
		 Goods goods = new Goods();
		 goods.setId(id);
		 GoodsDao gd = new GoodsDaoImpl();
		 PicDao pd = new PicDaoImpl();
		 ColorDao cd = new ColorDaoImpl();
		 Goods goods1 = (Goods) gd.selectById(goods, conn);
		 Pic pic = new Pic();
		 pic.setId(id);
		 Pic pic1 = (Pic) pd.selectById(pic, conn);
		 
		 String goodsid = Integer.toString(id);
         List<Color> color = (List<Color>) cd.selectByGoodsId(goodsid, conn);
		 bd.setGoodsname(goods1.getGoodsName());
		 bd.setIntroduction(goods1.getIntroduction());
		 bd.setColor(color.get(0).getGoodsColor());
//		 bd.setColor2(color.get(1).getGoodsColor());
//		 bd.setColor3(color.get(2).getGoodsColor());
		 bd.setPrice(goods1.getPrice());
		 bd.setId(id);
		 bd.setPicture1(pic1.getPicture1().substring(22));
		 bd.setPicture2(pic1.getPicture2().substring(22));
		 bd.setPicture3(pic1.getPicture3().substring(22));
		 request.setAttribute("bd", bd);
		 
		 
		GoodsStatisticsDao goodsStatisticsDao = new GoodsStatisticsImpl();
		String nowTime = DateHelper.getSimpleDate();
		try{
			conn.setAutoCommit(false);
			if(nowTime.equalsIgnoreCase(goods1.getTime())){
				GoodsStatistics statistics = new GoodsStatistics();
				statistics.setGoodsId(id);
				statistics.setTime(DateHelper.getSimpleDate());
				goodsStatisticsDao.update("addClickNumber", statistics, conn);
			}else{
				Goods aGood = new Goods();
				aGood.setTime(nowTime);
				gd.update("updateTime", aGood, conn);
				GoodsStatistics statistics = new GoodsStatistics();
				statistics.setGoodsId(id);
				statistics.setTime(nowTime);
				statistics.setClickNumber(1);
				statistics.setBuyNumber(0);
				goodsStatisticsDao.insert(statistics, conn);
				}
				conn.commit();
			}catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				DBHelper.closeConnection(conn);
			}

		forward="success";
		return forward;
	}

}
