package com.seller.action.product;

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

public class AddProduct extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	// 商家信息参数
	private String shop_id = null;
	
	private String product_name = null;
	
	private String product_price = null;
	private String product_kind = null;
	private String product_kucun = null;

	private File upload = null;
	private String uploadFileName = null;
	private String uploadContentType = null;

	
	private int product_id=1;
	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}

	public String getProduct_kind() {
		return product_kind;
	}

	public void setProduct_kind(String product_kind) {
		this.product_kind = product_kind;
	}

	public String getProduct_kucun() {
		return product_kucun;
	}

	public void setProduct_kucun(String product_kucun) {
		this.product_kucun = product_kucun;
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

	public String add() {
		generatePId();
		System.out.println("shop_id:" + this.shop_id);
		System.out.println("product_name:" + this.product_name);
		System.out.println("product_price" + this.product_price);
		System.out.println("product_kind:" + this.product_kind);
		System.out.println("product_kucun:" + this.product_kucun);

		System.out.println("upload:" + upload);
		System.out.println("uploadFileName:" + this.uploadFileName);
		System.out.println("uploadFileContentType:" + this.uploadContentType);

		if (accessDB(this.shop_id,this.product_name,this.product_price,
				this.product_kind,this.product_kucun, this.uploadContentType)) {
			return "success";
		} else {
			return "failure";
		}

	
	}

	private void generatePId() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		String query = "select * from product";
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				rs.previous();
				rs.last();
				this.product_id=rs.getInt("product_id")+1;
				System.out.println("Add product_id="+this.product_id);
			
			}else{
				product_id=1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		
	}

	public boolean accessDB(String shopId,String productName,
			String productPrice,String productKind, String productKuCun, String picture) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		Statement stmt = null, stmt2 = null;
		String append = null;
		
		//判断图片的类型
				if("image/png".equals(picture)){
					append=shopId +""+ this.product_id+".png";
				}else if("image/jpeg".equals(picture)){
					append=shopId +""+ this.product_id+".jpg";
				}else if("image/pjpeg".equals(picture)){
					append=shopId +""+ this.product_id+".jpg";
				}else if("image/x-png".equals(picture)){
					append=shopId +""+ this.product_id+".PNG";
				}else{
					append=null;
				}
		// 判断图片的类型
		/*if ("image/png".equals(picture)) {
			append = shopId +""+ this.product_id + ".png";
		} else if ("image/jpeg".equals(picture)) {
			append = shopId +""+ this.product_id + ".jpg";
		} else if ("image/pjpeg".equals(picture)) {
			append = shopId +""+this.product_id + ".jpg";
		}*/

		String query1 ="insert into product values('"+this.product_id+"','"+shopId+"','"+productName+"','null'"
				+ ",'0"+"','"+productPrice+"','"+productKind+"','"+productKuCun+"')";
		
		String query3=null;
		if (upload != null) {
			query3 = "update product set product_picture=\""
					+ "/image/product/" + append + "\" where shop_id=" + shopId
					+ " and product_id=" + this.product_id;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			stmt2 = conn.createStatement();
			int res = stmt.executeUpdate(query1);
			
			int res2 = 0;
			if (upload != null) {
				res2 = stmt2.executeUpdate(query3);
			}
			if (res == 1 && res2 == 1) {

				if (upload != null) {
					String dataDir = ServletActionContext.getServletContext()
							.getRealPath("image/product"); // "/StrutsJson1/WebContent/js/";

					File saveFilen = new File(dataDir, append);
					upload.renameTo(saveFilen);

				}

				//request.setAttribute("result", "修改商品信息成功");
				//request.setAttribute("storeId", this.shop_id);
				//request.setAttribute("shop_id", this.shop_id);
				//保证返回按钮可用
				//request.setAttribute("shopID", this.shop_id);
				
				return true;
			} else {
				request.setAttribute("result", "添加商品信息失败");
				// 保证从修改商品信息页面能成功返回到商家主页面
				//request.setAttribute("storeId", this.shop_id);
				//request.setAttribute("shop_id", this.shop_id);
				//保证返回按钮可用
			//	request.setAttribute("shopID", this.shop_id);
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("result", "数据库操作异常");
			//request.setAttribute("shop_id", this.shop_id);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
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
		request.setAttribute("result", "数据库插入操作出错");
		//request.setAttribute("storeId", this.shop_id);
		//request.setAttribute("shop_id", this.shop_id);
		return false;
	}

}
