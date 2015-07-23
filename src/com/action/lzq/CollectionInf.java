package com.action.lzq;

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
//用户收藏商家信息，取消收藏商家或者收藏商家
public class CollectionInf  extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getTo_states() {
		return to_states;
	}

	public void setTo_states(String to_states) {
		this.to_states = to_states;
	}

	HttpServletRequest request;
	HttpServletResponse response;
	
	
	private String customer_name;
	private String shop_name;
	private String to_states;
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
	
	public void changeState(){
		try{
			 this.response.setContentType("text/json;charset=utf-8");
			 this.response.setCharacterEncoding("UTF-8");
			 
			 Connection conn=DatabaseConn.getConnection();
			 Statement stmt = conn.createStatement(); 
			 
			 String str0="select customer_id from customer where customer_name='"+customer_name+"'";
			 String str1="select shop_id from shop where shop_name='"+shop_name+"'";
			 
			 ResultSet rs0=stmt.executeQuery(str0);
			 String customer_id="";
			 String shop_id="";
			 if(rs0.next()){
				 customer_id=rs0.getString(1);
				 System.out.println("customer_id:"+customer_id);
				 ResultSet rs1=stmt.executeQuery(str1);
				 if(rs1.next()){
					 shop_id=rs1.getString(1);
					 System.out.println("shop_id:"+shop_id);
					 String str2="select distinct collection_id from customer_collection where customer_id='"+customer_id+"'and shop_id='"+shop_id+"'";
					 System.out.println("str2:"+str2);
					 
					 ResultSet rs2=stmt.executeQuery(str2);
					 if(rs2.next()){
						 System.out.println("商家已经收藏了这个商店");
						 //此时，用户应该取消收藏该商店，删除相应的信息
						 String str4="delete from customer_collection where customer_id='"+customer_id+"' and shop_id='"+shop_id+"'";
						 int temp2 =stmt.executeUpdate(str4);
						 
						 
						 if(temp2>0){
							 JSONObject Fjson = new JSONObject();
							 Fjson.put("message", "sucess");
							 Fjson.put("details","取消收藏成功");
							 System.out.println(Fjson.toString());
					 
					 
							 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
							 response.setContentLength(jsonBytes.length);
							 response.getOutputStream().write(jsonBytes);
							 response.getOutputStream().flush();
							 response.getOutputStream().close();
						 }else{
							 JSONObject Fjson = new JSONObject();
							 Fjson.put("message", "failed");
							 Fjson.put("details","取消收藏失败");
							 System.out.println(Fjson.toString());
						 
						 
							 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
							 response.setContentLength(jsonBytes.length);
							 response.getOutputStream().write(jsonBytes);
							 response.getOutputStream().flush();
							 response.getOutputStream().close();
						 }
					 }else{
						 System.out.println("还没有收藏该商店");
						 //此时，用户应该收藏该商店，插入相应的信息
						 String str3="insert into customer_collection(customer_id,shop_id) values('"+customer_id+"','"+shop_id+"')";
						 int temp1=stmt.executeUpdate(str3);
						
						 if(temp1==1){
							 JSONObject Fjson = new JSONObject();
							 Fjson.put("message", "sucess");
							 Fjson.put("details","收藏商店成功");
							 System.out.println(Fjson.toString());
					 
					 
							 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
							 response.setContentLength(jsonBytes.length);
							 response.getOutputStream().write(jsonBytes);
							 response.getOutputStream().flush();
							 response.getOutputStream().close();
						 }else{
							 JSONObject Fjson = new JSONObject();
							 Fjson.put("message", "failed");
							 Fjson.put("details","收藏商店失败");
							 System.out.println(Fjson.toString());
						 
						 
							 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
							 response.setContentLength(jsonBytes.length);
							 response.getOutputStream().write(jsonBytes);
							 response.getOutputStream().flush();
							 response.getOutputStream().close();
						 }
					 }
				 }else{
					 System.out.println("传入的商店名有问题");
					 
					 JSONObject Fjson = new JSONObject();
					 Fjson.put("message", "failed");
					 Fjson.put("details","传入的商店名称在数据库中不存在");
					 System.out.println(Fjson.toString());
				 
				 
					 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
					 response.setContentLength(jsonBytes.length);
					 response.getOutputStream().write(jsonBytes);
					 response.getOutputStream().flush();
					 response.getOutputStream().close();
				 }
			 }else{
				 System.out.println("传入的用户名有问题");
				 
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "failed");
				 Fjson.put("details","传入的用户名在数据库中不存在");
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
