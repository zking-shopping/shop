package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsDao;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;

public class GoodsDaoImpl extends BaseDao implements GoodsDao{

	@Override
	public List<Goods> selectBySort(String sort, Connection conn) {
		String sql = "select * from t_goods where sort=?";
		List<Goods> list = new ArrayList<Goods>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sort);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("instroduction"));
				goods.setSort(rs.getString("sort"));
				goods.setColor(rs.getString("color"));
				goods.setPicId(rs.getInt("PicId"));
				goods.setTime(rs.getString("time"));
				goods.setDel(rs.getString("del"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Goods> selectDeleted(Connection conn){
		String sql = "select * from t_goods where del=false";
		List<Goods> list = new ArrayList<Goods>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("instroduction"));
				goods.setSort(rs.getString("sort"));
				goods.setColor(rs.getString("color"));
				goods.setPicId(rs.getInt("PicId"));
				goods.setTime(rs.getString("time"));
				goods.setDel(rs.getString("del"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
