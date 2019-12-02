package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.dto.BetterGoods;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;
import com.shopping.util.EncryptionHelper;

public class SortAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		String forward = null;
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//根据传过来的页面和数量查询数据库
//  		int pageNumber = Integer.parseInt( request.getParameter("pageNumber"));
//  		int pagesize = Integer.parseInt(request.getParameter("pagesize"));
//        String sort = request.getParameter("sort");
//  		//根据sort查询数据库,找到所有这一类型的图片
////        List<Goods> list = new ArrayList<Goods>();
  		
  		int pageNumber = (Integer) request.getAttribute("pageNumber");
  		int pageSize =  (Integer) request.getAttribute("pageSize");
  		String sort = request.getParameter("sort");
  		int number = pageNumber;
  		int size = pageSize;
        Connection conn = DBHelper.getConnection();
        GoodsDao dao = new GoodsDaoImpl();
        PicDao picDao = new PicDaoImpl();
        int count = dao.selectSortCount(Integer.valueOf(sort), conn);
        int page = count%size == 0 ? count/size : count/size+1;
        if(number > page){
        	number = page;
        }
        List<BetterGoods> list2 = new ArrayList<BetterGoods>();
        List<Goods> list = dao.selectSortPage(Integer.valueOf(sort), number, size, conn);
        for (int i = 0; i < list.size(); i++) {
        	BetterGoods b = new BetterGoods();
			Goods goods = list.get(i);
			Pic pic = new Pic();
			pic.setId(goods.getPicId());
			Pic p = (Pic) picDao.selectById(pic, conn);
			b.setGoodsname(goods.getGoodsName());
			b.setIntroduction(goods.getIntroduction());
			b.setPicture1(p.getPicture1().substring(22));
			b.setPrice(goods.getPrice());
			b.setId(goods.getId());
			list2.add(b);
		}
        
//        BaseDao bd = new GoodsDaoImpl();
//        PicDao pd = new PicDaoImpl();
//       List<Object> list = new ArrayList<Object>();
//    		  list =  bd.selectAll(new Goods(),conn);
//    		  list = list.subList(0, pagesize);
//       List<String> picture = new ArrayList<String>();
//       List<Object> list1 = new ArrayList<Object>();
//		  list1 =  bd.selectAll(new Pic(),conn);
//		  list1 = list1.subList(0, pagesize);
//		  List<BetterGoods> list2 = new ArrayList<BetterGoods>();
//		  for(int i = 0;i<pagesize;i++){
//			  BetterGoods bds = new BetterGoods();
//			  Goods goods = (Goods)list.get(i);
//			  Pic pic = (Pic)list1.get(i);
//			  bds.setGoodsname(goods.getGoodsName());
//			  bds.setIntroduction(goods.getIntroduction());
//			  String pics = pic.getPicture1();
//			  pics = pics.substring(22);
//			  bds.setPicture1(pics);
//			  bds.setPrice(goods.getPrice());
//			  bds.setId(goods.getId());
//			  list2.add(bds);
//		  }
        DBHelper.closeConnection(conn);
        //根据查询的所有商品找到所有的相对应的picid
//        List<Integer> listPic = new ArrayList<Integer>();
//       for(int i = 0;i<list.size();i++){
//    	   listPic.add(list.get(i).getPicId());
//       }
       //根据每一个picid找到对应的详情图片的picture1
//       List<String> pictures = new ArrayList<String>();
//       for(int i = 0;i<listPic.size();i++){
//    	   Pic pic =  (Pic) pd.selectById(list.get(i).getPicId(), conn);
//    	   String picture = pic.getPicture1();
//    	    pictures.add(picture);
//       }
  		JSONArray jo = JSONArray.fromObject(list2);
  		try {
  			PrintWriter out = response.getWriter();
  			out.print(jo.toString());
  			out.flush();
  			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
  		return forward;
	
	}
}
