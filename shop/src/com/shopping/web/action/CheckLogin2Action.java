package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;

public class CheckLogin2Action extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
		Goods g = null;
		Cookie[] cookies = request.getCookies();
		List<Cart> cart1 = new ArrayList<Cart>();
		Connection conn = DBHelper.getConnection();
		GoodsDao gd = new GoodsDaoImpl();
		response.setContentType("text/json;charset=utf-8");
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
	  
		try {
			 for(Cookie cookie : cookies){
			        if(cookie.getName().startsWith("goodsToCar")){
			        	g = new Goods();
			            String goodInfo = cookie.getValue();
			            System.out.println(goodInfo);
			            String goodid = goodInfo.split("=-=")[0];
			            String goodcount = goodInfo.split("=-=")[1];
			            String goodtype = goodInfo.split("=-=")[2];
			            g.setId(Integer.parseInt(goodid));		          
			            Goods goodOne = (Goods)gd.selectById(g, conn);
			            Cart c = new Cart();
//			            c.setColorId();
			            c.setId(Integer.parseInt(cookie.getName().substring(10)));
			            c.setGoodsColor(goodtype);
			            c.setGoodsId(goodOne.getId());
			            c.setGoodsName(goodOne.getGoodsName());
			            
//			            c.setMemberId(memberId);
			            c.setNumber(Integer.parseInt(goodcount));
			            c.setPrice(goodOne.getPrice());
			            int picId = goodOne.getPicId();
						PicDao pd = new PicDaoImpl();
						Pic p = new Pic();
						p.setId(picId);
						Pic pic = (Pic)pd.selectById(p, conn);
						c.setUrl(pic.getPicture1().substring(22));
			           
			            cart1.add(c);
			            g = null;
			        }
			     } 
			  PrintWriter pw = response.getWriter();
		    	 DBHelper.closeConnection(conn);
			    JSONArray ja = JSONArray.fromObject(cart1);
			    pw.write(ja.toString());
			    pw.flush();
			    pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return forward;
	}
   
}
