package com.action.order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import com.action.lzq.DatabaseConn;
public class CommitOrder extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	private String order_id;

	

	
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

	
	public void commitOrder(){
		try{
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			
			
			 Connection conn=DatabaseConn.getConnection();
			 Statement stmt = conn.createStatement(); 
			 
			 
		
			 String orderCon="";
			 String  str="select order_condition from dingdan where order_id='"+order_id+"'";
			 ResultSet rs=stmt.executeQuery(str);
			 if(rs.next()){
				 orderCon=rs.getString(1);
			 }else{
				 System.out.println("不存在该订单，出错！！！");
			 }
			 
			 if(orderCon.equals("未完成")){
				 String str2="update dingdan set order_condition='已完成' where order_id='"+order_id+"'";
				 int count=stmt.executeUpdate(str2);
				 if(count==1){
					 JSONObject Fjson = new JSONObject();
					 Fjson.put("message", "success");
					 Fjson.put("commitOrder", "确认订单成功");
					 System.out.println(Fjson.toString());
				 
				 
					 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
					 response.setContentLength(jsonBytes.length);
					 response.getOutputStream().write(jsonBytes);
					 response.getOutputStream().flush();
					 response.getOutputStream().close();
				 }else{
					 JSONObject Fjson = new JSONObject();
					 Fjson.put("message", "failed");
					 Fjson.put("commitOrder", "确认订单失败");
					 System.out.println(Fjson.toString());
				 
				 
					 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
					 response.setContentLength(jsonBytes.length);
					 response.getOutputStream().write(jsonBytes);
					 response.getOutputStream().flush();
					 response.getOutputStream().close();
			 }
			 }else if(orderCon.equals("已完成")){
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "failed");
				 Fjson.put("commitOrder", "原始订单状态是已完成完成，不应该这样，应该是未完成");
				 System.out.println(Fjson.toString());
			 
			 
				 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
				 response.setContentLength(jsonBytes.length);
				 response.getOutputStream().write(jsonBytes);
				 response.getOutputStream().flush();
				 response.getOutputStream().close();
			 }else{
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "failed");
				 Fjson.put("commitOrder", "没有这张订单");
				 System.out.println(Fjson.toString());
			 
			 
				 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
				 response.setContentLength(jsonBytes.length);
				 response.getOutputStream().write(jsonBytes);
				 response.getOutputStream().flush();
				 response.getOutputStream().close();
			 }
		}catch(SQLException e){
			e.printStackTrace();
		}catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
