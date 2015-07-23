<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.Shop"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>商家表</title>
</head>
<body>

<div class="container" style="width:100%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">商家表 shop</h1>
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
					    <th>shop_id</th>
						<th>shop_account</th>
						<th>shop_password</th>
					    <th>shop_name</th>
					    <th>qisongjia</th>
				        <th>peisong</th>
				        <th>open_time</th>
			            <th>shop_address</th>	
						<th>wuliu</th>
						<th>shop_phone_number</th>	
						<th>shop_picture</th>
						<th>shop_kind</th>
						<th>shop_remarks</th>
						<th>shop_permit</th>
						<th>shop_jingdu</th>
						<th>shop_weidu</th>
						<th>confirm</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<Shop> shops = (ArrayList<Shop>) request.getAttribute("shops");

						if (shops.size()!=0) {
			
							int j=0;
							for(;j<shops.size();j++){
								
								if(shops.get(j).getShop_permit().equals("0")){
									out.println("<tr>" +"<td>"+
									           shops.get(j).getShop_id()+
									            "</td>"+					
												"<td>"
												+ shops.get(j).getShop_account()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_password()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getShop_name()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getQisongjia()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getPeisong()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getOpen_time()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getShop_address()
											    + "</td>"		    
											    + "<td>"
											    +shops.get(j).getWuliu()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getShop_phone_number()
											    + "</td>"
											    + "<td>"
												+ shops.get(j).getShop_picture()
											    + "</td>"
											    + "<td>"
												+ shops.get(j).getShop_kind()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_remarks()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_permit()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_jingdu()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_weidu()
												+ "</td>"
											    + "<td><a href=\"database/confirm?shop_id="+shops.get(j).getShop_id()+"\">批准</a>"
												+ "</td>"
												+ "</tr>");
								}else{
									out.println("<tr>" +"<td>"+
									           shops.get(j).getShop_id()+
									            "</td>"+					
												"<td>"
												+ shops.get(j).getShop_account()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_password()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getShop_name()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getQisongjia()
												+ "</td>"
												+ "<td>"
											    + shops.get(j).getPeisong()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getOpen_time()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getShop_address()
											    + "</td>"		    
											    + "<td>"
											    +shops.get(j).getWuliu()
											    + "</td>"
											    + "<td>"
											    + shops.get(j).getShop_phone_number()
											    + "</td>"
											    + "<td>"
												+ shops.get(j).getShop_picture()
											    + "</td>"
											    + "<td>"
												+ shops.get(j).getShop_kind()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_remarks()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_permit()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_jingdu()
												+ "</td>"
												+ "<td>"
												+ shops.get(j).getShop_weidu()
												+ "</td>"
												+"<td>"
											    + "已通过审核"
												+ "</td>"
												+ "</tr>");
								}
									
								
							}
						}else {
							out.println("<p>无shop记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>