package com.action.lzq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//显示商家评论
public class ShopComment extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String shop_name;
	
	
	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
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
	
	
	public void comment(){
		try{
			 this.response.setContentType("text/json;charset=utf-8");
			 this.response.setCharacterEncoding("UTF-8");
			 
			 Connection conn=DatabaseConn.getConnection();
			 Statement stmt = conn.createStatement(); 
			 Statement stmt1 = conn.createStatement(); 
			 
			// shop_name="速度外卖";
			 
			 String str="select shop_id from shop where shop_name='"+shop_name+"'";
			 ResultSet rs=stmt.executeQuery(str);
			 
			 String shop_id="";
			 if(rs.next()){
				 System.out.println(rs.getString(1));
				 shop_id=rs.getString(1);
			 }
			 String str0="select customer_id,comment_star,comment_date,comment_content from comment where shop_id='"+shop_id+"'";
			 ResultSet rs0=stmt.executeQuery(str0);
			 
			 List<CommentDetail> list=new ArrayList<CommentDetail>();
			 System.out.println("!!!!!!!!!!");
			 while(rs0.next()){
				 System.out.println("!!!!!!!!!!");
				 CommentDetail c=new CommentDetail();
				 c.setStar(rs0.getString(2));
				 c.setDate(rs0.getString(3));
				 c.setContent(rs0.getString(4));
				 
				 String temp=rs0.getString(1);
				 String str1="select customer_name from customer where customer_id='"+temp+"'";
			
				 ResultSet rs1=stmt1.executeQuery(str1);
				 rs1.next();
				 c.setUserName(rs1.getString(1));
				 
				 list.add(c);
			 }
			 
			 
			 
			 for(int i=0;i<list.size();i++){
					System.out.println(list.get(i).getUserName());
					System.out.println(list.get(i).getStar());
					System.out.println(list.get(i).getDate());
					System.out.println(list.get(i).getContent());
					System.out.println();
					
					
					// String json = JSONConvert.SerializeObject();
					//  JSONObject json=new JSONObject();
				    //    json.accumulate("success", true);
				    //    json.accumulate("product", list.get(i));
				    //    System.out.println(json.toString());
					JSONObject json = JSONObject.fromObject(list.get(i));
					System.out.println(json);
				} 
				 if(list.size()>0){
					 JSONArray jsonArr = JSONArray.fromObject(list);
					 System.out.println(jsonArr.toString());
				
					 JSONObject Fjson = new JSONObject();
					Fjson.put("message", "success");
					Fjson.put("shop_comment", jsonArr);
					System.out.println(Fjson.toString());
				 
				 
					byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
					response.setContentLength(jsonBytes.length);
					response.getOutputStream().write(jsonBytes);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				 }else{
					 JSONObject Fjson = new JSONObject();
					 Fjson.put("message", "failed");
					 Fjson.put("shop_comment", "输入的商店不存在");
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
