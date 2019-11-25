package com.shopping.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHe {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_shoppingcenter","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		try {
			if((conn != null) && (!conn.isClosed())){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
