package com.seller.action.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

public class DatabaseConn {
	private static Connection conn;
	public static Connection getConnection() throws SQLException,NamingException{
		try{
			String url = "jdbc:mysql://localhost:3306/";   
		    String dbName = "communityec";   
		    String driverName = "com.mysql.jdbc.Driver";   
		    String userName = "root";   
		    String password = "1234";   
		     
		    
		    Class.forName(driverName).newInstance();   
		    conn=DriverManager.getConnection(url+dbName,userName,password);   
		System.out.println("成功连接数据库");
		}catch(Exception e){
	
			e.printStackTrace();
		}
		return conn;
	}
	
	/*public static void main(String[]agrs){
		DatabaseConn conn=new DatabaseConn();
		try {
			conn.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
