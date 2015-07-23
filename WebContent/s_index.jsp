<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/StrutsJson/css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
</head>
<body>
	<div class="container">
		<div class="jumbotron" style="background: #337ab7;">
		 <div class="row">
		    <div class="col-sm-3">
		      	<img class="img-responsive" alt="shop profile" src="/StrutsJson/image/logo.png">
		    </div>
		    <div class="col-sm-9"><h1>欢迎访问社区云商店</h1></div>
		 </div>
			

		</div>
		<div class="row">

			<div class="col-sm-6">


				<form class="form-horizontal" role="form"
					action="seller/sellerLogin" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label">商家账号</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_account"
								placeholder="请输入账号">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家密码</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" placeholder="请输入密码"
								name="password">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox"> 请记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">商家登录</button>
							&nbsp&nbsp&nbsp&nbsp <a href="/StrutsJson/s_register.jsp"
								class="btn btn-primary">商家注册</a>
						</div>
					</div>
				</form>
				<p>
					<%
						if (request.getAttribute("reason") != null) {
							out.println(request.getAttribute("reason") + "!!");
						}
					%>
				</p>
			</div>

			<div class="col-sm-4">
				<h4>社区云商店Android App</h4>
				<p>&nbsp</p>
				<!-- 提供额外的视觉效果，标识一组按钮中的原始动作 -->
				<s:a href="apk/store1.4.9.2.apk">点击下载</s:a>
			</div>
			<div class="col-sm-2"></div>

		</div>
	</div>
	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script type="text/javascript"
		src="/StrutsJson/js/jquery-1.11.3.min.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script type="text/javascript" src="/StrutsJson/js/bootstrap.min.js"></script>
	<!-- 添加IE8对placeholder的支持 -->
	<script type="text/javascript"
		src="/StrutsJson/js/jquery.placeholder.js"></script>
	<script>
		$(function() {
			$('input, textarea').placeholder();
		});
	</script>
	<!-- 
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script
		src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		 -->
</body>
</html>