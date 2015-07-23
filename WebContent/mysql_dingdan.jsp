<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.DingDan"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>订单表</title>
</head>
<body>

<div class="container" style="width:90%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">订单表 dingdan</h1>
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
					    <th>order_id</th>
						<th>order_address</th>
						<th>order_pay_way</th>
					    <th>order_peisong</th>
					    <th>order_total</th>
				        <th>order_condition</th>
				        <th>order_remarks</th>
			            <th>shop_id</th>	
						<th>customer_id</th>
						<th>order_date</th>		
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<DingDan> orders = (ArrayList<DingDan>) request.getAttribute("orders");

						if (orders.size()!=0) {
			
							int j=0;
							for(;j<orders.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								           orders.get(j).getOrder_id()+
								            "</td>"+					
											"<td>"
											+ orders.get(j).getOrder_address()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_pay_way()
											+ "</td>"
											+ "<td>"
										    + orders.get(j).getOrder_peisong()
											+ "</td>"
											+ "<td>"
										    + orders.get(j).getOrder_total()
											+ "</td>"
											+ "<td>"
										    + orders.get(j).getOrder_condition()
										    + "</td>"
										    + "<td>"
										    + orders.get(j).getOrder_remarks()
										    + "</td>"
										    + "<td>"
										    + orders.get(j).getShop_id()
										    + "</td>"		    
										    + "<td>"
										    + orders.get(j).getCustomer_id()
										    + "</td>"
										    + "<td>"
										    + orders.get(j).getOrder_date()
										    + "</td>"
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无dingdan记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>