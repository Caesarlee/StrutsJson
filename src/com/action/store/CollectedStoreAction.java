package com.action.store;

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
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class CollectedStoreAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private String message;

	HttpServletRequest request;
	HttpServletResponse response;
	private List<Store> storesList = new ArrayList<Store>();

	private String userName;

	@JSON(serialize = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Store> getStoresList() {
		return storesList;
	}

	public void setStoresList(List<Store> storesList) {
		this.storesList = storesList;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public String execute() throws Exception {

		if (accessDB(this.userName)) {
			message = "success";
			return SUCCESS;
		}
		message = "failure";
		return SUCCESS;

	}

	public boolean accessDB(String name) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null, rs1 = null, rs2 = null;
		Statement stmt = null, stmt1 = null, stmt2 = null;

		
		if (name == null) {
			return false;
		}
		String customer_id = null;
		String query = "select customer_id from customer where customer_name='"
				+ name + "'";
		String sql = null, sqlQuery = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				customer_id = rs.getString("customer_id");
			} else {
				return false;
			}
			// 从用户收藏表中选出shop_id
			sql = "select shop_id from customer_collection where customer_id='"
					+ customer_id + "'";
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			rs1 = stmt1.executeQuery(sql);
			Store store = null;
			String star = null;
			String shopId = null;
			if(!rs1.next()){
				return false;
			}
			rs1.previous();
			while (rs1.next()) {
				shopId = rs1.getString("shop_id");
				sqlQuery = "select * from shop where shop_id='" + shopId + "'";
				rs2 = stmt2.executeQuery(sqlQuery);
				if (rs2.next()) {
					store = new Store();
					store.setName(rs2.getString("shop_name"));
					star = shopStar(shopId);
					store.setRank(star);
					store.setBase_price(rs2.getString("qisongjia"));
					store.setSend_price(rs2.getString("peisong"));
					store.setPicturePath(rs2.getString("shop_picture"));
					storesList.add(store);
				}

			}

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
				if (rs1 != null) {
					rs1.close();
					rs1 = null;
				}
				if (rs2 != null) {
					rs2.close();
					rs2 = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (stmt1 != null) {
					stmt1.close();
					stmt1 = null;
				}
				if (stmt2 != null) {
					stmt2.close();
					stmt2 = null;
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

	public String shopStar(String id) {
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "select avg(comment_star) from comment where shop_id='"
				+ id + "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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

		return null;

	}

}
