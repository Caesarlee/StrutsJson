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

public class DingDanDetailsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private List<DingDanDetails> details = new ArrayList<DingDanDetails>();

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

	public String fetchOrderDetail() throws IOException {

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

		String query = "select * from dingdan_details";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			DingDanDetails ddd=null;
			while (rs.next()) {
				ddd = new DingDanDetails();
	            ddd.setOrder_details_id(rs.getString(1));
	            ddd.setOrder_id(rs.getString(2));
	            ddd.setOrder_product_id(rs.getString(3));
	            ddd.setOrder_product_name(rs.getString(4));
	            ddd.setOrder_product_count(rs.getString(5));
	            ddd.setOrder_product_price(rs.getString(6));
                details.add(ddd);
                
			}
			request.setAttribute("details", this.details);
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
