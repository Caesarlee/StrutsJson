package com.seller.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware,SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	//HttpSession session;
    Map<String,Object> session;
	private String shop_account=null;
	private String password;
    private String shop_id=null;


	public String getShop_account() {
		return shop_account;
	}

	public void setShop_account(String shop_account) {
		this.shop_account = shop_account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
       this.session=arg0;		
	}
	public String login() {

		System.out.println("shop_account:"+this.shop_account);
		System.out.println("passwd:"+this.password);
		 
		if (accessDB(this.shop_account,this.password)) {

			request.setAttribute("reason", "登陆成功");
			session.clear();//当一个商家未推出系统,另一个商家在同一个浏览器又登录时清除上次登录痕迹
		    session.put("shop_account", this.shop_account);
		    session.put("shop_id", this.shop_id);
		    //使用session代替request
			//request.setAttribute("shop_account", this.shop_account);
		    return "success";
		} else {
			
			return "failure";
		}

	}

	public boolean accessDB(String name, String passwd) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select shop_id,shop_permit from shop where shop_account='" + name
				+ "' and shop_password='" + passwd + "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				String permit="0";
				permit=rs.getString(2);
				if(permit.equals("0")){
					request.setAttribute("reason", "尚未通过审核!!");
					return false;
				}
				this.shop_id=rs.getString("shop_id");
				System.out.println("login->shop_id="+this.shop_id);
				
				return true;
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

		
		request.setAttribute("reason", "账号或密码出错");
		return false;
	}



}

