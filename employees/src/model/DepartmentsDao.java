package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.Departments;

//쿼리문을 입력해서 데이터 베이스에 있는 Departments 에 있는 데이터들을 가져와서 가져온 값을 departments에 저장한다.

public class DepartmentsDao {
	
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
