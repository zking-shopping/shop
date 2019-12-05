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
import net.sf.json.JSONObject;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;

public class CheckLoginAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {

		String forward = null;
		
		//先判断session中有没有账户信息（用户登录会在session中存值），即判断用户有没有登录，
		//如果有，就去数据库查询用户，没有，就在cookie中取值
		Member m = (Member)request.getSession().getAttribute("member");
		Connection conn = DBHelper.getConnection();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
		System.out.println(m+"-----");
		try {
			PrintWriter pw = response.getWriter();
				CartDao cd = new CartDaoImpl();
				System.out.println(m.getId()+"getId");
				ArrayList<Cart> cart = (ArrayList<Cart>)cd.selectByMemberId(m.getId(), conn);
				for(int i=0;i<cart.size();i++){
					 cart.get(i).setUrl(cart.get(i).getUrl().substring(22));
				}
				System.out.println(cart.size()+"购物车的数量===========");
				DBHelper.closeConnection(conn);			
				JSONArray ja = JSONArray.fromObject(cart);
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
