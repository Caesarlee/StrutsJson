package com.seller.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.seller.action.order.DatabaseConn;

public class StoreInfo extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
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

	public String storeInfo() {
		shop_id = (String) session.get("shop_id");
		System.out.println("StoreInfo->this.shop_id:"+this.shop_id);
		
		if (shop_id==null) {
			
			StoreEntity s=new StoreEntity();
			s.setShop_id(" ");
			s.setShop_account(" ");
			s.setShop_password(" ");
			s.setShop_name(" ");
			s.setQisongjia(" ");
			s.setPeisong(" ");
			s.setOpen_time(" ");
			s.setShop_address(" ");
			s.setWuliu(" ");
			s.setShop_phone_number(" ");
			s.setShop_picture(" ");
			s.setShop_kind(" ");
			s.setShop_remarks(" ");
			s.setShop_permit(" ");
			request.setAttribute("storeInfo", s);
			return "failure";
			

		}
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {

			conn = DatabaseConn.getConnection();
			stmt = conn.createStatement();
			// 选出商家的所有点单
			String str1 = "select * from shop where shop_id='" + shop_id + "'";


				rs = stmt.executeQuery(str1);
				StoreEntity store = new StoreEntity();
 
				while (rs.next()) {
					store.setShop_id(rs.getString(1));
					store.setShop_account(rs.getString(2));
					store.setShop_password(rs.getString(3));
					store.setShop_name(rs.getString(4));
					store.setQisongjia(rs.getString(5));
					store.setPeisong(rs.getString(6));
					store.setOpen_time(rs.getString(7));
					store.setShop_address(rs.getString(8));
					store.setWuliu(rs.getString(9));
					store.setShop_phone_number(rs.getString(10));
					store.setShop_picture(rs.getString(11));
					store.setShop_kind(rs.getString(12));
					store.setShop_remarks(rs.getString(13));
					store.setShop_permit(rs.getString(14));
				}
			
			System.out.println("StoreInfo的shop_id=" + this.shop_id);
			System.out.println("shop_picture:" + store.getShop_picture());

			// 设置storeId,从而保证商铺信息界面的返回按钮可用
			// request.setAttribute("storeId", this.shop_id);
			request.setAttribute("storeInfo", store);
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
			} catch (Exception e) {

			}

		}
		// 设置storeId,从而保证订单中心的返回按钮可用
		// request.setAttribute("storeId", this.shop_id);
		request.setAttribute("storeInfo", null);
		return "failure";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

}
