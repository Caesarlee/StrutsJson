package com.database.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class ShopAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private List<Shop> shops = new ArrayList<Shop>();

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String fetchShops() throws IOException {

		
		if (accessDB()) {

			return SUCCESS;
		} else {
			return "failure";
		}

	}

	public boolean accessDB() {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select * from shop";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			Shop s=null;
			while (rs.next()) {
				s=new Shop();
                s.setShop_id(rs.getString(1));
                s.setShop_account(rs.getString(2));
                s.setShop_password(rs.getString(3));
                s.setShop_name(rs.getString(4));
                s.setQisongjia(rs.getString(5));
                s.setPeisong(rs.getString(6));
                s.setOpen_time(rs.getString(7));
                s.setShop_address(rs.getString(8));
                s.setWuliu(rs.getString(9));
                s.setShop_phone_number(rs.getString(10));
                s.setShop_picture(rs.getString(11));
                s.setShop_kind(rs.getString(12));
                s.setShop_remarks(rs.getString(13));
                s.setShop_permit(rs.getString(14));
                s.setShop_jingdu(rs.getString(15));
                s.setShop_weidu(rs.getString(16));
                
                shops.add(s);
			}
			request.setAttribute("shops", this.shops);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
