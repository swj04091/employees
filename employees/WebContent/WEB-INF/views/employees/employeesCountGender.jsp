<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesCountGender</title>
</head>
<body>
	
	<div>
		<a href="${pageContext.request.contextPath}/"> 홈으로</a>
	</div>
	
	<h1>사원수(성별 group by gender)</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>성별</th>
				<th>인원</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="gender" items="${list}">
				<tr>
					<td>${gender.gender}</td>
					<td>${gender.cnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>