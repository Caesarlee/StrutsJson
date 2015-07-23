package com.seller.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class OpenOrClose extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	Map<String, Object> session;
	private String shop_id = null;
	private String openOrClose = null;
	private String message=null;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JSON(serialize = false)
	public String getOpenOrClose() {
		return openOrClose;
	}

	public void setOpenOrClose(String openOrClose) {
		this.openOrClose = openOrClose;
	}
	@JSON(serialize = false)
	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

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

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String openOrClose() {
		//shop_id = (String) session.get("shop_id");
		System.out.println("OpenOrClose->shop_id:" + this.shop_id);
		System.out.println("OpenOrClose->openOrClose:"+this.openOrClose);

		if (accessDB(this.shop_id, this.openOrClose)) {
            message="success";
			return "success";
		} else {
            message="failure";
			return "failure";
		}

	}

	public boolean accessDB(String id, String condition) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;

		Statement stmt = null;
		String query = null;
		if (condition.equals("open")) {
			query = "update shop set opening='Y' where shop_id='" + id + "'";
		} else if (condition.equals("close")) {
			query = "update shop set opening='N' where shop_id='" + id + "'";
		} else {
			return false;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);

			if (result == 1) {
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

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
