<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title>云商店商家注册</title>
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

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lDyg23G8xboGvnq9ABzQSgg3"></script>
<script type="text/javascript">
	//获取地理位置的函数
	window.onload = function() {

		// 百度地图API功能
		

		/*var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
			
				document.getElementById("jing").value = r.point.lng;
				document.getElementById("wei").value = r.point.lat;
				//alert('您的位置：'+r.point.lng+','+r.point.lat);
			}
			else {
				alert('获取经纬度失败,failed'+this.getStatus());
			}        
		},{enableHighAccuracy: true})*/
		//关于状态码
		//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
		//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
		//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
		//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
		//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
		//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
		//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
		//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
		//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
		if (navigator.geolocation) {

			navigator.geolocation.getCurrentPosition(function(position) {
				var latitude = position.coords.latitude;
				var longitude = position.coords.longitude;

				document.getElementById("jing").value = longitude;
				document.getElementById("wei").value = latitude;

			}, function(error) {
				document.getElementById("jw").innerHTML = error.message
						|| "获取地理位置的时候发生了错误。";
			}, function(error) {
				console.log("Error code: " + error.code);
				console.log("Error message: " + error.message);
			});
		} else {
			document.getElementById("jingDuIsNull").innerHTML="你的浏览器无法获取经纬度";
			document.getElementById("weiDuIsNull").innerHTML="建议换一个浏览器注册!!";
			
			//alert("你的浏览器不支持 Geolocation API,无法获取经纬度!!客户无法通过定位功能找到你的店铺,建议换一个浏览器进行注册!!")
		}
	}
	//提交按钮的函数,判断主要信息是否填写完全
	function check() {
		with (document.all) {
			shopNameIsNull.innerHTML = "";
			var sn = true;
			shopIdIsNull.innerHTML = "";
			var si = true;
			var siReg=true;
			passwordIsNull.innerHTML = "";
			var pw = true;
			var reg=true;
			xiangtong.innerHTML = "";
			var xt = true;
			shopAddressIsNull.innerHTML = "";
			var sa = true;
			shopPhoneIsNull.innerHTML = "";
			var sp = true;
			shopTypeIsNull.innerHTML = "";
			var st = true;
			openTimeIsNull.innerHTML = "";
			var ot = true;
			qiSongJiaIsNull.innerHTML = "";
			var qsj = true;
			peiSongIsNull.innerHTML = "";
			var ps = true;
			wuLiuIsNull.innerHTML = "";
			var wl = true;
			if (shopName.value == "") {
				shopNameIsNull.innerHTML = "请填写商店名称";
				sn = false;

			}
			if (shopId.value == "") {
				shopIdIsNull.innerHTML = "请填写商店账号";
				si = false;

			}
			if(shopId.value!=""){
				var re=new RegExp("^[a-zA-Z0-9_]+$");
				var res=re.test(shopId.value);
				if(res){
					sireg=true;
				}else{
					sireg=false;
				}
				shopIdIsNull.innerHTML = "商店账号格式不对";
			}
			if (password1.value == "") {
				passwordIsNull.innerHTML = "请输入密码";
				pw = false;

			}
			if(password1.value!=""){
				var re=new RegExp("^[a-zA-Z0-9_]+$");
				var res=re.test(password1.value);
				if(res){
					reg=true;
				}else{
					reg=false;
				}
				passwordIsNull.innerHTML = "密码格式不对";
			}
			if (password1.value != password2.value) {
				xiangtong.innerHTML = "密码不相同";
				password1.value = "";
				password2.value = "";
				xt = false;

			}
			if (shopAddress.value == "") {
				shopAddressIsNull.innerHTML = "请填写商店地址";
				sa = false;

			}
			if (shopPhone.value == "") {
				shopPhoneIsNull.innerHTML = "请填写联系方式";
				sp = false;

			}/*
												if (shopType.value == "") {
													shopTypeIsNull.innerHTML = "请填写商店类型";
													st = false;

												}*/
			if (openTime.value == "") {
				openTimeIsNull.innerHTML = "请填写开放时间";
				ot = false;

			}
			if (qiSongJia.value == "") {
				qiSongJiaIsNull.innerHTML = "请填写起送价";
				qsj = false;

			}
			if (peiSong.value == "") {
				peiSongIsNull.innerHTML = "请填写配送费";
				ps = false

			}
			if (wuLiu.value == "") {
				wuLiuIsNull.innerHTML = "请填写物流信息";
				wl = false;

			}
		}

		if (sn && si && sa && pw && xt && ps && qsj && wl && sp && ot && st&&reg&&siReg) {
			document.forms[0].submit();
		}

		return false;
	}
</script>
</head>
<body>

	<div class="container">
		<div class="jumbotron" style="background: #337ab7;">
			<h2>云商店商家注册</h2>

		</div>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<form class="form-horizontal" role="form"
					enctype="multipart/form-data" action="seller/sellerRegister"
					method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">商家名称</label>
						<div class="col-sm-7">
							<input id="shopName" type="text" class="form-control"
								name="shop_name" placeholder="请输入名称">
						</div>
						<div id="shopNameIsNull" class="text-danger"></div>

						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家账号</label>
						<div class="col-sm-7">
							<input id="shopId" type="text" class="form-control"
								name="shop_account" placeholder="仅支持英文字母、数字、下划线">
						</div>
						<div id="shopIdIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家密码</label>
						<div class="col-sm-7">
							<input id="password1" type="password" class="form-control"
								name="shop_password" placeholder="仅支持英文字母、数字、下划线">
						</div>
						<div id="passwordIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-7">
							<input id="password2" type="password" class="form-control"
								placeholder="请输入密码">
						</div>
						<div id="xiangtong" class="text-danger"></div>

						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家地址</label>
						<div class="col-sm-7">
							<input id="shopAddress" type="text" class="form-control"
								name="shop_address" placeholder="请输入地址">
						</div>
						<div id="shopAddressIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-7">
							<input id="shopPhone" type="text" class="form-control"
								name="shop_phone_number" placeholder="">
						</div>
						<div id="shopPhoneIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家类型</label>
						<div class="col-sm-7">
							<select class="form-control" name="shop_kind" id="shopType"
								placeholder="请选择类型">
								<option>便利店</option>
								<option>快餐店</option>
								<option>药店</option>
								<option>生活服务</option>
							</select>
							<!-- 
							<input id="shopType" type="text" class="form-control"
								name="shop_kind" placeholder="">
								 -->
						</div>
						<div id="shopTypeIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label">开放时间</label>
						<div class="col-sm-7">
							<input id="openTime" type="text" class="form-control"
								name="open_time" placeholder="如早上8点到下午4点">
						</div>
						<div id="openTimeIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">起送价</label>
						<div class="col-sm-7">
							<input id="qiSongJia" type="text" class="form-control"
								name="qisongjia" placeholder="">
						</div>
						<div id="qiSongJiaIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">配送费</label>
						<div class="col-sm-7">
							<input id="peiSong" type="text" class="form-control"
								name="peisong" placeholder="">
						</div>
						<div id="peiSongIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">物流信息</label>
						<div class="col-sm-7">
							<input id="wuLiu" type="text" class="form-control" name="wuliu"
								placeholder="请输入物流方式,如顺丰物流">
						</div>
						<div id="wuLiuIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商家备注</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="shop_remarks"
								placeholder="">
						</div>
						<div class="col-sm-3"></div>
					</div>

					<!-- 经纬度 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">您商店所在的经度</label>
						<div class="col-sm-7">
							<input id="jing" type="text" class="form-control"
								name="shop_jingdu" readonly placeholder="">
						</div>
						<div id="jingDuIsNull" class="text-danger"></div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">您商店所在的纬度</label>
						<div class="col-sm-7">
							<input id="wei" type="text" class="form-control"
								name="shop_weidu" readonly>
						</div>
						<div id="weiDuIsNull" class="text-danger"></div>
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
							<button type="button" class="btn btn-primary" onclick="check()">提交</button>
						</div>
						<div class="col-sm-7">
							<a class="btn btn-primary" href="/StrutsJson/s_index.jsp">返回</a>
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