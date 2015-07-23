package com.action.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class AddressAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String userName;
	private String message=null;
	private String info=null;
	private List<Address> addressList = new ArrayList<Address>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
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

		if (accessDB(this.userName)) {

			message = "success";
			return SUCCESS;
		} else {

			message = "failure";
			return SUCCESS;
		}

	}

	public boolean accessDB(String name) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null,rs1=null;
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
				String sql="select * from address where customer_id='"+customer_id+"'";
				stmt1 = conn.createStatement();
				rs1 = stmt1.executeQuery(sql);
				if(!rs1.next()){
					info="用户无收货地址";
					return false;
				}
				rs1.previous();
				Address addr;
				while(rs1.next()){
					addr=new Address();
					addr.setPhone(rs1.getString("phone_number"));
					addr.setAddress_shi(rs1.getString("address_shi"));
					addr.setAddress_qu(rs1.getString("address_qu"));
					addr.setAddress_xiangxi(rs1.getString("address_xiangxi"));
					addressList.add(addr);
				}
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
				if (rs1 != null) {
					rs1.close();
					rs1 = null;
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
