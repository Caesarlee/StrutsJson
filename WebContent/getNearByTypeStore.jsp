<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取附加某类商家</title>
</head>
<body>
 <form action="store/nearbyStore" method="post">
   类型:<input type="text" name="storeType"><br/> 
         经度:<input type="text" name="langitude"><br/>
         纬度:<input type="text" name="latitude"><br/>
     
        
      <input type="submit" value="确定">
      </form>
</body>
</html>