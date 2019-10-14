package com.dhee.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 定义连接数据库字段，编写对数据库的增删改查，关闭等基本操作
 * 
 * @author Administrator
 *
 */
public class DBO {

	private final String url = "jdbc:mysql://localhost:3306/dhee_2?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false";
	private final String user = "root";
	private final String password = "11111111";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public DBO() {
		// TODO Auto-generated constructor stub
		try {
			this.connection = DBC.getCon(url, user, password);
			this.statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int save(String sql) {// 增
		int temp = 0;
		try {
			temp = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("save出错！");
			e.printStackTrace();
		}
		return temp;
	}

	public int delete(String sql) {// 删
		int temp = 0;
		try {
			temp = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("save出错！");
			e.printStackTrace();
		}
		return temp;
	}

	public int update(String sql) {// 改
		int temp = 0;
		try {
			temp = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("save出错！");
			e.printStackTrace();
		}
		return temp;
	}

	public ResultSet select(String sql) {// 查
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("save出错！");
			e.printStackTrace();
		}
		return resultSet;
	}

	public void closeRS() {// 关闭
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
