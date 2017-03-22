package com.melon.admin.authorization.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.dao.JdbcDaoSupport;

public class AuthorizationDaoImpl implements AuthorizationDao{
	
	private final String ACCOUNT = "MELON";
	private final String PASSWORD = "melon";

	@Override
	public int insertNewAuthorization(AuthorizationVO authorizationVO) {
		
		openJDBC();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url, ACCOUNT, PASSWORD);
			StringBuffer sb = new StringBuffer();
			
			sb.append("	INSERT	INTO	ATHRZTN		(               ");
			sb.append("									ATHRZTN_ID  ");
			sb.append("								,	ATHRZTN_NM  ");
			sb.append("								,	PRNT_ATHRZTN_ID  ");
			sb.append("							)               ");
			sb.append("	VALUES					(               ");
			sb.append("									?   ");
			sb.append("								,	?       ");
			sb.append("								,	?  ");
			sb.append("							)               ");
			
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, authorizationVO.getAuthorizationId());
			stmt.setString(2, authorizationVO.getAuthorizationName());
			stmt.setString(3, authorizationVO.getParentAuthorizationId());

			return stmt.executeUpdate();
			      
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

	@Override
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {
		openJDBC();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");

			StringBuffer sb = new StringBuffer();

			sb.append("		SELECT 		COUNT(1)	CNT	");
			sb.append("		FROM		ATHRZTN	");
	
			
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
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		
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
			sb.append("		      				SELECT 			ATHRZTN_ID	");
			sb.append("		       			       			,	ATHRZTN_NM  	");
			sb.append("		       			      			,	PRNT_ATHRZTN_ID	");
			sb.append("		       				FROM		ATHRZTN	");
			sb.append("		       				ORDER		BY		ATHRZTN_ID	DESC ");
			sb.append("		       							) 	A 	");
			sb.append("		       				WHERE	ROWNUM <= ? ");
			sb.append("		       				) ");
			sb.append("						WHERE	RNUM >= ? ");
	
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, authorizationSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, authorizationSearchVO.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			AuthorizationVO authorization = null;
			List<AuthorizationVO> authorizationList = new ArrayList<AuthorizationVO>();
			
			while(rs.next()) {
				authorization = new AuthorizationVO();
				authorization.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorization.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorization.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				authorizationList.add(authorization);
			}
			
			
			return authorizationList;
			
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

	@Override
	public int updateauthorizationInfo(AuthorizationVO authorizationVO) {
		
		openJDBC();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url, ACCOUNT, PASSWORD);
			StringBuffer sb = new StringBuffer();
			
			sb.append("		UPDATE 		ATHRZTN           ");
			sb.append("		SET								");
			sb.append("					ATHRZTN_NM	=	? 	");
			sb.append("					, PRNT_ATHRZTN_ID =	?	");		
			sb.append("		WHERE		ATHRZTN_ID 	=	? 	");
			
			stmt = conn.prepareStatement(sb.toString());

			stmt.setString(1, authorizationVO.getAuthorizationName());
			stmt.setString(2, authorizationVO.getParentAuthorizationId());
			stmt.setString(3, authorizationVO.getAuthorizationId());
			
			
			return stmt.executeUpdate();
			      
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

	@Override
	public int deleteAuthorizationInfo(String authorizationId) {
		openJDBC();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url, ACCOUNT, PASSWORD);
			StringBuffer sb = new StringBuffer();
			
			sb.append(" DELETE  		");
			sb.append(" FROM	ATHRZTN ");
			sb.append("	WHERE 	ATHRZTN_ID 	=	?	");
			
			
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, authorizationId);

			return stmt.executeUpdate();
			      
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
	
	public void openJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public String generateNewAuthorizationId() {
		
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer sb = new StringBuffer();
				sb.append("		SELECT	'AT-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ	");
				sb.append("		FROM	DUAL	");
				return sb.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException{
				
			}

			@Override
			public Object bindData(ResultSet rs) throws SQLException {
				return rs.getString("SEQ");	
			}};
		
			return (String) dao.selectOne();
	}

	
	

}
