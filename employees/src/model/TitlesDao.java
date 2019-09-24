package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBHelper;

public class TitlesDao {
	
	public List<String> selectTitlesListDistinct(){
		List<String> list = new ArrayList<String>();
		
		final String sql = "SELECT DISTINCT title FROM titles";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("title"));
			}
			
			System.out.println("Titles.Dao의 list : "+list);
			
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
	
	public int selectTitlesCount() {
		int count = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM titles";
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
