<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.database.action.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<title>评价表</title>
</head>
<body>

<div class="container" style="width:80%;">
		<div class="row">
			<div class="col-sm-9">
				<h1 class="text-primary">评价表 comment</h1>
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
					    <th>comment_id</th>
						<th>customer_id</th>
						<th>shop_id</th>
						<th>order_id</th>
						<th>comment_star</th>
						<th>comment_content</th>
						<th>comment_date</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");

						if (comments.size()!=0) {
			
							int j=0;
							for(;j<comments.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								            comments.get(j).getComment_id()+
								            "</td>"+					
											"<td>"
											+ comments.get(j).getCustomer_id()
											+ "</td>"
											+ "<td>"
											+ comments.get(j).getShop_id()
											+ "</td>"
											+ "<td>"
											+ comments.get(j).getOrder_id()
											+ "</td>"
											+ "<td>"
											+ comments.get(j).getComment_star()											
											+ "</td>"		
											+ "<td>"
											+ comments.get(j).getComment_content()										
											+ "</td>"	
											+ "<td>"
						     				+ comments.get(j).getComment_date()									
											+ "</td>"
											+ "</tr>");
								
							}
						}else {
							out.println("<p>无comment记录</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		


	</div>
</body>
</html>