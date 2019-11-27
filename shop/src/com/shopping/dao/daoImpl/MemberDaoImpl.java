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
}
