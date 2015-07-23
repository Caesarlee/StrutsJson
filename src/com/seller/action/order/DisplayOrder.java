package com.seller.action.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class DisplayOrder extends ActionSupport implements ServletRequestAware,
		ServletResponseAware,SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	Map<String,Object> session;
	private String shop_id;

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
		this.shop_id=(String)session.get("shop_id");
		
		ResultSet rs11 = null;
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = DatabaseConn.getConnection();
			stmt = conn.createStatement();
			// 选出商家的所有点单
			String str1 = "select order_id,order_address,order_pay_way,order_total,order_remarks,order_date,customer_name,customer_phone_number,order_condition,shop_id from dingdan natural join customer  where shop_id='"
					+ shop_id + "'";
			rs11 = stmt.executeQuery(str1);

			List<OrderToShop> list1 = new ArrayList<OrderToShop>();

			while (rs11.next()) {
				OrderToShop o = new OrderToShop();
				o.setOrder_id(rs11.getString(1));
				o.setOrder_address(rs11.getString(2));
				o.setOrder_pay_way(rs11.getString(3));
				o.setOrder_total(rs11.getString(4));
				o.setOrder_remarks(rs11.getString(5));
				o.setOrder_date(rs11.getString(6));
				o.setCustomer_name(rs11.getString(7));
				o.setCustomer_phone_number(rs11.getString(8));
				o.setOrder_condition(rs11.getString(9));
				o.setShop_id(rs11.getString(10));
				list1.add(o);
			}

			
			// 设置storeId,从而保证订单中心的返回按钮可用
		//	request.setAttribute("storeId", this.shop_id);
			request.setAttribute("orders", list1);
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
		//request.setAttribute("storeId", this.shop_id);
		request.setAttribute("orders", null);
		return "failure";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}

}
