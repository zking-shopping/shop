package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.AddressDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;

import net.sf.json.JSONArray;

public class DeleteAddressAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
  		//获得要删除的地址的id
  		String id = request.getParameter("deleteId");
  		int returnInfo = 0;
  		AddressDao ad = new AddressDaoImpl();
  		Connection conn = DBHelper.getConnection();
  		if(id!=null && !id.equals("") && !id.equals("null")){
  			int deleteId = Integer.parseInt(id);
  			Address add = new Address();
  			add.setId(deleteId);
  			boolean b = ad.deleteById(add, conn);
  			if(b==true){
  	  			returnInfo = 1;
  			}
  		}

  		PrintWriter out = null;
		try {
			out = response.getWriter();
	  		JSONArray jarr = JSONArray.fromObject(returnInfo);
	  		out.print(jarr.toString());
	  		out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
			if(conn!=null){
				DBHelper.closeConnection(conn);
			}
		}
		return null;
	}

}
