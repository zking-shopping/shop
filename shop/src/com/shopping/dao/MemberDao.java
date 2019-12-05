package com.shopping.dao;

import java.sql.Connection;
import java.util.List;

import com.shopping.pojo.Member;
import com.shopping.web.form.ChangeMyPersonInfoForm;

public interface MemberDao{
	public List<Object> selectAll(Object o, Connection conn);
	public Object selectById(Object o, Connection conn);
	public Boolean insert(Object obj, Connection conn);
	public Boolean deleteById(Object obj, Connection conn);
	public Boolean updateExId(Object obj, Connection conn);
	public Object select(String id, Object o, Connection conn);
	public Boolean update(String id, Object o, Connection conn);
	public int selectMax(int pageSize, Connection conn);
	public List<Member> selectByPages(int pageNumber, int pageSize, Connection conn);

	public Boolean updatePersonInfo(ChangeMyPersonInfoForm cmpif,Connection conn);

	
	public int selectDelMax(int pageSize, Connection conn);
	public List<Member> selectDelByPages(int pageNumber, int pageSize, Connection conn);
	
	public int selectCount(Connection conn);
	public int selectAvgCost(Connection conn);
	public int selectAvgTime(Connection conn);
	public int selectCoreMember(int coreTime, int coreCost, Connection conn);
	public int selectEdgeMember(int edgeTime, int edgeCost, Connection conn);

}
