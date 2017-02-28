package com.ktds.dojun.board.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.dojun.board.user.vo.UsersVO;

public class UserDaoImpl implements UserDao {

	@Override
	public int addUser(UsersVO usersVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 연결 실패");
			return 0;

		}
		Connection conn = null;
		PreparedStatement stmt = null;

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		try {

			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO USRS ( 	");
			query.append("        USR_ID, 			");
			query.append("        USR_NM,  		");
			query.append("        USR_PWD,  	");
			query.append("        JOIN_DTE )	");
			query.append(" VALUES( 					");
			query.append("         ? 				");
			query.append("        , ? 				");
			query.append("        , ? 				");
			query.append(" 		, SYSDATE 						");
			query.append(" ) 					");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, usersVO.getUserId());
			stmt.setString(2, usersVO.getUserName());
			stmt.setString(3, usersVO.getUserPassword());

			return stmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("인스턴스 연결 실패.");

			return 0;

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {

			}
		}

	}

	@Override
	public UsersVO loginUser(UsersVO usersVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 연결 실패");
			return null;

		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		try {

			conn = DriverManager.getConnection(oracleUrl, "BOARD", "board");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT 	");
			query.append("        USR_ID, 			");
			query.append("        USR_NM,  		");
			query.append("        USR_PWD,  	");
			query.append("        JOIN_DTE 	");
			query.append("   FROM     USRS 	");
			query.append("  WHERE USR_ID = ?  	");
			query.append("   AND USR_PWD = ?  	");
			

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, usersVO.getUserId());
			stmt.setString(2, usersVO.getUserPassword());
			
			rs = stmt.executeQuery();
			UsersVO user = null;
			while(rs.next()){
				
				user = new UsersVO();
				user.setUserId(rs.getString("USR_ID"));
				user.setUserName(rs.getString("USR_NM"));
				user.setUserPassword(rs.getString("USR_PWD"));
				user.setJoinDate(rs.getString("JOIN_DTE"));
			}
			
			return user;

		} catch (SQLException e) {

			System.out.println("인스턴스 연결 실패.");

			return null;

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {

			}
		}

	}
}
