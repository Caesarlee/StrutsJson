<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单确认</title>
</head>
<body>

      <form action="order/commitOrder" method="post">
         订单ID:<input type="text" name="order_id"><br/>      
      <input type="submit" value="确认">
      </form>
</body>
</html>