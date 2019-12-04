package com.shopping.web.servlet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;
import com.shopping.web.action.ActionFather;
import com.shopping.web.form.FormFather;

public class ShoppingCarAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		
		//先判断session中有没有账户信息（用户登录会在session中存值），即判断用户有没有登录，
		//如果有，就去数据库查询用户，没有，就在cookie中取值
		Member m = (Member)request.getSession().getAttribute("member");
		Connection conn = DBHelper.getConnection();
		
		if(m!=null){	
			
			CartDao cd = new CartDaoImpl();
			System.out.println(m.getId()+"getId");
			ArrayList<Cart> list = (ArrayList<Cart>)cd.selectByMemberId(m.getId(), conn);
			System.out.println(m.getId());
			DBHelper.closeConnection(conn);			
			return list;
		}else{
			Goods g = null;
			Cookie[] cookies = request.getCookies();
			List<Cart> goods = new ArrayList<Cart>();
//			List<String> goodsCount = new ArrayList<String>();
			GoodsDao gd = new GoodsDaoImpl();
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
//		            c.setColorId();
		            c.setId(Integer.parseInt(cookie.getName().substring(10)));
		            c.setGoodsColor(goodtype);
		            c.setGoodsId(goodOne.getId());
		            c.setGoodsName(goodOne.getGoodsName());
		            
//		            c.setMemberId(memberId);
		            c.setNumber(Integer.parseInt(goodcount));
		            c.setPrice(goodOne.getPrice());
		            int picId = goodOne.getPicId();
					PicDao pd = new PicDaoImpl();
					Pic p = new Pic();
					p.setId(picId);
					Pic pic = (Pic)pd.selectById(p, conn);
					c.setUrl(pic.getPicture1());
		           
		            goods.add(c);
//		            goodsCount.add(goodInfo.split(",")[1]);
		            g = null;
		        }
		     } 
		    DBHelper.closeConnection(conn);
		    return goods;
		}				
	}

}
