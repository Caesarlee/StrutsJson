package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction2 extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String userName;
	private String password;
	private String phone;
    private String info=null;//标识登陆失败的原因
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void login() {

		 System.out.println("userName:" + this.userName + "\n" + "password:" + this.password);
		this.response.setContentType("text/json;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		Map<String, String> json = new HashMap<String, String>();
		if (accessDB(this.userName, this.password)) {

			info="null";
			json.put("message", "success");
			json.put("userName", this.userName);
			json.put("password", this.password);
			json.put("phone", this.phone);
			json.put("info", this.info);
		} else {
			json.put("message", "failure");
			json.put("userName", this.userName);
			json.put("password", this.password);
			json.put("phone", this.phone);
			json.put("info", this.info);
		}

		try {
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			this.response.setContentLength(jsonBytes.length);
			this.response.getOutputStream().write(jsonBytes);
			this.response.getOutputStream().flush();
			this.response.getOutputStream().close();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean accessDB(String name, String passwd) {

		Connection conn = null;
		ResultSet rs = null,rs1=null;
		Statement stmt = null,stmt1=null;
	   
	    
		String query = "select * from customer where customer_name='" + name+"'";
			//	+ "' and customer_password='" + passwd + "'";
		String query1="select * from customer where customer_name='"+name+"' and customer_password='"+passwd+"'";
		System.out.println("userName:" + name + "\n" + "password:" + passwd);
		try {
		    conn=DatabaseAccess.getConn();
			stmt = conn.createStatement();
			stmt1=conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				rs1=stmt1.executeQuery(query1);
				if(rs1.next()){
					phone=rs1.getString("customer_phone_number");
					return true;	
				}else{
					info="密码输入错误!!";
					return false;
				}
				
			}else{
				info="账号不存在!!";
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info="未知错误";
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
				DatabaseAccess.close();
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
