<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

      <form action="user/register" method="post">
         用户名:<input type="text" name="userName"><br/>
         密码:<input type="text" name="password"><br/>
         电话:<input type="text" name="phone"><br/>
         城市:<input type="text" name="city"><br/>
         地区:<input type="text" name="district"><br/>
         详细:<input type="text" name="addr"><br/>
        
      <input type="submit" value="注册">
      </form>
</body>
</html>