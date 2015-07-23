package com.seller.action.location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.action.lzq.DatabaseConn;

public class test {

	public static void main(String[] args) throws SQLException, NamingException {
		// TODO Auto-generated method stub
		Connection conn=DatabaseConn.getConnection();
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String str00="select shop_jingdu,shop_weidu,shop_name,peisong,qisongjia,shop_picture from shop";
		ResultSet rs00=stmt.executeQuery(str00);
		
		int countTemp=0;
		while(rs00.next()){
		//	double temp=gps(lat_1,lung_1,rs00.getDouble(2),rs00.getDouble(1));
		//	list[countTemp]=temp;
			countTemp++;
		}
		System.out.println(countTemp);
		rs00.first();
		do{
			System.out.println(rs00.getString(3));
		}while(rs00.next());
	}

}
