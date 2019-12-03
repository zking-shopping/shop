package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
			
			AddressDao ad = new AddressDaoImpl();
	  		//获得会员Id
	  		for (int i = 0; i < 10; i++) {
	  	  		String id = request.getParameter("id"+i);
	  	  		if(id!=null){
	  	  	  		Address add = new Address();
	  	  	  		add.setId(Integer.parseInt(id));
	  	  	  		if(i==0){
	  	  	  			System.out.println(id);
	  	  	  	  		add.setDefaultAddress("true");
	  	  	  		}else{
	  	  	  	  		add.setDefaultAddress("false");
	  	  	  		}
	  	  	  		//更改数据
		  	  		ad.update("updateDefaultAddress", add, conn);
	  	  		}else{
	  	  	  		//中断操作
	  	  			break;
	  	  		}
			}
	  		JSONArray jarr = JSONArray.fromObject(1);
	  		PrintWriter out = response.getWriter();
	  		out.print(jarr.toString());
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeConnection(conn);
		}
  		
		return null;
	}

}
