<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.Customer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>用户表</title>
</head>
<body>

<div class="container" style="width:80%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">用户表 customer</h1>
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
					    <th>customer_id</th>
						<th>customer_name</th>
						<th>customer_password</th>
						<th>customer_phone_number</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");

						if (customers.size()!=0) {
			
							int j=0;
							for(;j<customers.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								            customers.get(j).getCustomer_id()+
								            "</td>"+					
											"<td>"
											+ customers.get(j).getCustomer_name()
											+ "</td>"
											+ "<td>"
											+ customers.get(j).getCustomer_password()
											+ "</td>"
											+ "<td>"
											+ customers.get(j).getCustomer_phone_number()
											+ "</td>"
											
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>