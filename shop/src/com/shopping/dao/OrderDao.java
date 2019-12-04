package com.shopping.dao;

import java.sql.Connection;
import java.util.List;

import com.shopping.pojo.Order;

public interface OrderDao {
	public List<Object> selectAll(Object o, Connection conn);
	public Object selectById(Object o, Connection conn);
	public Boolean insert(Object obj, Connection conn);
	public Boolean deleteById(Object obj, Connection conn);
	public Boolean updateExId(Object obj, Connection conn);
	public Object select(String id, Object o, Connection conn);
	public Boolean update(String id, Object o, Connection conn);
	
	public List<Order> selectByMemberId(String MemberId, String state, Connection conn);
	
	public List<Order> selectAllByState(String state, Connection conn);
}
