<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>salariesStatistics</title>
</head>
<body>

	<h1>연봉 통계값(count, sum, avg, max, min, std)</h1>
	
	<c:if test="${login != null}">
		<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
		<!-- Controller -> /LogoutServlet -->
	</c:if>
	
	<div>
		<a href="${pageContext.request.contextPath}/"> 홈으로</a>
	</div>
	
	<table border="1">
		<thead>
			<tr>
				<th>count</th>
				<th>sum</th>
				<th>avg</th>
				<th>max</th>
				<th>min</th>
				<th>std</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${map.count}</td>
				<td>${map.sum}</td>
				<td>${map.avg}</td>
				<td>${map.max}</td>
				<td>${map.min}</td>
				<td>${map.std}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>