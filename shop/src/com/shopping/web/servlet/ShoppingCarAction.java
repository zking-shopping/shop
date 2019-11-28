package com.shopping.web.servlet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
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
			List<Goods> goods = new ArrayList<Goods>();
			List<String> goodsCount = new ArrayList<String>();
			GoodsDao gd = new GoodsDaoImpl();
		    for(Cookie cookie : cookies){
		        if(cookie.getName().equals("goodsToCar")){
		        	g = new Goods();
		            String goodInfo = cookie.getValue();
		            String goodid = goodInfo.split(",")[0];
		            g.setId(Integer.parseInt(goodid));		          
		            Goods goodOne = (Goods)gd.selectById(g, conn);
		            goods.add(goodOne);
		            goodsCount.add(goodInfo.split(",")[1]);
		            g = null;
		        }
		     } 
		    DBHelper.closeConnection(conn);
		    return goods;
		}				
	}

}
