<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.Address"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>地址表</title>
</head>
<body>

<div class="container" style="width:80%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">地址表 address</h1>
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
					    <th>address_id</th>
						<th>customer_id</th>
						<th>phone_number</th>
						<th>address_shi</th>
						<th>address_qu</th>
						<th>address_xiangxi</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<Address> address = (ArrayList<Address>) request.getAttribute("address");

						if (address.size()!=0) {
			
							int j=0;
							for(;j<address.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								            address.get(j).getAddress_id()+
								            "</td>"+					
											"<td>"
											+ address.get(j).getCustomer_id()
											+ "</td>"
											+ "<td>"
											+ address.get(j).getPhone_number()
											+ "</td>"
											+ "<td>"
											+ address.get(j).getAddress_shi()
											+ "</td>"
											+ "<td>"
											+ address.get(j).getAddress_qu()											+ "</td>"
											+ "</td>"		
											+ "<td>"
											+ address.get(j).getAddress_xiangxi()											+ "</td>"
											+ "</td>"	
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无address记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>