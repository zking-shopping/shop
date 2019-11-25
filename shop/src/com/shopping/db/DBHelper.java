package com.shopping.db;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DBHelper {
	private static Context context = null;
	private static DataSource ds = null;
	private static Document doc = null;
	private static SAXReader reader = new SAXReader();
	private static List<Document> mappingList = new ArrayList<Document>();
	static{
		InputStream is = DBHelper.class.getResourceAsStream("/sql.xml");
		try{
			reader.setEncoding("UTF-8");
			doc = reader.read(is);
			context = new InitialContext();
			getDataSource();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource(){
		try{
			ds = (DataSource)context.lookup("java:comp/env/mysql");
		}catch(Exception e){
			e.printStackTrace();
		}
		return ds;
	}
	public static Connection getConnection(){
		Connection conn = null;
		try{
			if(ds != null){
				conn = ds.getConnection();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection(Connection conn){
		try{
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadMappings(){
		List<Element> list = doc.selectNodes("/mappings/mapping");
		System.out.println(list);
		for (Element element : list) {
			String path = element.attributeValue("path");
			try {
				Document mappingdoc = reader.read(DBHelper.class.getResourceAsStream("/"+path));
				mappingList.add(mappingdoc);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
	public static Document getDocumentByClass(Class c){
		loadMappings();
		for (Document document : mappingList) {
			Attribute attr = (Attribute) document.selectSingleNode("/class/@name");
			String className = attr.getValue();
			try {
				if(Class.forName(className) == c){
					return document;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
