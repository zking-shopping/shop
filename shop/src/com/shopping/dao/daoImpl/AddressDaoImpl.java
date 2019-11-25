package com.shopping.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.AddressDao;
import com.shopping.dao.BaseDao;
import com.shopping.pojo.Address;

public class AddressDaoImpl extends BaseDao implements AddressDao {

	@Override
	public List<Address> selectByMemberId(String memberId, Connection conn) {
		String sql = "select * from t_address where memberId=?";
		List<Address> list = new ArrayList<Address>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Address addr = new Address();
				addr.setId(rs.getInt("id"));
				addr.setMemberId(rs.getString("memberId"));
				addr.setCousignee(rs.getString("cousignee"));
				addr.setPhoneNumber(rs.getString("phoneNumber"));
				addr.setProvinces(rs.getString("provinces"));
				addr.setCity(rs.getString("city"));
				addr.setArea(rs.getString("area"));
				addr.setDetailAddress(rs.getString("detailAddress"));
				list.add(addr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
