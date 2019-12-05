package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dto.GoodsStatisticsDto;

public class GoodsStatisticsImpl extends BaseDao implements GoodsStatisticsDao{

	@Override
	public List<GoodsStatisticsDto> selectSortByClick(int pageNumber, int pageSize, Connection conn) {
		String sql = "select * from t_goodsstatistics,t_goods,t_type where t_goodsstatistics.goodsId=t_goods.id and t_goods.sort=t_type.id order by clickNumber DESC";
		if(pageNumber != 0 && pageSize != 0){
			sql = sql + " limit ?,?";
		}
		List<GoodsStatisticsDto> list = new ArrayList<GoodsStatisticsDto>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			if(pageNumber != 0 && pageSize != 0){
				ps.setInt(1, (pageNumber-1)*pageSize);
				ps.setInt(2, pageSize);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				GoodsStatisticsDto goods = new GoodsStatisticsDto();
				set(rs, goods);
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<GoodsStatisticsDto> selectSortByBuyNumber(int pageNumber,
			int pageSize, Connection conn) {
		String sql = "select * from t_goodsstatistics,t_goods,t_type where t_goodsstatistics.goodsId=t_goods.id and t_goods.sort=t_type.id order by buyNumber DESC";
		if(pageNumber != 0 && pageSize != 0){
			sql = sql + " limit ?,?";
		}
		List<GoodsStatisticsDto> list = new ArrayList<GoodsStatisticsDto>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			if(pageNumber != 0 && pageSize != 0){
				ps.setInt(1, (pageNumber-1)*pageSize);
				ps.setInt(2, pageSize);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				GoodsStatisticsDto goods = new GoodsStatisticsDto();
				set(rs, goods);
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public GoodsStatisticsDto set(ResultSet rs, GoodsStatisticsDto goods) throws SQLException{
		goods.setGoodsId(rs.getInt("id"));
		goods.setGoodsName(rs.getString("goodsName"));
		goods.setPrice(rs.getString("price"));
		goods.setIntroduction(rs.getString("introduction"));
		goods.setType(rs.getString("name"));
		goods.setColor(rs.getString("color"));
		goods.setPicId(rs.getInt("picId"));
		goods.setTime(rs.getString("time"));
		goods.setClickNumber(rs.getInt("clickNumber"));
		goods.setBuyNumber(rs.getInt("buyNumber"));
		return goods;
	}

	@Override
	public int selectClickSumBySort(String sort, String time, Connection conn) {
		String sql = "select sum(clickNumber) from t_goods,t_goodsstatistics where sort=? and t_goodsstatistics.time=? and t_goods.id = t_goodsstatistics.goodsId";
		int sum = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sort);
			ps.setString(2, time);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public int selectBuySumBySort(String sort, String time, Connection conn) {
		String sql = "select sum(buyNumber) from t_goods,t_goodsstatistics where sort=? and t_goodsstatistics.time=? and t_goods.id = t_goodsstatistics.goodsId";
		int sum = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sort);
			ps.setString(2, time);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
}
