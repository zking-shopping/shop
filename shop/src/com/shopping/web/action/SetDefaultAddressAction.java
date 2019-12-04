package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.AddressDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;

public class SetDefaultAddressAction extends ActionFather{
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");

		Connection conn = DBHelper.getConnection();
  		try {
			conn.setAutoCommit(false);
			AddressDao ad = new AddressDaoImpl();
	  		
			//获得两个id
			String id = request.getParameter("id0");
			System.out.println(id);
			if(id!=null && !id.equals("null") && !id.equals("")){
				int hadDefaultId = Integer.parseInt(id);
				Address add1 = new Address();
				add1.setId(hadDefaultId);
				add1.setDefaultAddress("false");
				ad.update("updateDefaultAddress", add1, conn);
			}
			int willDefaultId = Integer.parseInt(request.getParameter("id1"));
			Address add2 = new Address();
			add2.setId(willDefaultId);
			add2.setDefaultAddress("true");
			
			ad.update("updateDefaultAddress", add2, conn);
	  		conn.commit();
	  		JSONArray jarr = JSONArray.fromObject(1);
	  		PrintWriter out = response.getWriter();
	  		out.print(jarr.toString());
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DBHelper.closeConnection(conn);
		}
  		
		return null;
	}

}
