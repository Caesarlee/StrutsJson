<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seller.action.order.OrderToShop"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店商家订单中心</title>
 <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
      <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

      
</head>
<body>
	<div class="container" style="width: 80%;">
		<div class="row">
			<div class="col-sm-6">
				<h1>商家管理界面</h1>
			</div>
			<div class="col-sm-4">
				<div>&nbsp</div>
				<%
					if (session.getAttribute("shop_account") != null) {
						out.println("<h4 id=\"shopId\">欢迎"
								+ session.getAttribute("shop_account") + "登录系统</h4>");
					}
				%>
			</div>
				
			
			<div class="col-sm-2">
			  <p>&nbsp</p>
			  <a class="btn btn-primary" href="/StrutsJson/quitSystem.jsp">退出系统</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<ul class="nav nav-pills nav-justified">
                     <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="mainPage.action">商品管理
                   <!--  
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">商品管理
					-->
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a
								href="/StrutsJson/s_addProduct.jsp?shop_id=<%=session.getAttribute("shop_id")%>">添加商品</a></li>
							<!-- 
							<li><a href="#">修改商品</a></li> -->

						</ul></li>
					<li class="active"><a href="#">订单中心</a></li>
					<li><a href="storeInfo">商铺信息</a></li>
					<!--<li><a href="#">商铺信息</a></li>-->
				</ul>
			</div>

		</div>
        <!-- 切割条-->
        <div class="row">
			<div class="col-sm-12 bg-success">
				<h6></h6>
			</div>
		</div>
		<!-- 订单状态与颜色 -->
		<div class="row">
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-6">
						<h4>历史订单</h4>
					</div>
					<div class="col-sm-6">
						<h4>背景白色</h4>
					</div>

				</div>
			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-6">
						<h4>未处理订单</h4>
					</div>
					<div class="col-sm-6">
						<h4>背景淡蓝色</h4>
					</div>

				</div>
			</div>
			<div class="col-sm-2">
			    <!--  
				<a class="btn btn-primary" href="mainPage.action?shop_id=<%=request.getAttribute("storeId")  %>">返回</a>
			    -->
			</div>
		</div>
		<!-- 显示订单列表 -->
		<div class="table-responsive">
			<table class="table table-bordered">
				<caption>订单列表</caption>
				<thead>
					<tr>
					    <th>订单编号</th>
						<th>顾客姓名</th>
						<th>联系方式</th>
						<th>配送地址</th>
						<th>支付方式</th>
						<th>订单总额</th>
						<th>客户备注</th>
						<th>下单时间</th>
						<th><a>商品详情</a></th>
						<th><a>删除订单</a></th>
					</tr>
				</thead>
				<tbody>
					<!-- 使用JSP显示商家订单 -->
					<%
						List<OrderToShop> orders = (ArrayList<OrderToShop>) request.getAttribute("orders");
                        
						if (orders.size() != 0) {
							int i = 0;
							for (; i < orders.size(); i++) {
								if ("未完成".equals(orders.get(i).getOrder_condition())) {
									out.println("<tr class=\"success\">" +"<td>"+
								            orders.get(i).getOrder_id()+
								            "</td>"+
								            "<td>"
											+ orders.get(i).getCustomer_name()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getCustomer_phone_number()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getOrder_address()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getOrder_pay_way()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getOrder_total()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getOrder_remarks()
											+ "</td>"
											+ "<td>"
											+ orders.get(i).getOrder_date()
											+ "</td>"
											+ "<td><a href=\"orderDetail?order_id="+orders.get(i).getOrder_id()+"&shop_id="+orders.get(i).getShop_id()+"\">点击查看</a></td>"
													 + "<td>不可删除</td>"
											+ "</tr>");
								}
								//      
							}
							int j=0;
							for(;j<orders.size();j++){
								
								if ("已完成".equals(orders.get(j).getOrder_condition())) {
									out.println("<tr>" +"<td>"+
								            orders.get(j).getOrder_id()+
								            "</td>"+					
											"<td>"
											+ orders.get(j).getCustomer_name()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getCustomer_phone_number()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_address()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_pay_way()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_total()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_remarks()
											+ "</td>"
											+ "<td>"
											+ orders.get(j).getOrder_date()
											+ "</td>"
											+ "<td><a href=\"orderDetail?order_id="+orders.get(j).getOrder_id()+"&shop_id="+orders.get(j).getShop_id()+"\">点击查看</a></td>"
											+ "<td><a href=\"deleteOrder?order_id="+orders.get(j).getOrder_id()+"&shop_id="+orders.get(j).getShop_id()+"\">点击删除</a></td>"
													+ "</tr>");
								}
							}
						}else {
							out.println("<tr><td>没有任何订单</td></tr>");
						}
					%>
				</tbody>
			</table>
		</div>


	</div>
</body>
</html>