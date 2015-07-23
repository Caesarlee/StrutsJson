<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取订单的商品详情</title>
</head>
<body>
    <form action="order/fetchOrder_product" method="post">
           订单ID:<input type="text" name="orderId"><br/>  
         <input type="submit" value="确定">
      </form>
</body>
</html>