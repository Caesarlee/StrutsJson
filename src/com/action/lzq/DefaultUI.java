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





public class DefaultUI  extends ActionSupport implements ServletRequestAware,
ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	private String shop_name;
	private String customer_name;
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
	
	

	
	
	public void defUI(){
		try{
			 this.response.setContentType("text/json;charset=utf-8");
			 this.response.setCharacterEncoding("UTF-8");
			 
			 Connection conn=DatabaseConn.getConnection();
			 Statement stmt = conn.createStatement(); 
			 
			 //shopName="活动就撒谎";
			 
			 String str0="select shop_id from shop where shop_name='"+shop_name+"'";
			 ResultSet rs0=stmt.executeQuery(str0);
			 
			 String sh_id="";
			 if(rs0.next()){
				  sh_id=rs0.getString(1);
			 }else{
				  sh_id="";
			 }
			 
			 System.out.println("sh_id:"+sh_id);
			 
			 //传送给客户端商店里面有多少种种类的sql
			 String str00="select distinct product_kind from product where shop_id='"+sh_id+"'";
			 ResultSet rs00=stmt.executeQuery(str00);
			 
			 List<Kind> list00=new ArrayList<Kind>();
			 int countTemp=0;
			 while(rs00.next()){
				 Kind k=new Kind();
				 k.setKind(rs00.getString(1));
				 countTemp++;
				 list00.add(k);
			 }
			 JSONObject jsonKind=new JSONObject();
			 if(list00.size()>0){
				 JSONArray jsonArrKind = JSONArray.fromObject(list00);
				 
				 jsonKind.put("shopName", shop_name);
				 jsonKind.put("KindCount", countTemp);
				 jsonKind.put("KindDetails", jsonArrKind);
			 }else{
				 
				
				 jsonKind.put("shopName", shop_name);
				 jsonKind.put("KindCount", countTemp);
				 jsonKind.put("KindDetails", "商家没有商品");
			 }
			 
			 
			 
			 
			 String str1="select product_name,product_price,product_picture,product_sales,product_kind,product_kucun from product where shop_id='"+sh_id+"'";
			 
			 
			 String str11="select customer_id from customer where customer_name='"+customer_name+"'";
			 ResultSet rs2=stmt.executeQuery(str11);
			 String customer_id="";
			 if(rs2.next()){
				 customer_id=rs2.getString(1);
			 }else{
				 System.out.println("矛盾，用户名不存在矛盾，，不应该发生");
			 }
			 
			 System.out.println("customer_id："+customer_id);
			 
			 String str12="select shop_id from customer_collection where customer_id='"+customer_id+"'";
			 ResultSet rs3=stmt.executeQuery(str12);
			 String collection="false";
			 while(rs3.next()){
				 System.out.println("收藏的shop_id："+rs3.getString(1));
				 if(rs3.getString(1).equals(sh_id)){
					 collection="true";
					 break;
				 }
			 }
			 
			 System.out.println("collection："+collection);
			 ResultSet rs1=stmt.executeQuery(str1);
			 List<product> list=new ArrayList<product>();
			 while(rs1.next()){
				 product p=new product();
				 p.setName(rs1.getString(1));
				 p.setPrice(rs1.getString(2));
			//	 InputStream in=this.getInputSteam(rs1.getString(3));
				 p.setPicture(rs1.getString(3));
				 p.setSales(rs1.getString(4));
				 p.setKind(rs1.getString(5));
				 p.setKucun(rs1.getString(6));
				 list.add(p);
			 }
			
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getName());
				System.out.println(list.get(i).getPrice());
				System.out.println(list.get(i).getPicture());
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
				 Fjson.put("collection", collection);
				 Fjson.put("shopKind", jsonKind);
				 Fjson.put("shop_product_details", jsonArr);
				 
				 System.out.println(Fjson.toString());
			 
			 
				 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
				 response.setContentLength(jsonBytes.length);
				 response.getOutputStream().write(jsonBytes);
				 response.getOutputStream().flush();
				 response.getOutputStream().close();
			 }else{
				 product p=new product();
				 p.setName(null);
				 p.setPrice(null);
			//	 InputStream in=this.getInputSteam(rs1.getString(3));
				 p.setPicture(null);
				 p.setSales(null);
				 p.setKind(null);
				 
				 JSONObject failed =JSONObject.fromObject(p);
				 
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "failed");
				 Fjson.put("shopKind", jsonKind);
				 Fjson.put("shop_product_details",failed);
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
