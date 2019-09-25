package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBHelper;
import vo.Employees;

public class EmployeesDao {
	
	//마지막페이지 확인
	public int selectLastPage(int rowPerPage) {
		/*
		 * 마지막 페이지 구하기
		 * (모든 데이터의 갯수 / 페이지에 보여줄 갯수) 의 나머지의 값이 0
		 * */
		int endRow = selectEmployeesCount();
		int lastPage = 0;
		
		if((endRow%rowPerPage)==0) {
			lastPage = endRow/rowPerPage;
		}else {
			lastPage = (endRow/rowPerPage)+1;
		}
		
		return lastPage;
	}
	
	//페이지추가(페이징하기)
	public List<Employees> selectEmployeesListByPage(int currentPage, int rowPerPage){
		//Employees의 내용을 담기 위한 arraylist 생성
		List<Employees> list = new ArrayList<Employees>();
		
		System.out.println("EmployeesDao`s currentPage: "+ currentPage);
		System.out.println("EmployeesDao`s rowPerPage: "+ rowPerPage);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//페이지의 시작
		int startRow = (currentPage-1)*rowPerPage;
		
		try {
			//데이터 베이스에서 가져올 데이터 값과, 페이징을 위한 limit문 사용
			String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees Limit ?,?";
			conn = DBHelper.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,startRow);
			stmt.setInt(2,rowPerPage);
			rs = stmt.executeQuery();
			
			System.out.println(stmt);
			
			//employees테이블의 컬럼의 다음이 없을때 까지 반복
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				//Arraylist에 데이터 추가
				list.add(employees);
			}
			System.out.println("EmployeesDao`s list: "+list);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					DBHelper.close(rs, stmt, conn);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		//반환
		return list;
	}
	
	public List<Employees> selectEmployeesListBetween(int min, int max) {
		List<Employees> list = new ArrayList<Employees>();
		
		final String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE emp_no BETWEEN ? AND ? ORDER BY emp_no asc";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,min);
			stmt.setInt(2,max);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
			}
			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {
				DBHelper.close(rs, stmt, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	public int selectEmpNo(String str) {
		int empNo = 0;
		String sql = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		if(str.equals("max")) {
			sql="SELECT MAX(emp_no) FROM employees";
		}
		else if(str.equals("min")){
			sql="SELECT MiN(emp_no) FROM employees";
		}
	
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				empNo = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return empNo;
	}
	
	
	public List<Map<String,Object>> selectEmployeesCountGroupByGender(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		final String sql = "SELECT gender, COUNT(gender) FROM employees GROUP BY gender";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("gender",rs.getString("gender"));
				map.put("cnt", rs.getLong("COUNT(gender)"));
				list.add(map);
			}
			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {
				DBHelper.close(rs, stmt, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<Employees> selectEmployeesListOrderBy(String order){
		
		List<Employees> list = new ArrayList<Employees>();
		String sql = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//여기서 분기를 주어 asc이면 오름차순으로, desc이면 내림차순의 쿼리문을 입력한다.
		if(order.equals("asc")) {
			sql="SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees order by first_name asc LIMIT 50";
		}else if(order.equals("desc")) {
			sql="SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees order by first_name desc LIMIT 50";
		}
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			System.out.println(rs);
			
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
			}
			
			System.out.println(list);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBHelper.close(rs, stmt, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
	// employees에 있는 모든 테이블의 갯수를 알려주는 메소드
	public int selectEmployeesCount() {
		int count = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM employees";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {
				DBHelper.close(rs, stmt, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	// 갯수를 정한 만큼 employees테이블을 보여줌.
	public List<Employees> selectEmployeesList(int limit){
		List<Employees> list = new ArrayList<Employees>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees Limit ?";
			conn = DBHelper.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,limit);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					DBHelper.close(rs, stmt, conn);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		return list;
	}
}
