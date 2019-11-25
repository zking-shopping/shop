package com.shopping.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.w3c.dom.Attr;

import com.shopping.db.DBHelper;

public abstract class BaseDao {
	public List<Object> selectAll(Object o, Connection conn){
		Class c = o.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute table = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = table.getValue();
		Field[] fields = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(tableName);
		PreparedStatement ps;
		List<Object> list = new ArrayList<Object>();
		try {
			ps = conn.prepareStatement(sb.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Object obj = c.newInstance();
				for (int i = 0; i < fields.length; i++) {
					String methodName = "set" + fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
					Method m = c.getDeclaredMethod(methodName, fields[i].getType());
					m.invoke(obj, rs.getObject(fields[i].getName()));
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
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
		return list;
	}
	
	public Object selectById(Object o, Connection conn){
		Class c = o.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute table = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = table.getValue();
		Field[] fields = c.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(tableName);
		sb.append(" where id=?");
		PreparedStatement ps;
		Object obj = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			Method method = c.getDeclaredMethod("getId", null);
			ps.setObject(1, method.invoke(o, null));
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				obj = c.newInstance();
				for (int i = 0; i < fields.length; i++) {
					String methodName = "set" + fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
					Method m = c.getDeclaredMethod(methodName, fields[i].getType());
					m.invoke(obj, rs.getObject(fields[i].getName()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
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
		return obj;
	}
	
	//	未插入id
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
		for (int i = 1; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			sb.append(fieldName);
			if(i != fields.length-1){
				sb.append(",");
			}
		}
		sb.append(")  values (");
		for (int i = 1; i < fields.length; i++) {
			sb.append("?");
			if(i != fields.length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fields.length; i++) {
				String methodName = "get" + fields[i].getName().substring(0,1).toUpperCase() + fields[i].getName().substring(1);
				Method m = c.getDeclaredMethod(methodName, null);
				ps.setObject(i, m.invoke(obj, null));
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

	//	根据id删除某条记录，非该del属性
	public Boolean deleteById(Object obj, Connection conn){
		Class c = obj.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute table = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = table.getValue();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(tableName);
		sb.append(" where id=?");
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			Method method = c.getDeclaredMethod("getId", null);
			ps.setObject(1, method.invoke(obj, null));
			int i = ps.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//	通过id进行更新
	public Boolean updateExId(Object obj, Connection conn){
		Class c = obj.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute attr = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = attr.getValue();
		Field[] fields = c.getDeclaredFields();
		List<String> methodNames = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		for (int i = 1; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			methodNames.add(methodName);
			sb.append(fieldName);
			if(i != fields.length-1){
				sb.append("=?, ");
			}
		}
		sb.append("=? where id=?");
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fields.length; i++) {
				Method m = c.getDeclaredMethod(methodNames.get(i-1), null);
				ps.setObject(i, m.invoke(obj, null));
			}
			Method idMethod = c.getDeclaredMethod("getId", null);
			ps.setObject(fields.length, idMethod.invoke(obj, null));
			int i = ps.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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

	//	配置方式的select方法
	public Object select(String id, Object o, Connection conn){
		Class c = o.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute attr = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = attr.getValue();
		Attribute attr2 = (Attribute) doc.selectSingleNode("/class/select[@id='" + id + "']/@target");
		String target = attr2.getValue();
		System.out.println("selectForLogin" + target);
		Element ele = (Element)doc.selectSingleNode("/class/select[@id='" + id + "']");
		
		String str = ele.getTextTrim();
		Pattern p = Pattern.compile("#[{](\\w+)[}]");
		Matcher m = p.matcher(str);
		int count = 0;
		List<String> find = new ArrayList<String>();
		while(m.find()){
			count++;
			find.add(m.group(1));
		}
		String sql = str.replaceAll("#[{](\\w+)[}]", "?");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= count; i++) {
				String name = find.get(i-1);
				String s = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
				ps.setObject(i, c.getMethod(s, null).invoke(o, null));
			}
			ResultSet rs = ps.executeQuery();
			Class targetClass = Class.forName(target);
			Object obj = targetClass.newInstance();
			Field[] fields = targetClass.getDeclaredFields();
			if(rs.next()){
				for (int i = 0; i < fields.length; i++) {
					String methodName = "set" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
					Method method = targetClass.getDeclaredMethod(methodName, fields[i].getType());
					method.invoke(obj, rs.getObject(fields[i].getName()));
				}
			}
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//	
	public Boolean update(String id, Object o, Connection conn){
		Class c = o.getClass();
		Document doc = DBHelper.getDocumentByClass(c);
		Attribute attr = (Attribute) doc.selectSingleNode("/class/@table");
		String tableName = attr.getValue();
		Element ele = (Element)doc.selectSingleNode("/class/update[@id='" + id + "']");
		String str = ele.getTextTrim();
		Pattern p = Pattern.compile("#[{](\\w+)[}]");
		Matcher m = p.matcher(str);
		int count = 0;
		List<String> find = new ArrayList<String>();
		while(m.find()) {
			count++;
			find.add(m.group(1));
		}
		String sql = str.replaceAll("#[{](\\w+)[}]", "?");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= count; i++) {
				String name = find.get(i-1);
				String s = "get"+name.substring(0, 1).toUpperCase()+name.substring(1);
				ps.setObject(i, c.getMethod(s, null).invoke(o, null));
			}
			int i = ps.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
