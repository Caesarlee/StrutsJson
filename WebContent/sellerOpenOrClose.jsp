<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家改变营业状态</title>
</head>
<body>
	<form action="seller/openClose" method="post">
		商家ID:<input type="text" name="shop_id" /> <br /> 状态:<input
			type="text" name="openOrClose" /> <br /> <input type="submit"
			value="确定" />

	</form>
</body>
</html>