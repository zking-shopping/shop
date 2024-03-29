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
	public List<Order> selectByMemberId(String memberId, String state, Connection conn) {
		String sql = "select * from t_order where memberId = ?";
		if(state!=null){
			sql = sql + " and state = ?";
		}
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			if(state!=null){
				ps.setString(2, state);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setMemberId(rs.getString("memberId"));
				order.setAddressId(rs.getInt("addressId"));
				order.setOrderNumber(rs.getString("orderNumber"));
				order.setTotal(rs.getString("total"));
				order.setTime(rs.getString("time"));
				order.setState(rs.getString("state"));
				order.setDel(rs.getString("del"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Order> selectAllByState(String state, Connection conn) {
		String sql = "select * from t_order where state=?";
		List<Order> list = new ArrayList<Order>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, state);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setMemberId(rs.getString("memberId"));
				order.setAddressId(rs.getInt("addressId"));
				order.setOrderNumber(rs.getString("orderNumber"));
				order.setTotal(rs.getString("total"));
				order.setTime(rs.getString("time"));
				order.setState(rs.getString("state"));
				order.setDel(rs.getString("del"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//通过会员id和订单号查询订单id
	public int selectIdByMemberAndOrderNumber(Object object, Connection conn){
		String sql = "select id from t_order where memberId=? and orderNumber=?";
		Order order = (Order) object;
		int id = -1;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, order.getMemberId());
			ps.setString(2, order.getOrderNumber());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean selectByAddressId(int addressId, Connection conn) {
		String sql = "select * from t_order where addressId=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, addressId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
