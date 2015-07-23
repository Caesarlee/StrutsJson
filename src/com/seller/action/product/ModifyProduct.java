package com.seller.action.product;

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

public class ModifyProduct extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	// 商家信息参数
	private String shop_id = null;
	private String product_id = null;
	private String product_name = null;
	private String product_sales = null;
	private String product_price = null;
	private String product_kind = null;
	private String product_kucun = null;

	private File upload = null;
	private String uploadFileName = null;
	private String uploadContentType = null;

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_sales() {
		return product_sales;
	}

	public void setProduct_sales(String product_sales) {
		this.product_sales = product_sales;
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

	public String modify() {
		System.out.println("shop_id:" + this.shop_id);
		System.out.println("product:" + this.product_id);
		System.out.println("product_name:" + this.product_name);
		System.out.println("product_sales:" + this.product_sales);
		System.out.println("product_price" + this.product_price);
		System.out.println("product_kind:" + this.product_kind);
		System.out.println("product_kucun:" + this.product_kucun);

		System.out.println("upload:" + upload);
		System.out.println("uploadFileName:" + this.uploadFileName);
		System.out.println("uploadFileContentType:" + this.uploadContentType);

		if (accessDB(this.shop_id, this.product_id, this.product_price,
				this.product_kucun, this.uploadContentType)) {
			// 设置storeId属性,从而能够实现商家添加商品
			//注释下面这一行,因为已在session中进行了设置
			//request.setAttribute("storeId", this.shop_id);
			return "success";
		} else {
			// 设置storeId属性,从而能够实现商家添加商品
			//request.setAttribute("storeId", this.shop_id);

			return "failure";
		}

	}

	public boolean accessDB(String shopId, String productId,
			String productPrice, String productKuCun, String picture) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		Statement stmt = null, stmt1 = null, stmt2 = null;
		String append = null;
		// 判断图片的类型
		if ("image/png".equals(picture)) {
			append = shopId + productId + ".png";
		} else if ("image/jpeg".equals(picture)) {
			append = shopId + productId + ".jpg";
		} else if ("image/pjpeg".equals(picture)) {
			append = shopId + productId + ".jpg";
		} else if ("image/x-png".equals(picture)) {
			append = shopId + productId + ".PNG";
		} else {
			append = null;
		}
		// 判断图片的类型
	/*	if ("image/png".equals(picture)) {
			append = shopId + productId + ".png";
		} else if ("image/jpeg".equals(picture)) {
			append = shopId + productId + ".jpg";
		} else if ("image/pjpeg".equals(picture)) {
			append = shopId + productId + ".jpg";
		}*/

		String query1 = "update product set product_price=" + productPrice
				+ " where shop_id=" + shopId + " and product_id=" + productId;
		String query2 = "update product set product_kucun=" + productKuCun
				+ " where shop_id=" + shopId + " and product_id=" + productId;
		String query3 = null;// 更新商品数据库中的图片位置字段
		if (upload != null) {
			query3 = "update product set product_picture=\""
					+ "/image/product/" + append + "\" where shop_id=" + shopId
					+ " and product_id=" + productId;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			int res = stmt.executeUpdate(query1);
			int res1 = stmt1.executeUpdate(query2);
			int res2 = 0;
			if (upload != null) {
				res2 = stmt2.executeUpdate(query3);
			}
			if (res == 1 && res1 == 1) {
				if (res2 == 1) {
					if (upload != null) {
						String dataDir = ServletActionContext
								.getServletContext().getRealPath(
										"image/product"); // "/StrutsJson1/WebContent/js/";

						File saveFilen = new File(dataDir, append);
						upload.renameTo(saveFilen);

					}
				}

				request.setAttribute("result", "修改商品信息成功");
			//	request.setAttribute("shop_id", this.shop_id);
				System.out.println("进入ModifyProduct,shop_id:"
						+ request.getParameter("shop_id"));
				return true;
			} else {
				request.setAttribute("result", "修改商品信息失败");
				// 保证从修改商品信息页面能成功返回到商家主页面
				//request.setAttribute("shop_id", this.shop_id);
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
		///request.setAttribute("shop_id", this.shop_id);
		return false;
	}

}
