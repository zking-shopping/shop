package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.AddressDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;
import com.shopping.pojo.Member;

public class ShowAddressAction extends ActionFather{
	
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
		//获得session存储的member对象
//		Member m = (Member)request.getSession().getAttribute("member");
//		String id = m.getId();
		String id = "125a2177-a2be-4ee4-8223-8ecfe0eefef4";

		Connection conn = DBHelper.getConnection();
		AddressDao ad = new AddressDaoImpl();
		List<Address> list = new ArrayList<Address>();
		list = ad.selectByMemberId(id, conn);
		Iterator<Address> iterator = list.iterator();
		while (iterator.hasNext()) {
			Address add = iterator.next();
			String number = add.getPhoneNumber();
			number = number.substring(0, 3)+"****"+number.substring(7);
			add.setPhoneNumber(number);
		}
        DBHelper.closeConnection(conn);
		PrintWriter out = null;
		JSONArray jarr = JSONArray.fromObject(list);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jarr.toString());
		out.flush();
		out.close();
		
		return null;
	}

}
