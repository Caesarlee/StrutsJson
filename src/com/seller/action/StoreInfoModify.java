package com.seller.action;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class StoreInfoModify extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	//商家信息参数
	private String shop_id=null;
	private String shop_account=null;
	private String shop_name=null;
	private String shop_password=null;
	private String shop_address=null;
	private String shop_phone_number=null;
	private String shop_kind=null;
	private String open_time=null;
	private String qisongjia=null;
	private String peisong=null;
	
	
	private String wuliu=null;
	private String shop_remarks=null;
	private File upload=null;
	private String uploadFileName=null;
	private String uploadContentType=null;

	private Connection conn;

	
		
	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_account() {
		return shop_account;
	}

	public void setShop_account(String shop_account) {
		this.shop_account = shop_account;
	}

	public String getShop_password() {
		return shop_password;
	}

	public void setShop_password(String shop_password) {
		this.shop_password = shop_password;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}



	public String getQisongjia() {
		return qisongjia;
	}

	public void setQisongjia(String qisongjia) {
		this.qisongjia = qisongjia;
	}

	public String getPeisong() {
		return peisong;
	}

	public void setPeisong(String peisong) {
		this.peisong = peisong;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getWuliu() {
		return wuliu;
	}

	public void setWuliu(String wuliu) {
		this.wuliu = wuliu;
	}

	public String getShop_phone_number() {
		return shop_phone_number;
	}

	public void setShop_phone_number(String shop_phone_number) {
		this.shop_phone_number = shop_phone_number;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getShop_kind() {
		return shop_kind;
	}

	public void setShop_kind(String shop_kind) {
		this.shop_kind = shop_kind;
	}

	public String getShop_remarks() {
		return shop_remarks;
	}

	public void setShop_remarks(String shop_remarks) {
		this.shop_remarks = shop_remarks;
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

	public String infoModify() {
		System.out.println("shop_id:"+this.shop_id);
		System.out.println("shop_account:"+this.shop_account);
		System.out.println("shop_password:"+this.shop_password);
		System.out.println("shop_name:"+this.shop_name);
		System.out.println("qisongjia:"+this.qisongjia);
		System.out.println("peisong:"+this.peisong);
		System.out.println("open_time:"+this.open_time);
		System.out.println("shop_address:"+this.shop_address);
		System.out.println("wuliu:"+this.wuliu);
		System.out.println("shop_phone_number:"+this.shop_phone_number);
		
		System.out.println("upload:"+upload);
		System.out.println("uploadFileName:"+this.uploadFileName);
		System.out.println("uploadFileContentType:"+this.uploadContentType);
		
		
		System.out.println("shop_kind:"+this.shop_kind);
		System.out.println("shop_remarks:"+this.shop_remarks);
		
		
		
		if(accessDB(this.shop_id,this.shop_account,this.shop_password,this.shop_name,this.qisongjia,this.peisong,this.open_time,this.shop_address,this.wuliu,this.shop_phone_number,this.uploadContentType,this.shop_kind,this.shop_remarks)){
			return "success";
		}else{
			return "failure";
		}
		
	}

		public boolean accessDB(String id,String account,String passwd,String name,String qisong,String peisong,String openTime,String addr,String wuliu,String phone,String picture,String kind,String remark) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		conn = null;
		Statement stmt = null;
		String append=null;

		//判断图片的类型
				if("image/png".equals(picture)){
					append=account+".png";
				}else if("image/jpeg".equals(picture)){
					append=account+".jpg";
				}else if("image/pjpeg".equals(picture)){
					append=account+".jpg";
				}else if("text/plain".equals(picture)){
					append=account+".png";
				}else if("image/x-png".equals(picture)){
					append=account+".png";
				}else{
					append=null;
				}

		//判断图片的类型
				if("image/png".equals(picture)){
					append=account+".png";
				}else if("image/jpeg".equals(picture)){
					append=account+".jpg";
				}else if("image/pjpeg".equals(picture)){              
					append=account+".jpg";
				}else if("text/plain".equals(picture)){
					append=account+".png";
				}else if("image/x-png".equals(picture)){
					append=account+".PNG";
				}else{
					append=null;
				}
		
		String query1="update shop set shop_account='"+account+"' where shop_id='"+id+"'";
		String query2="update shop set shop_password='"+passwd+"' where shop_id='"+id+"'";
		String query3="update shop set shop_name='"+name+"' where shop_id='"+id+"'";
		String query4="update shop set qisongjia='"+qisong+"' where shop_id='"+id+"'";
		String query5="update shop set peisong='"+peisong+"' where shop_id='"+id+"'";
		String query6="update shop set open_time='"+openTime+"' where shop_id='"+id+"'";
		String query7="update shop set shop_address='"+addr+"' where shop_id='"+id+"'";
		String query8="update shop set wuliu='"+wuliu+"' where shop_id='"+id+"'";
		String query9="update shop set shop_phone_number='"+phone+"' where shop_id='"+id+"'";
		String query10="update shop set shop_picture='/image/"+append+"' where shop_id='"+id+"'";
		String query11="update shop set shop_kind='"+kind+"' where shop_id='"+id+"'";
		String query12="update shop set shop_remarks='"+remark+"' where shop_id='"+id+"'";
		
		
	/*	String query = "insert into shop(shop_account,shop_password,shop_name,qisongjia,peisong,open_time,shop_address,wuliu,"
				+ "shop_phone_number,shop_picture,shop_kind,shop_remarks) values('"+account+"','"+passwd+"','"+name+"','"+qisong+"'"
						+ ",'"+peisong+"','"+openTime+"','"+addr+"','"+wuliu+"','"+phone+"','/image/"+append+"','"+kind+"'"
								+ ",'"+remark+"')";*/
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			int res1=stmt.executeUpdate(query1);
			int res2=stmt.executeUpdate(query2);
			int res3=stmt.executeUpdate(query3);
			int res4=stmt.executeUpdate(query4);
			int res5=stmt.executeUpdate(query5);
			int res6=stmt.executeUpdate(query6);
			int res7=stmt.executeUpdate(query7);
			int res8=stmt.executeUpdate(query8);
			int res9=stmt.executeUpdate(query9);
			conn.commit();
			int res10=0;
			if(this.upload!=null){
				res10=stmt.executeUpdate(query10);	
			}
			
			int res11=stmt.executeUpdate(query11);
			int res12=stmt.executeUpdate(query12);
			
			if (res1==1&&res2==1&res3==1&res4==1&&res5==1&res6==1&res7==1&res8==1&res9==1&res11==1&res12==1) {
				
				if(this.upload!=null&&res10==1){
					String dataDir=ServletActionContext.getServletContext().getRealPath("image");                              //"/StrutsJson1/WebContent/js/";
					
					File saveFilen=new File(dataDir,append);
					upload.renameTo(saveFilen);
						
				}
				
				
				request.setAttribute("reason", "修改商家信息成功!!");
				//设置shop_id属性,从而实现对storeInfo Action的调用
				//request.setAttribute("shop_id", this.shop_id);
				//request.setAttribute("storeId", this.shop_id);
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("reason", "数据库操作异常");
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			request.setAttribute("reason", "数据库操作异常");
			e.printStackTrace();
		}finally{

			try {
			conn.setAutoCommit(true);
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
				if(conn!=null){
					conn.close();
					conn=null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		request.setAttribute("reason", "数据库插入操作出错");
		//request.setAttribute("shop_id", this.shop_id);
		//request.setAttribute("storeId", this.shop_id);
		return false;
	}

}
