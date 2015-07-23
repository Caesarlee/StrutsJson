package com.action.comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String message;

	private String info=null;//评论成功或失败的原因
	private String orderId;
	private String comment_star;
	private String comment_content;
	
	@JSON(serialize = false)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@JSON(serialize = false)
	public String getComment_star() {
		return comment_star;
	}

	public void setComment_star(String comment_star) {
		this.comment_star = comment_star;
	}
	@JSON(serialize = false)
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
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

		 System.out.println("orderId:" + this.orderId + "\n" + "comment_star:" + this.comment_star);
		
		if (accessDB(this.orderId, this.comment_star,this.comment_content)) {
           
			message="success";
			return SUCCESS;
			
		} else {
		     message="failure";
		     return SUCCESS;
		}
	}

	public boolean accessDB(String id, String star,String content) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
        String customer_id=null,shop_id=null;
		String query = "select * from dingdan where order_id='" + id
				+ "'";
		System.out.println("orderId:" + id + "\n" + "comment_star:" + star+"\n"+"comment_content:"+content);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				
				customer_id=rs.getString("customer_id");
				shop_id=rs.getString("shop_id");
				insertComment(id,customer_id,shop_id,star,content);
				return true;
			}else{
				info="订单不存在";
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

	public void insertComment(String id,String customer_id,String shop_id,String star,String content){
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
        //获取系统时间
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String commemt_date=df.format(date);
		String query ="insert into comment(customer_id,shop_id,order_id,comment_star,comment_content"
				+ ",comment_date) values('"+customer_id+"','"+shop_id+"','"+id+"','"+star+"','"+content+"','"+commemt_date
						+ "')";
			
		System.out.println("orderId:" + id + "\n" + "comment_star:" + star+"\n"+"comment_content:"+content);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			int res=stmt.executeUpdate(query);
			if (res==1) {
				
				info="评论成功";
				return;
				
			}else{
				info="评论失败";
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
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}

