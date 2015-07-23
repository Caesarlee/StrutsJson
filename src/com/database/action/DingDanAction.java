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

public class DingDanAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private List<DingDan> orders = new ArrayList<DingDan>();

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

	public String fetchOrders() throws IOException {

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

		String query = "select * from dingdan";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			DingDan dd = null;
			while (rs.next()) {
				dd = new DingDan();
				dd.setOrder_id(rs.getString(1));
				dd.setOrder_address(rs.getString(2));
				dd.setOrder_pay_way(rs.getString(3));
                dd.setOrder_peisong(rs.getString(4));
                dd.setOrder_total(rs.getString(5));
                dd.setOrder_condition(rs.getString(6));
                dd.setOrder_remarks(rs.getString(7));
                dd.setShop_id(rs.getString(8));
                dd.setCustomer_id(rs.getString(9));
                dd.setOrder_date(rs.getString(10));
                orders.add(dd);
                
			}
			request.setAttribute("orders", this.orders);
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
