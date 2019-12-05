package com.shopping.dao;

import java.sql.Connection;
import java.util.List;

import com.shopping.dto.GoodsStatisticsDto;

public interface GoodsStatisticsDao {
	public List<Object> selectAll(Object o, Connection conn);
	public Object selectById(Object o, Connection conn);
	public Boolean insert(Object obj, Connection conn);
	public Boolean deleteById(Object obj, Connection conn);
	public Boolean updateExId(Object obj, Connection conn);
	public Object select(String id, Object o, Connection conn);
	public Boolean update(String id, Object o, Connection conn);
	
	public List<GoodsStatisticsDto> selectSortByClick(int pageNumber, int pageSize, Connection conn);
	public List<GoodsStatisticsDto> selectSortByBuyNumber(int pageNumber, int pageSize, Connection conn);
	
	public int selectClickSumBySort(String sort, String time, Connection conn);
	public int selectBuySumBySort(String sort, String time, Connection conn);
}
