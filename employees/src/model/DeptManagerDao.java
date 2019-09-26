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

public class DeptManagerDao {
	
	public int deptManagerLastPage(int rowPerPage) {
		int count = selectDeptManagerCount();
		int lastPage = 0;
		
		
		if(count%rowPerPage != 0) {
			lastPage = (count/rowPerPage)+1;
		}else {
			lastPage = (count/rowPerPage);
		}
		
		System.out.println("lastPage= "+lastPage);
		
		return lastPage;
	}
	
	//DeptManager 의 사원정보,사원이름, 부서정보, 부서이름을 리턴 페이지도 알기 위해 currentPage와 rowPerPage를 매게변수로 받아옴
	public List<Map<String,Object>> selectDeptManagerByList(int currentPage, int rowPerPage){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		String sql = "SELECT d.dept_no , d.dept_name , e.emp_no , CONCAT(e.first_name, ' ', e.last_name) name"  + 
					 " FROM dept_manager dm" + 
					 " INNER JOIN employees e" + 
					 " INNER JOIN departments d" + 
					 " ON dm.dept_no = d.dept_no AND dm.emp_no = e.emp_no" + 
					 " ORDER BY to_date asc" +
					 " Limit ?,?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int beginRow = (currentPage-1)*rowPerPage;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map =new HashMap<String, Object>();
				
				map.put("deptNo",rs.getString("d.dept_no"));
				map.put("deptName",rs.getString("d.dept_name"));
				map.put("empNo",rs.getInt("e.emp_no"));
				map.put("name",rs.getString("name"));
				list.add(map);
			}

			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		
		return list;
	}	
	
	public int selectDeptManagerCount() {
		int count = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM dept_manager";
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
}
