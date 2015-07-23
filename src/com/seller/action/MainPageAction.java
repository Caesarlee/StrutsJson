package com.seller.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class MainPageAction  extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shop_account=null;
	private String shop_id=null;
	private List<Product> products=new ArrayList<Product>();
	HttpServletRequest request;
	Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=arg0;
	}
	public String execute() throws Exception{
		
		request=ServletActionContext.getRequest();
		
		shop_account=(String)session.get("shop_account");
		
		shop_id=(String)session.get("shop_id");
		
		System.out.println("MainPage->shop_account:"+this.shop_account);
		System.out.println("MainPage->shop_id:"+this.shop_id);
		if(accessDB(this.shop_account)){
			
			return SUCCESS;
		}
		
		return "failure";
		
		
		
	}
	private boolean accessDB(String shopAccount) {
		/*if(shop_id!=null){
			
		}else{
			shopId(shopAccount);
		}*/
	
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select * from product where shop_id='" +this.shop_id 
				+ "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			Product product;
			while (rs.next()) {
				product=new Product();
				product.setProduct_id(rs.getString("product_id"));
				product.setShop_id(rs.getString("shop_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_picture(rs.getString("product_picture"));
				product.setProduct_sales(rs.getString("product_sales"));
				product.setProduct_price(rs.getString("product_price"));
				product.setProduct_kind(rs.getString("product_kind"));
				product.setProduct_kucun(rs.getString("product_kucun"));
				products.add(product);
				
			}
			//设置此属性,从而让添加商品链接可访问
			//session.put("shop_id", this.shop_id);
			//使用session代替request
			//request.setAttribute("storeId", this.shop_id);
			request.setAttribute("product", products);
			return true;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       // session.put("shop_id", this.shop_id);
        //使用session代替request
		//request.setAttribute("storeId", this.shop_id);
		request.setAttribute("product", products);		
		return false;
	}
	/*private void shopId(String account) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select shop_id from shop where shop_account='" + account
				+ "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				shop_id=rs.getString("shop_id");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}*/
	
}
