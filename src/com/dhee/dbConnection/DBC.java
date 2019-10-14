package com.dhee.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * 单例模式，通过传入的参数获取连接对象Connection
 * 
 * @author Administrator
 *
 */
public class DBC {
	private static Connection con;

	static {// 注册驱动，保证驱动只被注册一次
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon(String url, String user, String password) {// 获取Connection的方法
		try {
			if (con == null) {
				con = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

}
