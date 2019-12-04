package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.CartDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Member;

public class ShowSettlementListAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
  		Connection conn = DBHelper.getConnection();
		CartDao cd = new CartDaoImpl();
		Member m = (Member)request.getSession().getAttribute("member");
		String memberId = m.getId();
  		String balanceGooods = (String) request.getSession().getAttribute("balanceGooods");
		System.out.println("balanceGooods："+balanceGooods);
  		List<Cart> list = new ArrayList<Cart>();
  		try {
			conn.setAutoCommit(false);
	  		String[] data = balanceGooods.split("-__-");
	  		for (int i = 1; i < data.length; i++) {
				String[] datas = data[i].split("-_~-_~");
				String id = datas[0];
				Cart cart = new Cart();
				cart.setId(Integer.parseInt(id));
				cart.setMemberId(memberId);
				Cart carts = (Cart) cd.select("selectCart", cart, conn);
				carts.setNumber(Integer.parseInt(datas[1]));
				list.add(carts);
			}
	  		conn.commit();
	  		JSONArray jarr = JSONArray.fromObject(list);
	  		PrintWriter out = response.getWriter();
	  		out.print(jarr.toString());
		} catch (SQLException e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null){
				DBHelper.closeConnection(conn);
			}
		}
		return null;
	}
	public static void main(String[] args) {
		
	}

}
