<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesListByPage</title>
</head>
<body>
	<h1>사원 목록 페이징</h1>
	
	<div>
		<a href="${pageContext.request.contextPath}/"> 홈으로</a>
	</div>
	
	
	<div>
		현재 페이지: ${currentPage}
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>사원번호</th>
				<th>사원생일</th>
				<th>사원이름</th>
				<th>사원성씨</th>
				<th>사원성별</th>
				<th>입사날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${list}">
				<tr>
					<td>${emp.empNo}</td>
					<td>${emp.birthDate}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.gender}</td>
					<td>${emp.hireDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage-1}">이전</a>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage?currentPage=${currentPage+1}">다음</a>
	</c:if>
	
</body>
</html>