<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单评论</title>
</head>
<body>

	<form action="comment/commentOrder" method="post">
		订单ID:<input type="text" name="orderId"><br /> 
		星级:<input type="text" name="comment_star"><br />
		评论内容:<input type="text" name="comment_content"><br />
		<input
			type="submit" value="确定">
	</form>
</body>
</html>