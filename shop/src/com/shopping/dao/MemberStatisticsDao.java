package com.shopping.dao;

import java.sql.Connection;
import java.util.List;

public interface MemberStatisticsDao {
	public List<Object> selectAll(Object o, Connection conn);
	public Object selectById(Object o, Connection conn);
	public Boolean insert(Object obj, Connection conn);
	public Boolean deleteById(Object obj, Connection conn);
	public Boolean updateExId(Object obj, Connection conn);
	public Object select(String id, Object o, Connection conn);
	public Boolean update(String id, Object o, Connection conn);
	
	public int selectCount(String date, Connection conn);
	public int selectAvgCost(String date, Connection conn);
	public int selectAvgTime(String date, Connection conn);
	public int selectCoreMember(String date, int coreTime, int coreCost, Connection conn);
	public int selectEdgeMember(String date, int edgeTime, int edgeCost, Connection conn);
}
