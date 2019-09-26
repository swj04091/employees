<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<table border = "1">
			<tr>
				<td>emp_no: <input type="text" name="empNo"></td>
			</tr>
			
			<tr>
				<td>first_name: <input type="text" name="firstName"></td>
			</tr>
			
			<tr>
				<td>last_name:<input type="text" name="lastName"></td>
			</tr>
		</table>
		
		<button type="submit">로그인</button>
	</form>
</body>
</html>