<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmployeesListBetween</title>
</head>
<body>
	<h1>사원 목록 between</h1>
	
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
					<th>사원 번호</th>
					<th>사원 생일</th>
					<th>사원 이름</th>
					<th>사원 성</th>
					<th>사원 성별</th>
					<th>사원 입사일</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach var="employees" items="${list}">
				<tr>
					<td>${employees.empNo}</td>
					<td>${employees.birthDate}</td>
					<td>${employees.firstName}</td>
					<td>${employees.lastName}</td>
					<td>${employees.gender}</td>
					<td>${employees.hireDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>