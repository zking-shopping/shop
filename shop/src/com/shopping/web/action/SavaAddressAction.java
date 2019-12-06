
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
import com.shopping.pojo.Member;

public class SavaAddressAction extends ActionFather{
	
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
		//获得会员id
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

		Connection conn = DBHelper.getConnection();
		try {
			conn.setAutoCommit(false);
			AddressDao ad = new AddressDaoImpl();
			//查询地址列表
			List<Address> list = new ArrayList<Address>();
			list = ad.selectByMemberId(memberId, conn);
			//判断有没有设置默认
			if(defaults.equals("true")){
				Address add1 = new Address();
				String savaId = request.getParameter("saveId");
				if(savaId!=null && savaId.equals("") && savaId.equals("undefined")){
					add1.setId(Integer.parseInt(savaId));
					add1.setDefaultAddress("false");
					ad.update("updateDefaultAddress", add1, conn);
				}
			}
			//自动默认
			if(list.size()==0){
				add.setDefaultAddress("true");
			}
			//插入
			ad.insert(add, conn);
			//查询
			list = ad.selectByMemberId(memberId, conn);
			Iterator<Address> iterator = list.iterator();
			//手机号码加密
			while (iterator.hasNext()) {
				Address add5 = iterator.next();
				String number = add.getPhoneNumber();
				number = number.substring(0, 3)+"****"+number.substring(7);
				add5.setPhoneNumber(number);
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
