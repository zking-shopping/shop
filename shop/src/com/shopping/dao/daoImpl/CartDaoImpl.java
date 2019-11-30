package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.CartDao;
import com.shopping.pojo.Cart;

public class CartDaoImpl extends BaseDao implements CartDao{

	@Override
	public List<Cart> selectByMemberId(String memberId, Connection conn) {
		String sql = "select * from t_cart where memberId=?";
		List<Cart> list = new ArrayList<Cart>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Cart c = new Cart();
				c.setId(rs.getInt("id"));
				c.setMemberId(rs.getString("memberId"));
				c.setGoodsName(rs.getString("goodsName"));
				c.setGoodsId(rs.getInt("goodsId"));
				c.setNumber(rs.getInt("number"));
				c.setUrl(rs.getString("url"));
				c.setPrice(rs.getString("price"));
				c.setColorId(rs.getInt("colorId"));
				c.setGoodsColor(rs.getString("goodsColor"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
