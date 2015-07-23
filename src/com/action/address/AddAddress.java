package com.action.address;

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

public class AddAddress extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String userName;//提交参数之用户名称
	private String phone_number;//提交参数之电话
	private String address_shi;//提交参数之市
	private String address_qu;//提交参数之区
	private String address_xiangxi;//提交参数之详细
	
	private String message=null;
	private String info=null;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@JSON(serialize = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JSON(serialize = false)
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@JSON(serialize = false)
	public String getAddress_shi() {
		return address_shi;
	}

	public void setAddress_shi(String address_shi) {
		this.address_shi = address_shi;
	}
	@JSON(serialize = false)
	public String getAddress_qu() {
		return address_qu;
	}

	public void setAddress_qu(String address_qu) {
		this.address_qu = address_qu;
	}
	@JSON(serialize = false)
	public String getAddress_xiangxi() {
		return address_xiangxi;
	}

	public void setAddress_xiangxi(String address_xiangxi) {
		this.address_xiangxi = address_xiangxi;
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

	public String execute() throws Exception {

		System.out.println("userName:" + this.userName + "\n");
		System.out.println("phone_number:" + this.phone_number + "\n");
		System.out.println("address_shi:" + this.address_shi + "\n");
		System.out.println("address_qu:" + this.address_qu + "\n");
		System.out.println("address_xiangxi:" + this.address_xiangxi + "\n");
		if (accessDB(this.userName,this.phone_number,this.address_shi,this.address_qu,this.address_xiangxi)) {

			message = "success";
			return SUCCESS;
		} else {

			message = "failure";
			return SUCCESS;
		}

	}

	public boolean accessDB(String name,String phone,String city,String district,String addr) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null,stmt1=null;

		String query = "select customer_id from customer where customer_name='"
				+ name+"'";
		System.out.println("userName:" + name + "\n");
		String customer_id = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {

				customer_id = rs.getString("customer_id");
				String sql="insert into address(customer_id,phone_number,address_shi,address_qu,address_xiangxi)"
						+ " values('"+customer_id+"','"+phone+"','"+city+"','"+district+"','"+addr+"')";
				stmt1 = conn.createStatement();
				int res=stmt1.executeUpdate(sql);
				if(res==0){
					info="添加地址出错";
					return false;
				}
			    info="no error";
				return true;
			} else {
				info="用户不存在";
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			info="数据库操作异常";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info="数据库操作异常";
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
				if (stmt1 != null) {
					stmt1.close();
					stmt1 = null;
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
