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

public class ShopDetail extends ActionSupport implements ServletRequestAware,
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
	
	public void shopdetail(){
		try{
			 this.response.setContentType("text/json;charset=utf-8");
			 this.response.setCharacterEncoding("UTF-8");
			 
			 Connection conn=DatabaseConn.getConnection();
			 Statement stmt = conn.createStatement(); 
			 // shop_name="家家乐超市";
			 System.out.println("!!!!!!!!!!!!!"+shop_name);
			 String str="select shop_remarks,wuliu,qisongjia,peisong,open_time,shop_address,shop_phone_number from shop where shop_name='"+shop_name+"'";
			 ResultSet rs=stmt.executeQuery(str);
			
			 
			 List<ShopInformation> list=new ArrayList<ShopInformation>();
			 System.out.println("!!!!!!!!!!");
			 while(rs.next()){
				 System.out.println("!!!!!!!!!!");
				 ShopInformation s=new ShopInformation();
				 s.setRemarks(rs.getString(1));
				 s.setWuliu(rs.getString(2));
				 s.setQisongjia(rs.getString(3));
				 s.setPeisong(rs.getString(4));
				 s.setOpentime(rs.getString(5));
				 s.setShopAddr(rs.getString(6));
				 s.setPhone(rs.getString(7));
				
				
				 
				 list.add(s);
			 }
			 
			 
			 
			if(list.size()>0){
				 
				 JSONArray jsonArr = JSONArray.fromObject(list);
				 System.out.println(jsonArr.toString());
				
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "success");
				 Fjson.put("shop_details", jsonArr);
				 System.out.println(Fjson.toString());
				 
				 
				 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
				 response.setContentLength(jsonBytes.length);
				 response.getOutputStream().write(jsonBytes);
				 response.getOutputStream().flush();
				 response.getOutputStream().close();
			}else{
				
				
				 JSONObject Fjson = new JSONObject();
				 Fjson.put("message", "failed");
				 Fjson.put("shop_details", "输入的商家名不存在");
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
