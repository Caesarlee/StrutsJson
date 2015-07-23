<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>产品表</title>
</head>
<body>

<div class="container" style="width:80%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">产品表 product</h1>
			</div>
			<div class="col-sm-3">
				<div>&nbsp</div>
				
			</div>
		</div>

		<!-- 显示订单列表 -->
		<div class="table-responsive" style="background: ;">
			<table class="table table-bordered table-hover">
				
				<thead>
					<tr>
					    <th>product_id</th>
						<th>shop_id</th>
						<th>product_name</th>
						<th>product_picture</th>
						<th>product_sales</th>
						<th>product_price</th>
						<th>product_kind</th>
						<th>product_kucun</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<Product> products = (ArrayList<Product>) request.getAttribute("products");

						if (products.size()!=0) {
			
							int j=0;
							for(;j<products.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								            products.get(j).getProduct_id()+
								            "</td>"+					
											"<td>"
											+ products.get(j).getShop_id()
											+ "</td>"
											+ "<td>"
											+ products.get(j).getProduct_name()
											+ "</td>"
											+ "<td>"
											+ products.get(j).getProduct_picture()
											+ "</td>"
											+ "<td>"
											+ products.get(j).getProduct_sales()										
											+ "</td>"		
											+ "<td>"
											+ products.get(j).getProduct_price()									
											+ "</td>"	
											+ "<td>"
						     				+ products.get(j).getProduct_kind()								
											+ "</td>"
												
											+ "<td>"
								     		+ products.get(j).getProduct_kucun()								
											+ "</td>"
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无product记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>