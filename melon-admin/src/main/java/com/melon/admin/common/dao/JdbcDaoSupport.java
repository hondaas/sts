package com.melon.admin.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcDaoSupport {
	
	public Object selectOne() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			String query = query();
		
			stmt = conn.prepareStatement(query);
			
			mappingParams(stmt);
			
			rs = stmt.executeQuery();
	
			if(rs.next()) {
				//VO 만들기...
				return bindData(rs);
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		finally{
			
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		
		}
	}
	
	public abstract String query();
	public abstract void mappingParams(PreparedStatement stmt) throws SQLException;
	public abstract Object bindData(ResultSet rs) throws SQLException;
	
}
