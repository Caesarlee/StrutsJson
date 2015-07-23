<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.DingDanDetails"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>订单详情表</title>
</head>
<body>

<div class="container" style="width:80%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">订单详情表 dingdan_details</h1>
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
					    <th>order_details_id</th>
						<th>order_id</th>
						<th>order_product_id</th>
					    <th>order_product_name</th>
					    <th>order_product_count</th>
				        <th>order_product_price</th>
					
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<DingDanDetails> details = (ArrayList<DingDanDetails>) request.getAttribute("details");

						if (details.size()!=0) {
			
							int j=0;
							for(;j<details.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								           details.get(j).getOrder_details_id()+
								            "</td>"+					
											"<td>"
											+ details.get(j).getOrder_id()
											+ "</td>"
											+ "<td>"
											+ details.get(j).getOrder_product_id()
											+ "</td>"
											+ "<td>"
										    + details.get(j).getOrder_product_name()
											+ "</td>"
											+ "<td>"
										    + details.get(j).getOrder_product_count()
											+ "</td>"
											+ "<td>"
										    + details.get(j).getOrder_product_price()
										    + "</td>"
							
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无dingdan_details记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>