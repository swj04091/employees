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
import vo.Departments;



public class DepartmentsDao {
	
	public List<Map<String,Object>> selectDepartmentsCountByDeptNo(){
		//리턴을 받을 값의 데이터 타입은 List<Map<String,Object>>
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		final String sql = "select de.dept_no, d.dept_name ,count(d.dept_no)\r\n" + 
							" from dept_emp de" + 
							" inner join departments d" + 
							" on de.dept_no = d.dept_no" + 
							" where to_date = '9999-01-01'" + 
							" group by d.dept_no" + 
							" order by count(d.dept_no) desc";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("deptNo", rs.getString("de.dept_no"));	//map에  de.dept_no를 저장한다.	키 값으로 deptNo를 가진다
				map.put("deptName", rs.getString("d.dept_name"));	//map에 d.dept_name을 저장한다.	키값으로 deptName을 가진다.
				map.put("count", rs.getString("count(d.dept_no)"));	//map에 count(d.dept_no)저장한다. 키값으로 count를 가진다.
				list.add(map);	//map을 list에 저장한다.
			}
			
			System.out.println(list);
			
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
	
	public int selectDepartmentsCount() {
		int count = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM departments";
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
	
	public List<Departments> selectDepartmentsList(){
		List<Departments> list = new ArrayList<Departments>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT dept_no, dept_name FROM departments";
		
	try {
		conn = DBHelper.getConnection();
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Departments departments = new Departments();
			departments.setDeptNo(rs.getString("dept_no"));
			departments.setDeptName(rs.getString("dept_name"));
			list.add(departments);
		}
		}catch(Exception e){
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
}
