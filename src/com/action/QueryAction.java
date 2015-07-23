package com.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class QueryAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	private String userName;


	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	//�û�ע�����ݵĲ�ѯ
	private String customer_id;
	private String customer_name;
	private String customer_password;
	private String customer_phone_number;
	private String address_shi;
	private String address_qu;
	private String address_xiangxi;
	public void query() {

		this.response.setContentType("text/json;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		Map<String, String> json = new HashMap<String, String>();
		if (accessDB(this.userName)) {

			json.put("message", "success");
			json.put("customer_id",this.customer_id);
			json.put("customer_name",this.customer_name);
			json.put("customer_password",this.customer_password);
			json.put("customer_phone_number",this.customer_phone_number);
			json.put("address_shi",this.address_shi);
			json.put("address_qu",this.address_qu);
			json.put("address_xiangxi",this.address_xiangxi);
			
			
		} else {
			json.put("message", "failure");
			json.put("userName",this.userName);
			
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

	public boolean accessDB(String name) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null,rs1=null;
		Statement stmt = null;

		String query = "select * from customer where customer_name='" + name
				+ "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				customer_id=rs.getString(1);
				customer_name=rs.getString(2);
				customer_password=rs.getString(3);
				customer_phone_number=rs.getString(4);
				System.out.println("�û�ID:"+customer_id+"\t�û���:"+customer_name+"\t�û�����:"+customer_password+"\t�û��绰:"+customer_phone_number);
				
				//��ѯaddress
				String str="select * from address where customer_id='"+customer_id+"'";
				rs1=stmt.executeQuery(str);
				if(rs1.next()){
					address_shi=rs1.getString(4);
					address_qu=rs1.getString(5);
					address_xiangxi=rs1.getString(6);
					System.out.println("����:"+address_shi+"\t��:"+address_qu+"\t��ϸ:"+address_xiangxi);
					
					
				}
				
				
				
				
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (rs1 != null) {
				rs1.close();
				rs1 = null;
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

		return false;
	}

}
