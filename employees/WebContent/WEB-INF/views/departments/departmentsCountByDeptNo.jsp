<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DepartmentsCountByDeptNo</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/"> 홈으로</a>
	</div>
	
	<h1>부서내 인구수</h1>
	
	<table border="1">
		<thead>
			<tr>
				<td>부서 번호</td>
				<td>부서 이름</td>
				<td>인구수 </td>
			</tr>
		</thead>	
		<tbody>
			<c:forEach var="dept" items="${list}">
				<tr>
					<td>${dept.deptNo}</td>
					<td>${dept.deptName}</td>
					<td>${dept.count}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>