package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class SalariesDao {
	
	public Map<String,Long> selectSalariesStatistics(){
		Map<String,Long> map = new HashMap<String,Long>();
		final String sql = "SELECT COUNT(salary), SUM(salary), AVG(salary), MAX(salary), MIN(salary), STD(salary) FROM salaries";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				map.put("count",rs.getLong("COUNT(salary)"));
				map.put("sum",rs.getLong("SUM(salary)"));
				map.put("avg",rs.getLong("AVG(salary)"));
				map.put("max",rs.getLong("MAX(salary)"));
				map.put("min",rs.getLong("MIN(salary)"));
				map.put("std",rs.getLong("STD(salary)"));
			}
			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	public int selectSalariesCount() {
		int count = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM salaries";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		}catch(Exception e) {	//자바의 변수 생명주기는 {}
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return count;
	}
}
