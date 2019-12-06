
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
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;
import com.shopping.pojo.Member;
import com.shopping.pojo.Order;
import com.shopping.util.EncryptionHelper;

public class ModifyAddressAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");

		Member m = (Member)request.getSession().getAttribute("member");
		String memberId = m.getId();
//		String memberId = "125a2177-a2be-4ee4-8223-8ecfe0eefef4";
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
  		OrderDao od = new OrderDaoImpl();
  		Connection conn = DBHelper.getConnection();
		List<Address> list = new ArrayList<Address>();
		try {
			conn.setAutoCommit(false);
	  		if(id!=null && !id.equals("null") && !id.equals("")){
				int saveId = Integer.parseInt(id);
				//查询地址是否被用于订单表
				boolean b = od.selectByAddressId(saveId, conn);
				add.setId(saveId);
				if(b==true){
					Address adds = add;
					adds.setMemberId("");
					//将对应id的memberId设置为空
					ad.update("updateMemberIdNull", adds, conn);
					//插入新的地址
					add.setMemberId(memberId);
					ad.insert(add, conn);
				}else{
					//判断对应会员id里面有没有默认地址
					if(defaults.equals("true")){
						Address add1 = new Address();
						String defaultId = request.getParameter("defaultId");
						if(defaultId!=null && !defaultId.equals("null") && !defaultId.equals("undefined")){
							add1.setId(Integer.parseInt(defaultId));
							add1.setDefaultAddress("false");
							ad.update("updateDefaultAddress", add1, conn);
						}
					}
					//更新对应id的地址信息
					ad.updateExId(add, conn);
				}
				list = ad.selectByMemberId(memberId, conn);
				EncryptionHelper.encryptMobileNumber(list);
	  		}
			conn.commit();
		} catch (SQLException e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(conn);
		}
		
		return list;
	}

}
