<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>

</head>
<body>

	<center>
	
		<h3>一个简单的struts</h3>
		<br/><hr/>
		<form name="myform" action="order/dingdansubmit"  method="post">
        order_address:<input name="order_address" type="text" ><br>
        peisong:<input name="peisong" type="text" ><br>
        order_total:<input name="order_total" type="text" ><br>
        order_remarks:<input name="order_remarks" type="text" ><br>
        shop_name:<input name="shop_name" type="text" ><br>
        customer_name:<input name="customer_name" type="text" ><br>
        date:<input name="date" type="text" ><br>
        json:<input name="json" type="text" ><br>
        
        
        <input type="submit" value="submit">
    </form>
	</center>
</body>
</html>