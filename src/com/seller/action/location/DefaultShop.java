package com.seller.action.location;

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

import com.action.lzq.DatabaseConn;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class DefaultShop extends ActionSupport implements ServletRequestAware,
ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	
	private double lat_1;
	//private double lat_2;
	private double lung_1;
	private double key;
	public double getLat_1() {
		return lat_1;
	}

	public void setLat_1(double lat_1) {
		this.lat_1 = lat_1;
	}

	public double getLung_1() {
		return lung_1;
	}

	public void setLung_1(double lung_1) {
		this.lung_1 = lung_1;
	}

	public double getKey() {
		return key;
	}

	public void setKey(double key) {
		this.key = key;
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
	
	private final double EARTH_RADIUS = 6378137.0;    
	private double gps(double lat_a, double lng_a, double lat_b, double lng_b) {  
	       double radLat1 = (lat_a * Math.PI / 180.0);  
	       double radLat2 = (lat_b * Math.PI / 180.0);  
	       double a = radLat1 - radLat2;  
	       double b = (lng_a - lng_b) * Math.PI / 180.0;  
	       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
	              + Math.cos(radLat1) * Math.cos(radLat2)  
	              * Math.pow(Math.sin(b / 2), 2)));  
	       s = s * EARTH_RADIUS;  
	       s = Math.round(s * 10000) / 10000;  
	       return s;  
	    }
	
	
	public void defshop(){
		try{
			this.response.setContentType("text/json;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			 
			key=3121951;
			lat_1=56.87;
			lung_1=67.22;
			
			System.out.println("测试是不是defshop action");
			Connection conn=DatabaseConn.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			String str00="select shop_jingdu,shop_weidu,shop_name,peisong,qisongjia,shop_picture from shop";
			ResultSet rs00=stmt.executeQuery(str00);
		//	double[]list = null;
			int countTemp=0;
			while(rs00.next()){
			//	double temp=gps(lat_1,lung_1,rs00.getDouble(2),rs00.getDouble(1));
			//	list[countTemp]=temp;
				countTemp++;
			}
			
			
			
			double[]list = new double[countTemp];
			rs00.first();
			
			int count=0;
			do{
				double temp=gps(lat_1,lung_1,rs00.getDouble(2),rs00.getDouble(1));
				list[count]=temp;
				count++;
			}while(rs00.next());
			
			
			for(int i=0;i<list.length;i++){
				System.out.println(list[i]);
			}
			
			
			
			rs00.first();
		//	while(rs00.next()){
		//		System.out.println(rs00.getFloat(1)+"  "+rs00.getFloat(2)+"  "+rs00.getFloat(3)+"  "+rs00.getFloat(4)+"  "+rs00.getFloat(5));
		//	}
			
			
			
			List<ShopINF> ShopList=new ArrayList<ShopINF>();
			for(int i=0;i<list.length;i++){
				ShopINF shopinf=new ShopINF();
				if(list[i]<key){
					System.out.println("第几行满足要求的距离内："+i);
					int hangshu=i;
					while(hangshu>0){
						rs00.next();
						hangshu--;
					}
					shopinf.setShop_name(rs00.getString(3));
					shopinf.setQisongjia(rs00.getString(5));
					shopinf.setPeisong(rs00.getString(4));
					shopinf.setShop_picture(rs00.getString(6));
					ShopList.add(shopinf);
					rs00.first();
				}
			}		
			
			 JSONObject Fjson = new JSONObject();
			 Fjson.put("message", "sucess");
			 Fjson.put("shop",ShopList);
			 System.out.println(Fjson.toString());
	 
	 
			 byte[] jsonBytes = Fjson.toString().getBytes("utf-8");
			 response.setContentLength(jsonBytes.length);
			 response.getOutputStream().write(jsonBytes);
			 response.getOutputStream().flush();
			 response.getOutputStream().close();
			
		//	String str="select shop_name,qisongjia,peisong,shop_picuture from shop where key >"+gps(lat_1,lung_1,);
			
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
