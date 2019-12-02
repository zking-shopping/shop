package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;

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
		 Goods goods1 = (Goods) gd.selectById(goods, conn);
		 Pic pic = new Pic();
		 pic.setId(id);
		 Pic pic1 = (Pic) pd.selectById(pic, conn);
		 bd.setGoodsname(goods1.getGoodsName());
		 bd.setIntroduction(goods1.getIntroduction());
		 bd.setColor(goods1.getColor());
		 bd.setPrice(goods1.getPrice());
		 bd.setId(id);
		 bd.setPicture1(pic1.getPicture1().substring(22));
		 bd.setPicture2(pic1.getPicture2().substring(22));
		 bd.setPicture3(pic1.getPicture3().substring(22));
		 request.setAttribute("bd", bd);
		 forward="success";
		return forward;
	}

}
