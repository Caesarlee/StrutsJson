package com.action.lzq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//提交订单的类

public class DingdanSubmit extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
//	private String customer_phone;
	private String order_address;
	private String peisong;
	private String  order_total;
	private String order_remarks;
	private String shop_name;
	private String customer_name;
	private String date;
	
	private String order_condition;
	
	private String order_pay_way;
	
	
	
	private String json; 
	
//	public String getCustomer_phone() {
//		return customer_phone;
//	}

//	public void setCustomer_phone(String customer_phone) {
//		this.customer_phone = customer_phone;
//	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getPeisong() {
		return peisong;
	}

	public void setPeisong(String peisong) {
		this.peisong = peisong;
	}

	public String getOrder_total() {
		return order_total;
	}

	public void setOrder_total(String order_total) {
		this.order_total = order_total;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getOrder_remarks() {
		return order_remarks;
	}

	public void setOrder_remarks(String order_remarks) {
		this.order_remarks = order_remarks;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getOrder_condition() {
		return order_condition;
	}

	public void setOrder_condition(String order_condition) {
		this.order_condition = order_condition;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrder_pay_way() {
		return order_pay_way;
	}

	public void setOrder_pay_way(String order_pay_way) {
		this.order_pay_way = order_pay_way;
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
	
	
	public void submitDingdan(){
		try{
			
			 this.response.setContentType("text/json;charset=utf-8");
			 this.response.setCharacterEncoding("UTF-8");
			 
			 Connection conn=DatabaseConn.getConnection();
			 conn.setAutoCommit(false); 
			 Statement stmt = conn.createStatement(); 
			 Statement stmt1 = conn.createStatement();
			 
			 System.out.println("json为："+json);
			 order_condition="未完成";
			 order_pay_way="到付";
			 //计算系统时间
			 Date dateTemp=new Date();
			  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  date=format.format(dateTemp);
			  System.out.println(date);
			
			 
			 String str="select shop_id from shop where shop_name='"+shop_name+"'";
			 System.out.println(str);
			 String str1="select customer_id from customer where customer_name='"+customer_name+"'";
			 System.out.println(str1);
			 
			 String shop_id=null;
			 String customer_id=null;
			 
			 //根据shop_name找到shop_id
			 ResultSet rs0=stmt.executeQuery(str);
			 if(rs0.next()){
				 shop_id=rs0.getString(1);
			 }
			 
			 ResultSet rs1=stmt.executeQuery(str1);
			 
			 if(rs1.next()){
				 customer_id=rs1.getString(1);
			 }
			 
			 String str99="insert into dingdan(order_address,order_pay_way,order_peisong,order_total,order_condition,order_remarks,shop_id,customer_id,order_date) values('"+order_address+"','"+order_pay_way+"','"+peisong+"','"+order_total+"','"+order_condition+"','"+order_remarks+"','"+shop_id+"','"+customer_id+"','"+date+"')";
			 System.out.println(str99);
			 int count1= 0;
			 System.out.println("peisong:"+peisong);
			 boolean b=!(peisong==null);
			 System.out.println("b:"+b);
			boolean a= (!(customer_id.equals(""))&&!(shop_id.equals(""))&&!(order_address.equals(""))&&!(peisong.equals(""))&&!(order_total.equals(""))&&!(date.equals("")));
			 System.out.println(a);
			 if(a){
				 count1=stmt.executeUpdate(str99);
				// conn.commit();
			 }
			 
			 //向dingdan_details里面存入数据
			 if(count1==1){
				 String str10="select order_id from dingdan where order_address='"+order_address+"' and order_pay_way='"+order_pay_way+"'and order_peisong='"+peisong+"'and order_total='"+order_total+"'and order_condition='"+order_condition+"'and order_remarks='"+order_remarks+"'and shop_id='"+shop_id+"'and customer_id='"+customer_id+"'and order_date='"+date+"'";
				 System.out.println("str10:"+str10);
				 ResultSet rs2=stmt.executeQuery(str10);
				 String order_id=null;
				if( rs2.next()){
					 //获得订单号
					  order_id=rs2.getString(1);
					
				}else{
					System.out.println("矛盾发生！！！！！！！！！！！！！！！！");
				}
			 
			 
				 //json的解析工作
				 JSONObject Fjson=JSONObject.fromObject(json);
				 System.out.println(Fjson);
				 JSONArray data=Fjson.getJSONArray("products");
				 int temp= data.size();
				 List<CountProduct> list=new ArrayList<CountProduct>();
			 
				 //如果传入的json里面有有效的数据
				 if(temp>0){
				
					 //解析json
					 for(int i=0;i<temp;i++){
						 CountProduct countproduct=new CountProduct();
						 JSONObject info=data.getJSONObject(i);
						 String name=info.getString("product_name");
						 int count=info.getInt("count");
						 countproduct.setName(name);
						 countproduct.setCount(count);
						 list.add(countproduct);
					 	}
					 
					 
						 //向dingdan_detail里面插入数据
					 int countTemp=0;
					 for(int i=0;i<temp;i++){
						 String product_id="";
						 String product_name=list.get(i).getName();
						 int product_count=list.get(i).getCount();
						 float product_price=0;
					 
					 
						 String str20="select product_id,product_price from product where shop_id='"+shop_id+"'and product_name='"+product_name+"'";
						 System.out.println("str20为："+str20);
						 ResultSet rs3=stmt1.executeQuery(str20);
						 if( rs3.next()){
							 product_id=rs3.getString(1);
							 product_price=product_count*rs3.getFloat(2);
						 }
					 
						 System.out.println("product_name:"+product_name);
						 System.out.println("product_id:"+product_id);
						 System.out.println("product_price:"+product_price);
						 System.out.println("product_count:"+product_count);
						 
						 boolean w1=!(product_name.equals(""));
						 System.out.println("w1:"+w1);
						 boolean w2=!(product_id.equals(""));
						 System.out.println("w2:"+w2);
						 boolean w3=(product_price>0);
						 System.out.println("w3:"+w3);
						 boolean w4=(product_count>0);
						 System.out.println("w4:"+product_count);
						 
						 boolean tm=(w1&&w2&&w3&&w4);
						 System.out.println("tm:"+tm);
					 
						 if(tm){
							 String str21="insert into dingdan_details(order_id,order_product_id,order_product_name,order_product_count,order_product_price)values('"+order_id+"','"+product_id+"','"+product_name+"','"+product_count+"','"+product_price+"')";
							 int jishu=stmt1.executeUpdate(str21);
							 System.out.println("str21:"+str21);
							 System.out.println("jishu:"+jishu);
							// conn.commit();
							 countTemp+=jishu;
							 System.out.println("countTemp"+countTemp);
						 }else{
								 //插入订单details表失败，插入订单details表的某一个数据出现问题
							// conn.rollback();
						 }
					}
					 
					 if(countTemp==temp){
						 //插入数据成功
						 
						 conn.commit();
						JSONObject FINALjson = new JSONObject();
						FINALjson.put("message", "success");
						FINALjson.put("Submit", "操作成功");
					//	System.out.println(Fjson.toString());
					 
					 
						byte[] jsonBytes = FINALjson.toString().getBytes("utf-8");
						response.setContentLength(jsonBytes.length);
						response.getOutputStream().write(jsonBytes);
						response.getOutputStream().flush();
						response.getOutputStream().close();
					 }else{
						 //插入的数据中有一些有问题需要进行数据库的回退操作，并且回退订单表中的信息
						 
						 
						 	conn.rollback();
						 
						 
						 
							String str6="插入订单详细表失败，json格式内容为中出现问题；请重新提交json格式数据";
							
						 	JSONObject FINALjson = new JSONObject();
							FINALjson.put("message", "failed");
							FINALjson.put("Submit", str6);
							//System.out.println(Fjson.toString());
						 
						 
							byte[] jsonBytes = FINALjson.toString().getBytes("utf-8");
							response.setContentLength(jsonBytes.length);
							response.getOutputStream().write(jsonBytes);
							response.getOutputStream().flush();
							response.getOutputStream().close();
					 }
					 
					 
					 
					 
				 }else{
					 
					 //回退插入的订单表的数据
					 
					 conn.rollback();
					 
					 
					 
					 String str6="插入订单详细表失败，json格式内容为空；请重新提交json格式数据";
						
					 	JSONObject FINALjson = new JSONObject();
						FINALjson.put("message", "faied");
						FINALjson.put("Submit",str6);
					//	System.out.println(Fjson.toString());
					 
					 
						byte[] jsonBytes = FINALjson.toString().getBytes("utf-8");
						response.setContentLength(jsonBytes.length);
						response.getOutputStream().write(jsonBytes);
						response.getOutputStream().flush();
						response.getOutputStream().close();
					 
				 }
			//	 int count=0;
				
			 
				// System.out.println("count为："+count);
			 
			 } else{
				 String str6="插入订单表失败，请重新提交数据";
					
					JSONObject FINALjson = new JSONObject();
					FINALjson.put("message", "failed");
					FINALjson.put("Submit", str6);
				//	System.out.println(Fjson.toString());
				 
				 
					byte[] jsonBytes = FINALjson.toString().getBytes("utf-8");
					response.setContentLength(jsonBytes.length);
					response.getOutputStream().write(jsonBytes);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				 
			 }
			 
			 
			// String str100="";
			// System.out.println(str100);
			// int count2= stmt.executeUpdate(str100);
			 
			/*if(count1==1&&count>0){
				String str5="success";
				String str6="插入成功";
				
				Map<String,String> json=new HashMap<String,String>();
		    	json.put(str5, str6);
		    	
		    	byte[] jsonBytes = json.toString().getBytes("utf-8");
				response.setContentLength(jsonBytes.length);
				response.getOutputStream().write(jsonBytes);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}else{
				
				
			}*/
			 
			 
			
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
