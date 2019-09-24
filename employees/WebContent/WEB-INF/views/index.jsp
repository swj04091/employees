<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<h1>Index</h1>
	
	<h2>테이블 정보</h2>
	<div>
	<table border="1">
		<thead>
			<tr>
				<th>테이블 이름</th>
				<th>전체 행의 수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Employees</td>
				<td>${employeesRowCount}</td>
			</tr>
			
			<tr>
				<td>Departments</td>
				<td>${departmentsRowCount}</td>
			</tr>
			
			<tr>
				<td>dept_emp</td>
				<td>${deptEmpRowCount}</td>
			</tr>
			
			<tr>
				<td>dept_manager</td>
				<td>${deptManagerRowCount}</td>
			</tr>
			
			<tr>
				<td>salaries</td>
				<td>${salariesRowCount}</td>
			</tr>
			
			<tr>
				<td>titles</td>
				<td>${titlesRowCount}</td>
			</tr>
			
		</tbody>
	</table>
	</div>
	
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/departments/getDepartmentsList">부서 목록</a></li>
			<li><a href="${pageContext.request.contextPath}/employees/getEmployeesList">사원 목록</a></li>
			
			<li>
				사원 목록 first_name
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc">오름차순</a>
				<a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc">내림차순</a>
			</li>
			
			<li><a href="${pageContext.request.contextPath}/titles/getTitlesListDistinct">업무 목록(중복제거 distinct)</a></li>
			
		</ul>
	</div>
	
	<div>
		employees table total row count : <%=request.getAttribute("employeesRowCount") %>
	</div>
</body>
</html>