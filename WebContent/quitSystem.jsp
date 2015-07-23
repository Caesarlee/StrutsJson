<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
   <%
   //清除会话信息
     session.invalidate();
    // System.out.println("退出后,shop_id="+session.getAttribute("shop_id"));
     //response.sendRedirect("s_index.jsp");
   %>
   <jsp:forward page="s_index.jsp"></jsp:forward>
   <
</body>
</html>