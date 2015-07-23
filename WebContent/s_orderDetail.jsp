<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seller.action.order.OrderDetail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店商家订单详情</title>
 <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
</head>
<body>
	<div class="container" style="width: 60%;">
		<div class="row">
			<div class="col-sm-9">
				<h1>订单详情页面</h1>
			</div>
			<div class="col-sm-3">
				<div>&nbsp</div>
				
			</div>
		</div>

		<!-- 显示订单列表 -->
		<div class="table-responsive">
			<table class="table table-bordered">
				<caption>订单详情列表</caption>
				<thead>
					<tr>
					    <th>商品编号</th>
						<th>商品名称</th>
						<th>购买数量</th>
						<th>商品合计</th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<OrderDetail> order = (ArrayList<OrderDetail>) request.getAttribute("orderDetail");

						if (order!= null) {
			
							int j=0;
							for(;j<order.size();j++){
								
								
									out.println("<tr>" +"<td>"+
								            order.get(j).getOrder_product_id()+
								            "</td>"+					
											"<td>"
											+ order.get(j).getOrder_product_name()
											+ "</td>"
											+ "<td>"
											+ order.get(j).getOrder_product_count()
											+ "</td>"
											+ "<td>"
											+ order.get(j).getOrder_product_price()
											+ "</td>"
											
											+ "</tr>");
								
							}
						}else {
							out.println("<p>没有订单详情</p>");
						}
					%>
				</tbody>
			</table>
			
		</div>
		<div class="row">
		  <a href="orderCenter" class="btn btn-primary btn-lg btn-block">返回</a>
		</div>


	</div>
</body>
</html>