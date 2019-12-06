package com.shopping.web.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;

public class BettergoodsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
  		
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
        Connection conn = DBHelper.getConnection();
        BaseDao bd = new GoodsDaoImpl();
        PicDao pd = new PicDaoImpl();
        List<Object> list = new ArrayList<Object>();
    	list =  bd.selectAll(new Goods(),conn);
    	Random r = new Random();
    	int n = r.nextInt(list.size()-8);
    	list = list.subList(n, n+8);
    	List<String> picture = new ArrayList<String>();
    	List<Object> list1 = new ArrayList<Object>();
		list1 =  bd.selectAll(new Pic(),conn);
		list1 = list1.subList(n, n+8);
		List<BetterGoods> list2 = new ArrayList<BetterGoods>();
		for(int i = 0;i<8;i++){
			  BetterGoods bds = new BetterGoods();
			  Goods goods = (Goods)list.get(i);
			  Pic pic = (Pic)list1.get(i);
			  bds.setGoodsname(goods.getGoodsName());
			  bds.setIntroduction(goods.getIntroduction());
			  String pics = pic.getPicture1();
			  pics = pics.substring(22);
			  bds.setPicture1(pics);
			  bds.setPrice(goods.getPrice());
			  bds.setId(goods.getId());
			  list2.add(bds);
		  }
        DBHelper.closeConnection(conn);
        JSONArray jo =JSONArray.fromObject(list2);
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(list.size()!=0){
  				out.print(jo.toString());
  				out.flush();
  				out.close();
  			}else{
  				forward = "error";
  			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return forward;
	}

}
