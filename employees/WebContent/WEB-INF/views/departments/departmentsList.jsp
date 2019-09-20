<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsList</title>
</head>
<body>
<!-- servlet에서 처리 하는 내용을 출력 해주는 페이지 -->
<h1>부서 목록</h1>
<table border = "1">
		<thead>
			<tr>
				<th>부서번호</th>
				<th>부서이름</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="departments" items="${list}">	<!-- 커스텀 테이블의 큰따옴표는 값이 들어가야됨. -->
				<tr>
					<td>${departments.deptNo}</td><!--EL : deptNo 출력 -->
					<td>${departments.deptName}</td><!--EL : deptName 출력 -->
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>