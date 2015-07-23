<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
</head>
<body>
    <form action="order/fetchOrders" method="post">
    客户名称:<input type="text" name="customer_name"><br/>
           订单状态:<input type="text" name="orderCondition"><br/>  
         <input type="submit" value="确定">
      </form>
</body>
</html>