package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;

public class IntroductionAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
		String picid = request.getParameter("picid");
		int id = Integer.parseInt(picid);
         //通过picid拿到对应的详情图片
		Connection conn = DBHelper.getConnection();
	    GoodsDao gd = new GoodsDaoImpl();
		BetterGoods bd = new BetterGoods();
			Goods goods = new Goods();
			goods.setId(id);
			Goods goods1 = (Goods) gd.selectById(goods, conn);
	
		
		  PicDao pd = new PicDaoImpl();
		  Pic p = new Pic();
		  p.setId(id);
		  Pic pi = (Pic) pd.selectById(p, conn);
		  DBHelper.closeConnection(conn);
			bd.setGoodsname(goods1.getGoodsName());
			bd.setId(id);
			bd.setIntroduction(goods1.getIntroduction());
			bd.setPrice(goods1.getPrice());
			bd.setPicture1(pi.getPicture1().substring(22));
			bd.setPicture2(pi.getPicture2().substring(22));
			bd.setPicture3(pi.getPicture3().substring(22));
		  try {
			  if(pi.getPicture2()!=null){
				  request.setAttribute("bd", bd);
				  forward="success";
			  }else{
				  forward="error";
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	   return forward;
	}
   
}
