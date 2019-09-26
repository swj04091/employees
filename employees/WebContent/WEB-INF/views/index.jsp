<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<div class="row">
<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
<div class="col">
	<h1>Index</h1>
</div>

<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
<div class="col"></div>
</div>

	<div>
		<c:if test="${login != null}">
		<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
		<!-- Controller -> /LogoutServlet -->
		</c:if>
		<table class="table-borderless">
			<tr>
				<td><a href="${pageContext.request.contextPath}/departments/getDepartmentsList"  class="btn btn-outline-info">부서 목록</a></td>
				<td><a href="${pageContext.request.contextPath}/employees/getEmployeesList"  class="btn btn-outline-info">사원 목록</a></td>
				<td><a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=asc"  class="btn btn-outline-info">사원 목록 이름순 오름차순</a></td>
				<td><a href="${pageContext.request.contextPath}/employees/getEmployeesListOrderBy?order=desc"  class="btn btn-outline-info">사원 목록 이름순 내림차순</a></td>
				<td><a href="${pageContext.request.contextPath}/titles/getTitlesListDistinct"  class="btn btn-outline-info">업무 목록(중복제거 distinct)</a></td>
				<td><a href="${pageContext.request.contextPath}/salaries/getSalariesStatistics"  class="btn btn-outline-info">연봉 통계값(count, sum, avg, max, min, std)</a></td>
				<td><a href="${pageContext.request.contextPath}/employees/getEmployeesCountGender"  class="btn btn-outline-info">사원수(성별 group by gender)</a></td>
				<td><a href="${pageContext.request.contextPath}/departments/getDepartmentsCountByDeptNo"  class="btn btn-outline-info">부서목록(이름까지)</a></td>
				<td><a href="${pageContext.request.contextPath}/employees/getEmployeesListByPage"  class="btn btn-outline-info">사원 목록 페이징(10명씩)</a></td>
				<td><a href="${pageContext.request.contextPath}/departments/getDeptManagerListByPage" class="btn btn-outline-info">부서장 정보(페이징)</a></td>
			</tr>
		</table>
	</div>
	
	<br>
	
	<div class="row">
	<div class="col"></div>
	<div class="col">
		<h2>테이블 정보</h2>
		<table class="table table-bordered">
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
	<div class="col">
	<br><br>
		사원번호의 범위: ${minEmpNo}~${maxEmpNo}
		<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
			<input type="number" name="begin" class="form-control">
			<div>~</div>
			<input type="number" name="end" class="form-control">
			<br>
			<button type="submit" class="btn btn-outline-info">사원번호 범위내 사원검색</button>
		</form>
	</div>
	<div class="col"></div>
	</div>
</body>
</html>