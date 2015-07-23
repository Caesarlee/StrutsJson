package com.seller.action.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class OrderDetailAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String order_id;
	private String shop_id;

	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
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

	public String displayorder() {
		ResultSet rs11 = null;
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = DatabaseConn.getConnection();
			stmt = conn.createStatement();
			// 选出商家的所有点单
			String str1 = "select * from dingdan_details  where order_id='"
					+ order_id + "'";
			rs11 = stmt.executeQuery(str1);

			List<OrderDetail> list1 = new ArrayList<OrderDetail>();

			while (rs11.next()) {
				OrderDetail od = new OrderDetail();
				od.setOrder_id(rs11.getString(2));
				od.setOrder_product_name(rs11.getString(4));
				od.setOrder_product_count(rs11.getString(5));
				od.setOrder_product_price(rs11.getString(6));
				od.setOrder_product_id(rs11.getString(3));
				
				list1.add(od);
			}

			
			// 设置storeId,从而保证订单中心的返回按钮可用
			//request.setAttribute("storeId", this.order_id);
			//设置shop_id属性，从而保证订单详情页面的返回按钮可用、
	//		request.setAttribute("shop_id", this.shop_id);
			request.setAttribute("orderDetail", list1);
			return SUCCESS;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs11 != null) {
					rs11.close();
					rs11 = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {

			}

		}
		// 设置storeId,从而保证订单中心的返回按钮可用
		//request.setAttribute("storeId", this.order_id);
		//设置shop_id属性，从而保证订单详情页面的返回按钮可用、
		//request.setAttribute("shop_id", this.shop_id);
		request.setAttribute("orderDetail", null);
		return "failure";
	}

}
