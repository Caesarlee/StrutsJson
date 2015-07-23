package com.action.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class DetailOrderAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private String message;

	HttpServletRequest request;
	HttpServletResponse response;
	private String orderId;
	DetailOrder order = new DetailOrder();;

	public DetailOrder getOrder() {
		return order;
	}

	public void setOrder(DetailOrder order) {
		this.order = order;
	}

	@JSON(serialize = false)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

		System.out.println(this.orderId);
		if (accessDB(this.orderId)) {
			message = "success";
			return SUCCESS;
		}
		message = "failure";
		return SUCCESS;

	}

	public boolean accessDB(String orderId) {

		
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null, rs1 = null, rs2 = null;
		Statement stmt = null, stmt1 = null, stmt2 = null;

		String query = null;
		String sqlStr = null;
		String sqlQuery = null;// 查询是否已经评价
		query = "select * from dingdan where order_id='" + orderId + "'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			rs = stmt.executeQuery(query);

			String shop_id;
			String shop_name = null;
			String customer_id;
			String customer_phone = null;

			if(rs.next()) {

				order.setOrder_total(rs.getString("order_total"));
				order.setOrder_id(rs.getString("order_id"));
				order.setOrder_date(rs.getString("order_date"));
				order.setOrder_address(rs.getString("order_address"));
				customer_id = rs.getString("customer_id");
				// 查询数据库找出order_id对应的商店的name,pictures
				shop_id = rs.getString("shop_id");
				sqlStr = "select shop_name from shop where shop_id='" + shop_id
						+ "'";
				rs1 = stmt1.executeQuery(sqlStr);
				if (rs1.next()) {
					shop_name = rs1.getString("shop_name");

				}
				// 查询完毕
				order.setShop_name(shop_name);

				sqlQuery = "select * from customer where customer_id='"
						+ customer_id + "'";
				rs2 = stmt2.executeQuery(sqlQuery);
				if (rs2.next()) {
					customer_phone = rs2.getString("customer_phone_number");
				}
				order.setCustomer_phone(customer_phone);
				// 获取该orderId的商品详情
				fetchProducts(this.orderId);
			}else{
				return false;
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

	public void fetchProducts(String orderid) {
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String query = null;
		query = "select * from dingdan_details where order_id='" + orderid
				+ "'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			OrderProduct product;
			while (rs.next()) {
				product = new OrderProduct();
				product.setOrder_product_id(rs.getString("order_product_id"));
				product.setOrder_product_name(rs
						.getString("order_product_name"));
				product.setOrder_product_count(rs
						.getString("order_product_count"));
				product.setOrder_product_price(rs
						.getString("order_product_price"));
				order.getProducts().add(product);
			}

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

	}
}
