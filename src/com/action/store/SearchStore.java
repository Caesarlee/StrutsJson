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

public class SearchStore extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private String message;
	private List<Store> storesList = new ArrayList<Store>();
	HttpServletRequest request;
	HttpServletResponse response;
	private String storeName;

	@JSON(serialize = false)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

		System.out.println("storeName:"+this.storeName);
		if (accessDB(this.storeName)) {
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
		ResultSet rs = null, rs1 = null;
		Statement stmt = null, stmt1 = null;

		String query = null;
		String sqlStr = null;
		System.out.println("accessDB storeName:"+name);
		if (name == null || name.equals("")) {

			System.out.println("storeName is null!!");
			return false;

		} else {
			query = "select * from shop";
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			rs = stmt.executeQuery(query);
			Store store;
			String shop_id = null;
			String comment_avg = null;
			storesList.clear();
			String shop_name = null;
			while (rs.next()) {
				shop_name = rs.getString("shop_name");
				if (shop_name.equals(name) || shop_name.contains(name)
						|| name.contains(shop_name)) {
					store = new Store();
					store.setName(shop_name);
					// store.setRank("5");
					store.setBase_price(rs.getString("qisongjia"));
					store.setSend_price(rs.getString("peisong"));
					store.setPicturePath(rs.getString("shop_picture"));

					// 查询数据库找出shop_id对应的评论等级
					shop_id = rs.getString("shop_id");
					sqlStr = "select avg(comment_star) from comment where shop_id='"
							+ shop_id + "'";
					rs1 = stmt1.executeQuery(sqlStr);
					if (rs1.next()) {
						comment_avg = rs1.getString(1);
					}
					// 查询完毕
					store.setRank(comment_avg);
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
