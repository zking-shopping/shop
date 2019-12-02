package com.shopping.dao;

import java.sql.Connection;
import java.util.List;

import com.shopping.pojo.Color;

public interface ColorDao {
	public List<Object> selectAll(Object o, Connection conn);
	public Object selectById(Object o, Connection conn);
	public Boolean insert(Object obj, Connection conn);
	public Boolean deleteById(Object obj, Connection conn);
	public Boolean updateExId(Object obj, Connection conn);
	public Object select(String id, Object o, Connection conn);
	public Boolean update(String id, Object o, Connection conn);
	
	public List<Color> selectByGoodsId(String goodsId, Connection conn);
}
