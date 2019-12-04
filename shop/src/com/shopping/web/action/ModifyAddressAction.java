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

import com.shopping.dao.AddressDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;

public class ModifyAddressAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");

//		Member m = (Member)request.getSession().getAttribute("member");
//		String memberId = m.getId();
		String memberId = "125a2177-a2be-4ee4-8223-8ecfe0eefef4";
		Address add = new Address();
		add.setMemberId(memberId);
		add.setCousignee(request.getParameter("cousignee"));
		add.setPhoneNumber(request.getParameter("phoneNumber"));
		add.setProvinces(request.getParameter("provinces"));
		add.setCity(request.getParameter("city"));
		add.setArea(request.getParameter("area"));
		add.setDetailAddress(request.getParameter("detailAddress"));
		String defaults = request.getParameter("defaultAddress");
		add.setDefaultAddress(defaults);
  		String id = request.getParameter("saveId");
  		AddressDao ad = new AddressDaoImpl();
  		Connection conn = DBHelper.getConnection();
		List<Address> list = new ArrayList<Address>();
		try {
			conn.setAutoCommit(false);
	  		if(id!=null && !id.equals("null") && !id.equals("")){
				int saveId = Integer.parseInt(id);
				add.setId(saveId);
				ad.updateExId(add, conn);
				list = ad.selectByMemberId(memberId, conn);
				Iterator<Address> iterator = list.iterator();
				//手机号码加密
				while (iterator.hasNext()) {
					Address add5 = iterator.next();
					String number = add.getPhoneNumber();
					number = number.substring(0, 3)+"****"+number.substring(7);
					add5.setPhoneNumber(number);
				}
	  		}
			conn.commit();
	  		PrintWriter out = response.getWriter();
	  		JSONArray jarr = JSONArray.fromObject(list);
	  		out.print(jarr.toString());
		} catch (SQLException e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(conn);
		}
		
		return null;
	}

}
