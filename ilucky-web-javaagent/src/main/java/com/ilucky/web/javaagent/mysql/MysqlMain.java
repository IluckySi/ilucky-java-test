package com.ilucky.web.javaagent.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlMain {

	public static String url = "jdbc:mysql://10.0.3.42:3306/tsb_test?useUnicode=true&characterEncoding=utf8";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "kevin";
	public static String password = "000000";
	
	public static void main(String[] args) {
		test(11);
	}
	
	public static void test(int i) {
		Connection conn = getConnection();
		String sql = "INSERT INTO test(name,id) VALUES (?,?) " ;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql); // 实例化PreapredStatement对象
			pstmt.setInt(2,i);
			pstmt.setString(1,"xxxx") ;
			int t = pstmt.executeUpdate() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
		
		 public static Connection getConnection() {
		        Connection conn = null;
		        try {
		            //加载驱动
		            Class.forName(driver);
		            //通过获取连接
		            conn = DriverManager.getConnection(url, user, password);
		            System.out.println(conn);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return conn;
		    }
}
