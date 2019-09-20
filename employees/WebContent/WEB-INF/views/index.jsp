<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<h1>Index</h1>
	
	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="<%=request.getContextPath()%>/employees/getEmployeesList">사원 목록</a></li>
		</ul>
	</div>
	
	<div>
		employees table total row count : <%=request.getAttribute("employeesRowCount") %>
	</div>
</body>
</html>