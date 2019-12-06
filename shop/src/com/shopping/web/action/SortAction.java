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
  		String pageNum = request.getParameter("page");
  		String pageSize = request.getParameter("pagesize");
  		if(pageNum==null){
  			pageNum = "1";
  		}
  		if(pageSize == null){
  			pageSize="20";
  		}
  		int pageNumber = Integer.parseInt(pageNum);
  		int pagesize =  Integer.parseInt(pageSize);
  		String sort = request.getParameter("sort");
  		
  		int number = pageNumber;
  		int size = pagesize;
        Connection conn = DBHelper.getConnection();
        GoodsDao dao = new GoodsDaoImpl();
        PicDao picDao = new PicDaoImpl();
        //一个种类的最大页数
  		int maxPage = dao.selectSortCount(Integer.parseInt(sort), conn);
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
        request.getSession().setAttribute("maxPage", maxPage);
        DBHelper.closeConnection(conn);
       
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
