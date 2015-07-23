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

public class RegisterAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	private String userName=null;
	private String password=null;
	private String phone=null;
	private String city=null;
	private String district;
	private String addr;

	private String info=null;//注册失败的详细原因
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public void register() {

		this.response.setContentType("text/json;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		Map<String, String> json = new HashMap<String, String>();
		if (registerDB(this.userName, this.password, this.phone, this.city,
				this.district, this.addr)) {
			json.put("message", "success");
			json.put("userName",this.userName);
			json.put("password", this.password);
			json.put("city", this.city);
			json.put("info", this.info);
			
		}else{
			json.put("message", "failure");
			json.put("userName",this.userName);
			json.put("password", this.password);
			json.put("city", this.city);
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

	public boolean registerDB(String name, String pass, String phon,
			String city1, String dist, String add) {
		
		if(isExist(name)){
			return false;
		}
		System.out.println("userName:"+name);
		System.out.println("password:"+pass);
		System.out.println("phone:"+phon);
		System.out.println("city:"+city1);
		System.out.println("district:"+dist);
		System.out.println("addr:"+add);
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null,stmt1=null;

		String query = "insert into customer(customer_name,customer_password,customer_phone_number) values('"
				+ name + "','" + pass + "','" + phon + "')";
		String query1 = "select customer_id from customer where customer_name='"
				+ name + "' and customer_password='" + pass + "'";
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			rs = stmt.executeQuery(query1);
			if (rs.next()) {
				String query2 = "insert into address(customer_id,phone_number,address_shi,address_qu,address_xiangxi) values('"
						+ rs.getString("customer_id") + "','" + phon + "','"+city1+"','"+dist+"','"+add+"')";
				stmt1=conn.createStatement();
				int res=stmt1.executeUpdate(query2);
				if(res==1){
					info="注册成功!!";
					return true;
				}
				
			}else{
				info="插入数据表出错!!";
				return false;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			info="数据库操作失败";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info="数据库操作失败";
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
	public boolean isExist(String name){
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql="select * from customer where customer_name='"+name+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if (rs.next()) {
		         info="用户名已存在";
				return true;
			}else{
				return false;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			info="数据库操作失败";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info="数据库操作失败";
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			}catch(Exception exc){
				
			}
			
			
		}
		return false;
	}
}
