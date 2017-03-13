package com.melon.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.melon.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	@Override
	public int insertNewUser(UserVO userVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);

		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");

			StringBuffer query = new StringBuffer();

			query.append(" INSERT INTO USR ( USR_ID, USR_NM , USR_PWD , USR_PNT   ) ");
			query.append(" VALUES ( ? , ? , ? , 0 ) ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
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
	public UserVO selectOneUser(UserVO userVO) {
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

			StringBuffer query = new StringBuffer();

			query.append(" SELECT  USR_ID, USR_NM , USR_PWD , USR_PNT  ");
			query.append(" FROM USR ");
			query.append(" WHERE USR_ID = ? ");
			query.append(" AND USR_PWD = ? ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());

			rs = stmt.executeQuery();
			UserVO user = null;
			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
			}

			return userVO;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
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

	@Override
	public int updatePoint(String userId, int point) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);

		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");

			StringBuffer query = new StringBuffer();

			query.append(" UPDATE	USR                      ");
			query.append(" SET		USR_PNT = USR_PNT + ?    ");
			query.append(" WHERE	USR_ID = ?               ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, point);
			stmt.setString(2, userId);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
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
	public int selectCountByUserId(String userId) {
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

			StringBuffer query = new StringBuffer();

			query.append(" SELECT	COUNT(1) CNT                      ");
			query.append(" FROM		USR                      ");
			query.append(" WHERE	USR_ID = ?               ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userId);

			rs = stmt.executeQuery();

			if (rs.next()) {

				return rs.getInt("CNT");
			}
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
			
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
