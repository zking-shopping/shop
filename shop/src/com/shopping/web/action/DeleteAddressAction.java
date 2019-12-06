package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.AddressDao;
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;
import com.shopping.pojo.Member;
import com.shopping.util.EncryptionHelper;

import net.sf.json.JSONArray;

public class DeleteAddressAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
  		//获得要删除的地址的id
		Member m = (Member)request.getSession().getAttribute("member");
		String memberId = m.getId();
  		String id = request.getParameter("deleteId");
  		String isSuccess = "-1";
  		AddressDao ad = new AddressDaoImpl();
  		OrderDao od = new OrderDaoImpl();
  		Connection conn = DBHelper.getConnection();
		try {
			conn.setAutoCommit(false);
	  		if(id!=null && !id.equals("") && !id.equals("null")){
	  			int deleteId = Integer.parseInt(id);
				//查询地址是否被用于订单表
				boolean used = od.selectByAddressId(deleteId, conn);
	  			Address add = new Address();
	  			add.setId(deleteId);
				if(used==true){
					add.setMemberId("");
					//将对应id的memberId设置为空
					ad.update("updateMemberIdNull", add, conn);
				}else{
		  			boolean b = ad.deleteById(add, conn);
		  			if(b==true){
		  				isSuccess = "1";
		  			}
				}
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
		return isSuccess;
	}

}
