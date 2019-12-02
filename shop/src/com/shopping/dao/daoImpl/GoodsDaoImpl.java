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
		List<Goods> list = null;
		PreparedStatement ps;
		try {
			list = new ArrayList<Goods>();
			ps = conn.prepareStatement(sql);
			ps.setString(1, sort);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("introduction"));
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
		List<Goods> list = null;
		try {
			list = new ArrayList<Goods>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("introduction"));
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

	@Override
	public int selectMax(int pageSize, Connection conn) {
		String sql = "select count(*) from t_goods";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count%pageSize == 0 ? count/pageSize : count/pageSize+1;
	}

	@Override
	public List<Goods> selectByPage(int pageNumber, int pageSize,
			Connection conn) {
		String sql = "select * from t_goods limit ?,?";
		List<Goods> list = null;
		try {
			list = new ArrayList<Goods>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNumber-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("introduction"));
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

	@Override
	public List<Goods> selectAllNoDel(Connection conn) {
		String sql = "select * from t_goods where del=false";
		List<Goods> list = null;
		try {
			list = new ArrayList<Goods>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("introduction"));
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

	@Override
	public int selectSorCount(int sort, int pageSize, Connection conn) {
		String sql = "select count(*) from t_goods where sort=? and del=false";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sort);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


	@Override
	public List<Goods> selectSortPage(int sort, int pageNumber, int pageSize,
			Connection conn) {
		String sql = "select * from t_goods where sort=? and del=false limit ?,?";
		List<Goods> list = null;
		try {
			list = new ArrayList<Goods>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sort);
			ps.setInt(2, (pageNumber-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Goods goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getString("price"));
				goods.setIntroduction(rs.getString("introduction"));
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
