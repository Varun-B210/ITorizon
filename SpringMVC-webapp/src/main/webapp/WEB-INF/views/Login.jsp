<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yahoo!!!! from JSP</title>
</head>
<body>
<form action="/login" method ="POST">
<font color="red">${errormsg}</font><br>
Enter name: <input type="text" name="name"/>
Enter password:<input type="password" name="password"/>
<input type ="submit" value ="login"/>
</form>

</body>
</html>