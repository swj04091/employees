<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptEmpListByPage</title>
</head>
<body>
	<h1>부서장 목록</h1>
	
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
				<th>
					부서번호
				</th>
				<th>
					부서명
				</th>
				<th>
					사원번호
				</th>
				<th>
					사원이름
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="deptEmp" items="${list}">
				<tr>
					<td>${deptEmp.deptNo}</td>
					<td>${deptEmp.deptName}</td>
					<td>${deptEmp.empNo}</td>
					<td>${deptEmp.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${currentPage > 1}">
		<div><a href="${pageContext.request.contextPath}/departments/getDeptManagerListByPage?currentPage=${currentPage-1}">이전</a></div>
	</c:if>
	<c:if test="${currentPage < lastPage}">
		<div><a href="${pageContext.request.contextPath}/departments/getDeptManagerListByPage?currentPage=${currentPage+1}">다음</a></div>
	</c:if>
	
</body>
</html>