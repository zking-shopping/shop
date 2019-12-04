package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.dao.BaseDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.MemberStatisticsDao;

public class MemberStatisticsDaoImpl extends BaseDao implements MemberStatisticsDao{

	@Override
	public int selectCount(String date, Connection conn) {
		String sql = "select count(*) from t_memberstatistics where date=?";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
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
	public int selectAvgCost(String date, Connection conn) {
		String sql = "select avg(cost) from t_memberstatistics where date=?";
		int avg = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				avg = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avg;
	}

	@Override
	public int selectAvgTime(String date, Connection conn) {
		String sql = "select avg(time) from t_memberstatistics where date=?";
		int avg = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				avg = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avg;
	}

	@Override
	public int selectCoreMember(String date, int coreTime, int coreCost,
			Connection conn) {
		String sql = "select count(*) from t_memberstatistics where date=? and time>? and cost>?";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setInt(2, coreTime);
			ps.setInt(3, coreCost);
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
	public int selectEdgeMember(String date, int edgeTime, int edgeCost, Connection conn) {
		String sql = "select count(*) from t_memberstatistics where date=? and time<? and cost<?";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, date);
			ps.setInt(2, edgeTime);
			ps.setInt(3, edgeCost);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
