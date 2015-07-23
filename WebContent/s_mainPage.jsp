<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.seller.action.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>商家主页</title>
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">

<script src="/StrutsJson/js/jquery-1.11.3.min.js"></script>
<script src="/StrutsJson/js/bootstrap.min.js"></script>
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<!--  
<script type="text/javascript" src="/StrutsJson/js/jquery-1.11.3.min.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		
		var bt1 = document.getElementById("button1");
		bt1.addEventListener("click", start, false);//为button1,添加click事件star

		var bt2 = document.getElementById("button2");
		bt2.addEventListener("click", stop, false);
		//bt2.style.background = "#717174";//我设置的不能点击按钮的颜色是灰色

	};


	function createXmlHttpRequest() {
		if (window.ActiveXObject) { //如果是IE浏览器    
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) { //非IE浏览器    
			return new XMLHttpRequest();
		}
	}

	function start() {//发请求并调用change方法
		
		var shopId = encodeURIComponent(document.getElementById("button1").getAttribute("shop_id"));
		var url = "seller/openClose?shop_id="+shopId+"&openOrClose="
				+ encodeURIComponent("open");
		xmlHttpRequest = createXmlHttpRequest();
		xmlHttpRequest.onreadystatechange = change;
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.send(null);

	}

	function stop() {
		var shopId = encodeURIComponent(document.getElementById("button2").getAttribute("shop_id"));
		var url = "seller/openClose?shop_id="+shopId+"&openOrClose="
				+ encodeURIComponent("close");
		xmlHttpRequest = createXmlHttpRequest();
		xmlHttpRequest.onreadystatechange = change2;
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.send(null);

	}

	function change() { //用于更换按钮的样式,去除click事件和添加click事件
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var b = xmlHttpRequest.responseText;

			var oblect2 = eval("(" + b + ")");

			if (oblect2.message == "success") {

				//var bt1 = document.getElementById("button1");
				//bt1.removeEventListener("click", start, false);
				//bt1.style.background = "#717174";

				//var bt2 = document.getElementById("button2");
				//bt2.style.background = "#337ab7";
				//bt2.addEventListener("click", stop, false);
				alert("开始营业");

			}

		}
		;

	};

	function change2() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var b = xmlHttpRequest.responseText;
			var oblect2 = eval("(" + b + ")");

			if (oblect2.message == "success") {
				/*var bt2 = document.getElementById("button2");
				bt2.removeEventListener("click", stop, false);
				bt2.style.background = "#717174";

				var bt1 = document.getElementById("button1");
				bt1.style.background = "#337ab7";
				bt1.addEventListener("click", start, false);*/
				alert("停止营业");
			}
		}
		;

	};
</script>

</head>
<body>



	<div class="container" style="width: 80%;">
		<div class="row">
			<div class="col-sm-5">
				<h1>商家管理界面</h1>
			</div>
			<div class="col-sm-4">
				<div>&nbsp</div>
				<%
					//if (request.getAttribute("shop_account") != null) {
					if (session.getAttribute("shop_account") != null) {
						out.println("<h3 id=\"shopId\">欢迎"
								+ session.getAttribute("shop_account") + "登录系统</h3>");
						//	+ request.getAttribute("shop_account") + "登录系统</h4>");
					}
				%>
			</div>
			<div class="col-sm-1">
				<p>&nbsp</p>
				<button id="button1" type="button" class="btn btn-primary"
					shop_id=<%=session.getAttribute("shop_id")%>>开始营业</button>
			</div>
			<div class="col-sm-1">
				<p>&nbsp</p>
				<button id="button2" type="button" class="btn btn-primary"
					shop_id=<%=session.getAttribute("shop_id")%>>停止营业</button>
			</div>
			<div class="col-sm-1">
				<p>&nbsp</p>
				<a class="btn btn-primary" href="/StrutsJson/quitSystem.jsp">退出系统</a>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<ul class="nav nav-pills nav-justified">

					<li class="dropdown active"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">商品管理 <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a
								href="/StrutsJson/s_addProduct.jsp?shop_id=<%=session.getAttribute("shop_id")%>">添加商品</a></li>
							<!--  
							<li><a href="#">修改商品</a></li>-->

						</ul></li>
					<li><a href="orderCenter">订单中心</a></li>
					<li><a href="storeInfo">商铺信息</a></li>
				</ul>
			</div>

		</div>
		<!-- 切割条-->
		<div class="row">
			<div class="col-sm-12 bg-success">
				<h6></h6>
			</div>
		</div>

		<%
			/*
			out.println(request.getAttribute("product"));
				if (request.getAttribute("product") != null) {*/

			List<Product> products = (ArrayList<Product>) request
					.getAttribute("product");
			if (products.size() != 0) {
				int size = products.size();
				String productName = null, productKind = null;
				for (int i = 0; i < products.size(); i++) {

					productName = URLEncoder.encode(URLEncoder.encode(products
							.get(i).getProduct_name()));

					productKind = URLEncoder.encode(URLEncoder.encode(products
							.get(i).getProduct_kind()));
					out.println("<div class=\"row\">"
							+ "<div class=\"col-sm-3\">" + "<img src=\""
							+ "/StrutsJson"
							+ products.get(i).getProduct_picture()
							+ "\" class=\"img-responsive img-thumbnail\""
							+ "alt=\"Cinque Terre\" height=\"50\">"
							+

							"</div>"
							+ "<div class=\"col-sm-6\">"
							+ "<div class=\"row\">"
							+ "<div class=\"col-sm-5\"><h4>名称:"
							+ products.get(i).getProduct_name()
							+ "</h4></div>"
							+ "<div class=\"col-sm-6\">"
							+ "<h4>类别:"
							+ products.get(i).getProduct_kind()
							+ "</h4>"
							+ "</div>"
							+ "<div class=\"col-sm-1\"></div>"
							+ "</div>"
							+

							"<div class=\"row\">"
							+ "<div class=\"col-sm-5\"><h4>价格:"
							+ products.get(i).getProduct_price()
							+ "</h4></div>"
							+ "<div class=\"col-sm-5\"><h4>库存:"
							+ products.get(i).getProduct_kucun()
							+ "</h4></div>"
							+ "<div class=\"col-sm-2\"></div>"
							+ "</div>"
							+ "<div class=\"row\">"
							+ "<div class=\"col-sm-5\"><h4>销售量:"
							+ products.get(i).getProduct_sales()
							+ "</h4></div>"
							+ "<div class=\"col-sm-5\"><h4>ID:"
							+ products.get(i).getProduct_id()
							+ "</h4></div>"
							+ "<div class=\"col-sm-2\"></div>"
							+ "</div>"
							+ "<div class=\"row\">"
							+ "<div class=\"col-sm-5\">"
							+ "<a class=\"btn btn-primary\" href=\""
							+ "/StrutsJson/s_modifyProduct.jsp?shop_id="
							+ products.get(i).getShop_id()
							+ "&product_id="
							+ products.get(i).getProduct_id()
							+ "&"
							+ "product_name="
							+ productName
							+ "&product_sales="
							+ products.get(i).getProduct_sales()
							+ "&product_price="
							+ products.get(i).getProduct_price()
							+ "&product_kind="
							+ productKind
							+ "&product_kucun="
							+ products.get(i).getProduct_kucun()
							+ "\">修改</a>"
							+ "</div>"
							+ "<div class=\"col-sm-5\">"
							+ "<a class=\"btn btn-primary\" href=\"deleteProduct?shop_id="
							+ products.get(i).getShop_id()
							+ "&product_id="
							+ products.get(i).getProduct_id()
							+ "&product_picture="
							+ products.get(i).getProduct_picture()
							+ "\">删除</a>"
							+ "</div>"
							+ "<div class=\"col-sm-2\"></div>"
							+ "</div>"
							+ "</div>"
							+ "<div class=\"col-sm-3\"></div>"
							+ "</div>");

				}

			} else {
				out.println("初始无任何商品!!点击商品管理,添加商品.");
			}
		%>
	</div>



</body>
</html>