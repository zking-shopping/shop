package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.ColorDao;
import com.shopping.pojo.Color;
import com.shopping.pojo.Member;

public class ColorDaoImpl extends BaseDao implements ColorDao{

	@Override
	public List<Color> selectByGoodsId(String goodsId, Connection conn) {
		String sql = "select * from t_color where goodsId=?";
		List<Color> list = null;
		try {
			list = new ArrayList<Color>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, goodsId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Color color = new Color();
				color.setId(rs.getInt("id"));
				color.setGoodsId(rs.getInt("goodsId"));
				color.setGoodsColor(rs.getString("goodsColor"));
				color.setStock(rs.getInt("stock"));
				color.setHide(rs.getString("hide"));
				list.add(color);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
