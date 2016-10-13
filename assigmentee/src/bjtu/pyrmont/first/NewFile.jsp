<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="form1" name="form1" method="post" action="Servlet/LoginServlet">
		<table width="260" border="1" align="center">
		  <tr>
			<td width="64">用户：</td>
			<td width="180"><input type="text" name="username" /></td>
		  </tr>
		  <tr>
			<td>密码：</td>
			<td> <input type="password" name="userpass" /></td>
		  </tr>
		  <tr>
			<td> </td>
			<td>
			  <input type="submit" name="Submit2" value="登录" />
			</td>
		  </tr>
		</table>
	</form>
</body>
</html>