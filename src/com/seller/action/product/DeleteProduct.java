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

public class DeleteProduct extends ActionSupport implements
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

	private String product_picture=null;
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

	public String getProduct_picture() {
		return product_picture;
	}

	public void setProduct_picture(String product_picture) {
		this.product_picture = product_picture;
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

	public String delete() {
		System.out.println("shop_id:" + this.shop_id);
		System.out.println("product_id:" + this.product_id);
		System.out.println("product_picture:" + this.product_picture);

		if (accessDB(this.shop_id, this.product_id,this.product_picture)) {
			return "success";
		} else {
			return "failure";
		}

	}

	public boolean accessDB(String shopId, String productId,String path) {

		String url = "jdbc:mysql://localhost:3306/communityec";
		String user = "root";
		String password = "1234";
		Connection conn = null;
		Statement stmt = null;

		String query = "delete from product where shop_id="+shopId+" and product_id="+productId;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		
			int res = stmt.executeUpdate(query);

			if (res==1) {
				String dataDir=ServletActionContext.getServletContext().getRealPath(path); 
				File file=new File(dataDir);
				if(file.exists()){
					file.delete();
				}
				//request.setAttribute("result", "修改商品信息成功");
				//设置shopID属性，调用mainPageAction
				//注释下一行,因为已经在session中设置shop_id
				//request.setAttribute("shopID", this.shop_id);
				
				return true;
			} else {
				//request.setAttribute("result", "修改商品信息失败");
				//添加storeId,确保添加商品链接可用
				//注释下一行,因为已经在session中设置shop_id

//				request.setAttribute("storeId", this.shop_id);
				// 保证从修改商品信息页面能成功返回到商家主页面
				//注释下一行,因为已经在session中设置shop_id

//				request.setAttribute("shopID", this.shop_id);
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	request.setAttribute("result", "数据库操作异常");
	//		request.setAttribute("storeId", this.shop_id);
//			request.setAttribute("shopID", this.shop_id);
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
		
	//	request.setAttribute("shopID", this.shop_id);
		return false;
	}

}
