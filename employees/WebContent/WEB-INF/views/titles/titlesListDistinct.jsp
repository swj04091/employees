<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>titlesListDistinct</title>
</head>
<body>

	<c:if test="${login != null}">
		<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
		<!-- Controller -> /LogoutServlet -->
	</c:if>
	
	<div>
		<a href="${pageContext.request.contextPath}/"> 홈으로</a>
	</div>

	<h1>업무 목록(중복제거 distinct)</h1>
	<ol>
		<c:forEach var="row" items="${list}">
			<li>${row}</li>
		</c:forEach>
	</ol>
	
</body>
</html>