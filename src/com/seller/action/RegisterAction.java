package com.seller.action;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	//商家信息参数
	private String shop_account=null;
	private String shop_password=null;
	private String shop_name=null;
	private String qisongjia=null;
	private String peisong=null;
	private String open_time=null;
	private String shop_address=null;
	private String wuliu=null;
	private String shop_phone_number=null;
	private File upload=null;
	private String uploadFileName=null;
	private String uploadContentType=null;
	private String shop_kind=null;
	private String shop_remarks=null;
	private String shop_permit="0";
	private String shop_jingdu="0";
	private String shop_weidu="0";
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

	public String getShop_jingdu() {
		return shop_jingdu;
	}

	public void setShop_jingdu(String shop_jingdu) {
		this.shop_jingdu = shop_jingdu;
	}

	public String getShop_weidu() {
		return shop_weidu;
	}

	public void setShop_weidu(String shop_weidu) {
		this.shop_weidu = shop_weidu;
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

	public String register() {
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
		System.out.println("shop_jingdu:"+this.shop_jingdu);
		System.out.println("shop_weidu:"+this.shop_weidu);
		
		if(isExist(this.shop_account)){
			return "failure";
		}
		
		if(accessDB(this.shop_account,this.shop_password,this.shop_name,this.qisongjia,this.peisong,this.open_time,this.shop_address,this.wuliu,this.shop_phone_number,this.uploadContentType,this.shop_kind,this.shop_remarks)){
			return "success";
		}else{
			return "failure";
		}
		
	}

	public boolean isExist(String account){

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select * from shop where shop_account='" + account+ "'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				request.setAttribute("reason", "商家账号已存在!");
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			
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

		return false;
	
	}
	public boolean accessDB(String account,String passwd,String name,String qisong,String peisong,String openTime,String addr,String wuliu,String phone,String picture,String kind,String remark) {

	
		if(this.shop_jingdu.equals("")){
			this.shop_jingdu="0";
		}
		if(this.shop_weidu.equals("")){
			this.shop_weidu="0";
		}
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
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
			append=account+".PNG";
		}else{
			append=null;
		}
		
		
		
		
		String query = "insert into shop(shop_account,shop_password,shop_name,qisongjia,peisong,open_time,shop_address,wuliu,"
				+ "shop_phone_number,shop_picture,shop_kind,shop_remarks,shop_permit,shop_jingdu,shop_weidu,opening) values('"+account+"','"+passwd+"','"+name+"','"+qisong+"'"
						+ ",'"+peisong+"','"+openTime+"','"+addr+"','"+wuliu+"','"+phone+"','/image/"+append+"','"+kind+"'"
								+ ",'"+remark+"','"+this.shop_permit+"','"+this.shop_jingdu+"','"+this.shop_weidu+"','"+"N')";
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			int res=stmt.executeUpdate(query);
			if (res==1) {
				
				if(upload!=null){
					String dataDir=ServletActionContext.getServletContext().getRealPath("image");                              //"/StrutsJson1/WebContent/js/";
					
					File saveFilen=new File(dataDir,append);
					upload.renameTo(saveFilen);
				}
				
				
				
				request.setAttribute("reason", "注册成功,等待审核...");
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("reason", "数据库操作异常");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

			try {
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
		return false;
	}

}
