package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.OrderDao;
import com.shopping.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao{

	@Override
	public List<Order> selectByMemberId(String memberId, Connection conn) {
		String sql = "select * from t_order where memberId=?";
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setMemberId(rs.getString("memberId"));
				order.setOrderNumber(rs.getString("orderNumber"));
				order.setTotal(rs.getString("total"));
				order.setMemberId(rs.getString("memberId"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
