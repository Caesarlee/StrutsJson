package com.action.order;

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

public class OrderAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware {
	private static final long serialVersionUID = 1L;

	private String  message;
	private List<Order> ordersList=new ArrayList<Order>();

	HttpServletRequest request;
	HttpServletResponse response;
	private String customer_name=null;
	
	private String orderCondition=null;
	@JSON(serialize=false)
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}




	@JSON(serialize=false)
	public String getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public List<Order> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Order> ordersList) {
		this.ordersList = ordersList;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
	}

	public String execute() throws Exception{
		
		System.out.println(this.orderCondition);
		System.out.println(this.customer_name);
		if(accessDB(this.orderCondition)){
			message="success";
			return SUCCESS;
		}
		message="failure";
		return SUCCESS;
		
	}	
	public boolean accessDB(String type) {

		String customer_id=null;
		customer_id=fetchId(this.customer_name);
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null,rs1=null,rs2=null;
		Statement stmt = null,stmt1=null,stmt2=null;

		String query=null;
		String sqlStr=null;
		String sqlQuery=null;//查询是否已经评价
	    query="select * from dingdan where order_condition='"+type+"' and customer_id='"+customer_id+"'";
	
	

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2=conn.createStatement();
			rs = stmt.executeQuery(query);
			Order order;	
			String order_id;
			String shop_id;
			String shop_name=null,shop_picture=null;
			
			ordersList.clear();
			while(rs.next()){
				order=new Order();
				order_id=rs.getString("order_id");
				order.setOrder_id(order_id);
				order.setOrder_time(rs.getString("order_date"));
				order.setOrder_total(rs.getString("order_total"));			
				//查询数据库找出order_id对应的商店的name,picture
				shop_id=rs.getString("shop_id");
				sqlStr="select shop_name,shop_picture from shop where shop_id='"+shop_id+"'";
				rs1=stmt1.executeQuery(sqlStr);
				if(rs1.next()){
					shop_name=rs1.getString("shop_name");
					shop_picture=rs1.getString("shop_picture");
				}
				//查询完毕
				order.setShop_name(shop_name);
				order.setShop_picture(shop_picture);
				if("未完成".equals(type)){
					order.setIsComment("false");
				}else if("已完成".equals(type)){
					sqlQuery="select * from comment where order_id='"+order_id+"'";
					rs2=stmt2.executeQuery(sqlQuery);
					if(rs2.next()){
						order.setIsComment("true");
					}else{
						order.setIsComment("false");
					}
					
				}
				ordersList.add(order);
			}
		

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
				if (rs1 != null) {
					rs1.close();
					rs1 = null;
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

	private String fetchId(String name) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
        String id=null;
   
		String query = "select * from customer where customer_name='" + name
				+ "'";
		//System.out.println("userName:" + name + "\n" + "password:" + passwd);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				
				id=rs.getString("customer_id");
				return id;
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

		

		return id;
	}



}
