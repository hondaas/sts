package com.melon.admin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	@Override
	public int insertNewUser(UserVO newUserVO) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("	INSERT	INTO	USR		(               ");
			sb.append("									USR_ID  ");
			sb.append("								,	USR_NM  ");
			sb.append("								,	USR_PWD ");
			sb.append("								,	USR_PNT ");
			sb.append("							)               ");
			sb.append("	VALUES					(               ");
			sb.append("									?       ");
			sb.append("								,	?       ");
			sb.append("								,	?       ");
			sb.append("								,	0       ");
			sb.append("							)               ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, newUserVO.getUserId());
			stmt.setString(2, newUserVO.getUserName());
			stmt.setString(3, newUserVO.getUserPassword());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("		SELECT 		*	");
			sb.append("		FROM		( 	");
			sb.append("					SELECT	ROWNUM AS RNUM 	");
			sb.append("							, A.* 	");
			sb.append("					FROM				( 	");
			sb.append("		      				SELECT 			U.USR_ID	");
			sb.append("		       			       			,	U.USR_NM  	");
			sb.append("		       			      			,	U.USR_PWD	");
			sb.append("		       			       			,	U.USR_PNT	");
			sb.append("		       			       			, 	AT.ATHRZTN_ID	");
			sb.append("		       			       			, 	AT.ATHRZTN_NM ");
			sb.append("		       			       			, 	AT.PRNT_ATHRZTN_ID ");
			sb.append("		       				FROM		USR U	");
			sb.append("		       							, ATHRZTN AT ");
			sb.append("		       				WHERE		U.ATHRZTN_ID =	AT.ATHRZTN_ID(+) ");
			sb.append("		       							) 	A 	");
			sb.append("		       				WHERE	ROWNUM <= ? ");
			sb.append("		       				) ");
			sb.append("						WHERE	RNUM >= ? ");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, userSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, userSearchVO.getPager().getStartArticleNumber());
			rs = stmt.executeQuery();

			List<UserVO> userList = new ArrayList<UserVO>();
			UserVO userVO = null;

			while (rs.next()) {
				userVO = new UserVO();
				userVO.setIndex(rs.getInt("RNUM"));
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				userList.add(userVO);
			}

			System.out.println("user id  = " + userVO.getUserId());
			System.out.println("user pwd = " + userVO.getUserPassword());
			System.out.println("user nm = " + userVO.getUserName());

			return userList;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public int updateUserInfo(UserVO userVO) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("		UPDATE 		USR           ");
			sb.append("		SET		 	");
			sb.append("					 	USR_PNT	=	? 	");
			sb.append("					,	USR_PWD =	?	");
			sb.append("					,	ATHRZTN_ID =	?	");
			sb.append("		WHERE		USR_ID	=	?	");

			stmt = conn.prepareStatement(sb.toString());

			// stmt.setString(1, userVO.getUserName());
			stmt.setInt(1, userVO.getUserPoint());
			stmt.setString(2, userVO.getUserPassword());
			stmt.setString(3, userVO.getAuthorizationId());
			stmt.setString(4, userVO.getUserId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public int deleteOneUser(String userId) {

		openJDBC();

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");

			StringBuffer sb = new StringBuffer();

			sb.append(" DELETE  		");
			sb.append(" FROM	USR ");
			sb.append("	WHERE 	USR_ID = ?");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, userId);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		}

	}

	@Override
	public UserVO selectOneUser(String userId) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("	SELECT			U.USR_ID		");
			sb.append("					,		U.USR_NM          ");
			sb.append("					,		U.USR_PWD         ");
			sb.append("					,		U.USR_PNT         ");
			sb.append("					,		U.ATHRZTN_ID	U_ATHRZTN_ID	");
			sb.append("					,		A.ATHRZTN_ID         ");
			sb.append("					,		A.ATHRZTN_NM         ");
			sb.append("					,		A.PRNT_ATHRZTN_ID         ");
			sb.append("	FROM			USR U        ");
			sb.append("					,	ATHRZTN A        ");
			sb.append("	WHERE			U.ATHRZTN_ID = A.ATHRZTN_ID(+)  ");
			sb.append("	AND				U.USR_ID = ?  	");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
			UserVO userVO = null;

			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
			}

			// System.out.println("user id = " + userVO.getUserId());
			// System.out.println("user pwd = " + userVO.getUserPassword());
			// System.out.println("user nm = " + userVO.getUserName());

			return userVO;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	public void openJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);

		}
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("	SELECT			U.USR_ID		");
			sb.append("					,		U.USR_NM          ");
			sb.append("					,		U.USR_PWD         ");
			sb.append("					,		U.USR_PNT         ");
			sb.append("					,		U.ATHRZTN_ID	U_ATHRZTN_ID	");
			sb.append("					,		A.ATHRZTN_ID         ");
			sb.append("					,		A.ATHRZTN_NM         ");
			sb.append("					,		A.PRNT_ATHRZTN_ID         ");
			sb.append("	FROM			USR U        ");
			sb.append("					,	ATHRZTN A        ");
			sb.append("	WHERE			U.ATHRZTN_ID = A.ATHRZTN_ID(+)  ");
			sb.append("	AND				U.ATHRZTN_ID = 'AT-2017032009-000050'  	");
			sb.append("	AND				U.USR_ID = ?  	");
			sb.append("	AND				U.USR_PWD = ?  	");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, userVO.getUserId());
			rs = stmt.executeQuery();

			if (rs.next()) {
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PWD"));
				userVO.setUserPoint(rs.getInt("USR_PNT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				userVO.getAuthorizationVO().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				userVO.getAuthorizationVO().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
			}

			// Logger("user id = " + userVO.getUserId());
			System.out.println("user pwd = " + userVO.getUserPassword());
			System.out.println("user nm = " + userVO.getUserName());

			return userVO;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}
	}

	@Override
	public int selectAllUserCount(UserSearchVO userSearchVO) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");

			StringBuffer sb = new StringBuffer();

			sb.append("		SELECT 		COUNT(1)	CNT	");
			sb.append("		FROM		USR U	");
			sb.append("					,  ATHRZTN A ");
			sb.append("		WHERE U.ATHRZTN_ID = A.ATHRZTN_ID(+) ");

			stmt = conn.prepareStatement(sb.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("CNT");
			}

			return 0;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	@Override
	public int changeUser(String checkedUserId, String beforeAuthorization, String afterAuthorization) {

		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			StringBuffer sb = new StringBuffer();

			sb.append("		UPDATE 		USR           ");
			sb.append("		SET		 	");
			sb.append("					ATHRZTN_ID  =	?	");

			if (checkedUserId != null && checkedUserId.length() > 0)

			{

				sb.append("		WHERE		USR_ID = ?	");

			}

			else {
				if (beforeAuthorization == null || beforeAuthorization.length() == 0) {

					sb.append("		WHERE		ATHRZTN_ID	IS NULL	");
				}

				else {
					sb.append("		WHERE		ATHRZTN_ID	=	?	");
				}
			}

			stmt = conn.prepareStatement(sb.toString());

			if (afterAuthorization == null || afterAuthorization.length() == 0) {

				stmt.setNull(1, Types.VARCHAR);
			} else {
				stmt.setString(1, afterAuthorization);
			}

			if (checkedUserId != null && checkedUserId.length() > 0) {

				stmt.setString(2, checkedUserId);
			}

			else if (beforeAuthorization != null && beforeAuthorization.length() > 0) {

				stmt.setString(2, beforeAuthorization);
			}

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

}
