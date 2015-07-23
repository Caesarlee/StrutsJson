<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店商家信息修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 引入 Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
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
		    <div class="col-sm-9">
		      <h3>&nbsp</h3>
		    <h2>社区云商店商家信息修改</h2></div>
		 </div>
			

		</div>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<form class="form-horizontal" role="form"
					enctype="multipart/form-data" action="seller/storeInfoModify"
					method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">商家ID</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_id"
								readonly value=<%= request.getParameter("shop_id") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家账号</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_account"
							readonly	value=<%= request.getParameter("shop_account") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家名称</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_name"
							
								value=<%= URLDecoder.decode(URLDecoder.decode(request.getParameter("shop_name"), "utf-8"),"utf-8")%>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家密码</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_password"
								value=<%= request.getParameter("shop_password") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">商家地址</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_address"
								value=<%=URLDecoder.decode(URLDecoder.decode(request.getParameter("shop_address"), "utf-8"),"utf-8") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_phone_number"
								value=<%= request.getParameter("shop_phone") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家类型</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_kind"
								value=<%=URLDecoder.decode(URLDecoder.decode(request.getParameter("shop_kind"), "utf-8"),"utf-8")  %>>
						</div>
						<div class="col-sm-3"></div>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label">开放时间</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="open_time"
								value=<%=URLDecoder.decode(URLDecoder.decode(request.getParameter("open_time"), "utf-8"),"utf-8") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">起送价</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="qisongjia"
								value=<%= request.getParameter("qisongjia") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">配送费</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="peisong"
								value=<%= request.getParameter("peisong") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">物流信息</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="wuliu"
								value=<%= URLDecoder.decode(URLDecoder.decode(request.getParameter("wuliu"), "utf-8"),"utf-8") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家备注</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_remarks"
								value=<%= URLDecoder.decode(URLDecoder.decode(request.getParameter("shop_remarks"), "utf-8"),"utf-8") %>>
						</div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">商家图标</label>
						<div class="col-sm-7">
							<input type="file" id="inputfile" name="upload"> <small>目前仅支持jpg、jpeg、png格式</small>
						</div>
						<div class="col-sm-3"></div>
					</div>


					<div class="form-group">
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
						<div class="col-sm-7">
							<a class="btn btn-primary" href="seller/storeInfo">返回</a>
						</div>
						<div class="col-sm-3"></div>
					</div>


				</form>
				<p>
					<%
						if (request.getAttribute("reason") != null)
							out.println(request.getAttribute("reason"));
					%>
				</p>
			</div>

			<div class="col-sm-3"></div>
		</div>

	</div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>