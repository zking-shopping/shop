package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.DetailOrderDao;
import com.shopping.pojo.DetailOrder;
import com.shopping.pojo.Member;
import com.shopping.pojo.Order;

public class DetailOrderDaoImpl extends BaseDao implements DetailOrderDao{

	@Override
	public List<DetailOrder> selectByOrderId(String orderId, Connection conn) {
		String sql = "select * from t_detailOrder where orderId=?";
		List<DetailOrder> list = new ArrayList<DetailOrder>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DetailOrder detail = new DetailOrder();
				detail.setId(rs.getInt("id"));
				detail.setOrderId(rs.getInt("orderId"));
				detail.setGoodsId(rs.getInt("goodsId"));
				detail.setGoodsName(rs.getString("goodsName"));
				detail.setUrl(rs.getString("url"));
				detail.setPrice(rs.getString("price"));
				detail.setNumber(rs.getString("number"));
				detail.setColorId(rs.getInt("colorId"));
				detail.setGoodsColor(rs.getString("goodsColor"));
				list.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
