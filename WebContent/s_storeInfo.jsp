<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.seller.action.StoreEntity"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店商家信息</title>
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
					<img class="img-responsive" alt="shop profile"
						src="/StrutsJson/image/logo.png">
				</div>
				<div class="col-sm-9">
					<h3>&nbsp</h3>
					<h2>社区云商店商家信息</h2>
				</div>
			</div>


		</div>

		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<div class="row">
					<div class="col-sm-6">

						<img class="img-thumbnail img-responsive" alt="shop profile"
							src=<%= "/StrutsJson"+((StoreEntity)request.getAttribute("storeInfo")).getShop_picture()%>>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<label>商家ID:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_id()
 %>
								</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-6">
								<label>商家账号:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_account()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>账号密码:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_password()
 %>
								</label>
							</div>
						</div>

					</div>

				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<label>商家名称:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_name()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>起送价:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getQisongjia()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>配送费:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getPeisong()
 %>
								</label>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<label>开放时间:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getOpen_time()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>商家地址:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_address()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>物流信息:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getWuliu()
 %>
								</label>
							</div>
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<label>联系方式:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_phone_number()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>商家类型:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_kind()
 %>
								</label>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-6">
								<label>商家备注:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_remarks()
 %>
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<label>是否通过注册:</label>
							</div>
							<div class="col-sm-6">
								<label> <%=
 	((StoreEntity) request.getAttribute("storeInfo")).getShop_permit()
 %>
								</label>
							</div>
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col-sm-2">
						 	  
							<a class="btn btn-primary" href="/StrutsJson/s_storeInfoModify.jsp?shop_id=<%=((StoreEntity) request.getAttribute("storeInfo")).getShop_id() %>&shop_account=<%=	((StoreEntity) request.getAttribute("storeInfo")).getShop_account() %>&shop_name=
						
						<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getShop_name())) %>&shop_password=<%=((StoreEntity) request.getAttribute("storeInfo")).getShop_password() %>&shop_address=<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getShop_address()))%>&shop_phone=<%=	((StoreEntity) request.getAttribute("storeInfo")).getShop_phone_number()%>
						
						&shop_kind=<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getShop_kind()))%>&open_time=<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getOpen_time()))%>&qisongjia=<%=((StoreEntity) request.getAttribute("storeInfo")).getQisongjia() %>&peisong=<%=((StoreEntity) request.getAttribute("storeInfo")).getPeisong() %>&wuliu=
						
						<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getWuliu()))%>&shop_remarks=<%=URLEncoder.encode(URLEncoder.encode(((StoreEntity) request.getAttribute("storeInfo")).getShop_remarks()))%>">修改</a>
				    
					</div>
					<div class="col-sm-7">
						<a class="btn btn-primary"
							href="mainPage">返回</a>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<%
                if(request.getAttribute("reason")!=null){
                	out.println(request.getAttribute("reason"));
                }
             %>
			</div>

			<div class="col-sm-2"></div>
		</div>

	</div>


	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script type="text/javascript"
		src="/StrutsJson/js/jquery-1.11.3.min.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script type="text/javascript" src="/StrutsJson/js/bootstrap.min.js"></script>
</body>
</html>