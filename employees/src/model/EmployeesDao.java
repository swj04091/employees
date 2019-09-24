package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.Employees;

public class EmployeesDao {
	
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
