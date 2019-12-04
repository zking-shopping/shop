package com.shopping.dao.daoImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;

import com.shopping.dao.BaseDao;
import com.shopping.dao.MemberDao;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;

public class MemberDaoImpl extends BaseDao implements MemberDao{
	public Boolean insert(Object obj, Connection conn){
		Class c = obj.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute table = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = table.getValue();
		Field[] fields = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(tableName);
		sb.append(" (");
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			sb.append(fieldName);
			if(i != fields.length-1){
				sb.append(",");
			}
		}
		sb.append(")  values (");
		for (int i = 0; i < fields.length; i++) {
			sb.append("?");
			if(i != fields.length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sb.toString());
			for (int i = 0; i < fields.length; i++) {
				String methodName = "get" + fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
				Method m = c.getDeclaredMethod(methodName, null);
				ps.setObject(i+1, m.invoke(obj, null));
			}
			int result = ps.executeUpdate();
			if(result > 0){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public int selectMax(int pageSize, Connection conn) {
		String sql = "select count(*) from t_member";
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
	
	public List<Member> selectByPages(int pageNumber, int pageSize, Connection conn) {
		String sql = "select * from t_member limit ?,?";
		List<Member> list = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNumber-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setStatistics(rs.getString("statistics"));
				member.setCost(rs.getString("cost"));
				member.setTime(rs.getString("time"));
				member.setDate(rs.getString("date"));
				member.setDel(rs.getString("del"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Member> selectDelByPages(int pageNumber, int pageSize, Connection conn) {
		String sql = "select * from t_member where del='true' limit ?,? ";
		List<Member> list = new ArrayList<Member>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (pageNumber-1)*pageSize);
			ps.setInt(2, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phoneNumber"));
				member.setStatistics(rs.getString("statistics"));
				member.setCost(rs.getString("cost"));
				member.setTime(rs.getString("time"));
				member.setDate(rs.getString("date"));
				member.setDel(rs.getString("del"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectCount(Connection conn) {
		String sql = "select count(*) from t_member";
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
		return count;
	}

	@Override
	public int selectAvgCost(Connection conn) {
		String sql = "select avg(cost) from t_member";
		int avg = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	public int selectAvgTime(Connection conn) {
		String sql = "select avg(time) from t_member";
		int avg = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	public int selectCoreMember(int coreTime, int coreCost, Connection conn) {
		String sql = "select count(*) from t_member where time>? and cost>?";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, coreTime);
			ps.setInt(2, coreCost);
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
	public int selectEdgeMember(int edgeTime, int edgeCost, Connection conn) {
		String sql = "select count(*) from t_member where time<? and cost<?";
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, edgeTime);
			ps.setInt(2, edgeCost);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int selectDelMax(int pageSize, Connection conn) {
		String sql = "select count(*) from t_member where del='true'";
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
}
