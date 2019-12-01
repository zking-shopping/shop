package com.shopping.web.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;

public class MintypeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//根据传过来的页面和数量查询数据库
        String sort = request.getParameter("sort");
  		//根据sort查询数据库,找到所有这一类型的图片
        Connection conn = DBHelper.getConnection();
        BaseDao bd = new GoodsDaoImpl();
        GoodsDao gd = new GoodsDaoImpl();
        PicDao pd = new PicDaoImpl();
       List<Goods> list = new ArrayList<Goods>();
    		  list = gd.selectBySort(sort, conn);
    	
       List<Object> list1 = new ArrayList<Object>();
		  list1 =  bd.selectAll(new Pic(),conn);

		  List<BetterGoods> list2 = new ArrayList<BetterGoods>();
		  for(int i = 0;i<list.size();i++){
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
        JSONArray jo = JSONArray.fromObject(list2);
  		
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(list2.size()!=0){
//  				System.out.println(list);
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
