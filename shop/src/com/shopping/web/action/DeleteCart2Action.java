package com.shopping.web.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Member;

public class DeleteCart2Action extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		        String forward = null;
		        HttpSession session = request.getSession();
		        Member member = (Member) session.getAttribute("member");
		      String memberId = member.getId();
		        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		        //根据goodsId删除对应的用户的购物车商品
		       CartDao cd = new CartDaoImpl();
		       Connection conn = DBHelper.getConnection();
		       List<Cart> list = new ArrayList<Cart>();
		       list = cd.selectByMemberId(memberId, conn);
		       for (Cart cart : list) {
		    	   Cart carts = new Cart();
				     if(cart.getGoodsId()==goodsId){
				    	 System.out.println(cart.getId());
				    	 carts.setId(cart.getId());
				    	 cd.deleteById(carts, conn);
				     }
				     carts = null;
			   }
		        System.out.println(goodsId+"=========商品Id");
		        DBHelper.closeConnection(conn);
		        forward = "success";
		        return forward;
	}

}
