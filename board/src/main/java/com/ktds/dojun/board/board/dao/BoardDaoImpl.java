package com.ktds.dojun.board.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.taglibs.standard.tag.common.sql.DriverTag;

import com.ktds.dojun.board.board.vo.BoardSearchVO;
import com.ktds.dojun.board.board.vo.BoardVO;
import com.ktds.dojun.board.user.vo.UsersVO;
import com.ktds.dojun.dao.support.JDBCDaoSupport;
import com.ktds.dojun.dao.support.QueryHandler;
import com.ktds.dojun.dao.support.annotation.BindData;

public class BoardDaoImpl extends JDBCDaoSupport implements BoardDao {

	@Override
	public int getAllArticlesCount(BoardSearchVO boardSearchVO) {
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

			conn = DriverManager.getConnection(url, "BOARD", "board");

			StringBuffer query = new StringBuffer();

			query.append(" SELECT COUNT(B.BOARD_ID) CNT ");
			query.append(" FROM		BOARD B ");
			query.append(" 			, USRS U ");
			query.append(" WHERE	B.WRITER = U.USR_ID	");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			int aa = 0;
			if (rs.next()) {

				aa = rs.getInt("CNT");

			}

			return aa;

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
				if (rs != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (rs != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}

		}

	}

	@Override
	public int insertArticle(BoardVO boardVO) {
		return update(new QueryHandler() {

			@Override
			public String preparedQuery() {

				StringBuffer query = new StringBuffer();
				query.append(" INSERT INTO BOARD ( ");
				query.append("              BOARD_ID ");
				query.append("              , SUBJECT     ");
				query.append("              , CONTENT ");
				query.append("              , WRITER ");
				query.append("              , LIKE_COUNT ");
				query.append("              , WRITE_DATE ");
				query.append("              , IP ");
				query.append("              , IMG ");
				query.append("                  ) ");
				query.append(" VALUES         (      BOARD_ID_SEQ.NEXTVAL ");
				query.append("                , ? ");
				query.append("                , ? ");
				query.append("                , ? ");
				query.append("                , 0 ");
				query.append("                , SYSDATE ");
				query.append("                , ? ");
				query.append("                , ? ");
				query.append("                ) ");
				return query.toString();

			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				// 쿼리에 파라미터가 존재 (? 가 존재)할 때 사용
				stmt.setString(1, boardVO.getSubject());
				stmt.setString(2, boardVO.getContent());
				stmt.setString(3, boardVO.getWriter());
				stmt.setString(4, boardVO.getIp());
				stmt.setString(5, boardVO.getImg());
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public List<BoardVO> selectAllArticle(BoardSearchVO searchVO) {
		return selectList(new QueryHandler() {
			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();

				query.append(" SELECT * ");
				query.append(" FROM   ( ");
				query.append("            SELECT ROWNUM RNUM     ");
				query.append("            , RST.*     ");
				query.append("            FROM   (     ");

				query.append(" SELECT  B.BOARD_ID ");
				query.append("         , B.SUBJECT ");
				query.append("         , B.WRITER ");
				query.append("         , B.LIKE_COUNT ");
				query.append("         , B.WRITE_DATE ");
				query.append("         , B.IP ");
				query.append("         , U.USR_ID ");
				query.append("         , U.USR_NM ");
				query.append("         , U.JOIN_DTE ");
				query.append(" FROM    BOARD B ");
				query.append("			, USRS U ");
				query.append("	WHERE	B.WRITER = U.USR_ID ");
				query.append(" ORDER	BY  WRITE_DATE DESC ");

				query.append("        			    )  RST  ");
				query.append("            WHERE ROWNUM <= ?   ");
				query.append("				 )   ");
				query.append(" WHERE RNUM >= ?  ");

				return query.toString();

			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, searchVO.getPager().getEndArticleNumber());
				stmt.setInt(2, searchVO.getPager().getStartArticleNumber());
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
				BindData.startBind(rs, boardVO);

				UsersVO usersVO = boardVO.getUser();
				BindData.bindData(rs, usersVO);

				return boardVO;
			}
		});
	}

	@Override
	public BoardVO selectOneArticle(int articleNum) {
		return (BoardVO) selectOne(new QueryHandler() {

			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();

				query.append(" SELECT  B.BOARD_ID ");
				query.append("         , B.SUBJECT ");
				query.append("         , B.WRITER ");
				query.append("         , B.CONTENT ");
				query.append("         , B.LIKE_COUNT ");
				query.append("         , B.WRITE_DATE ");
				query.append("         , B.IP ");
				query.append("         , B.IMG ");
				query.append("         , U.USR_ID ");
				query.append("         , U.USR_NM ");
				query.append("         , U.JOIN_DTE ");
				query.append(" FROM    BOARD B ");
				query.append("			, USRS U ");
				query.append("	WHERE	B.WRITER = U.USR_ID ");
				query.append(" AND   B.BOARD_ID = ? ");

				return query.toString();

			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, articleNum);
			}

			@Override
			public Object getData(ResultSet rs) {
				BoardVO boardVO = new BoardVO();
				BindData.bindData(rs, boardVO);

				UsersVO usersVO = boardVO.getUser();
				BindData.bindData(rs, usersVO);

				return boardVO;

			}
		});
	}

	@Override
	public int deleteOneArticle(int articleNum) {

		return update(new QueryHandler() {

			@Override
			public String preparedQuery() {

				StringBuffer query = new StringBuffer();

				query.append(" DELETE FROM BOARD ");
				query.append(" WHERE BOARD_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {

				stmt.setInt(1, articleNum);

			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public int updateOneArticle(BoardVO boardVO) {

		return update(new QueryHandler() {

			@Override
			public String preparedQuery() {

				StringBuffer query = new StringBuffer();
				query.append(" UPDATE BOARD SET WRITER = ? ");
				query.append(" , SUBJECT = ? , CONTENT = ? , IMG = ? WHERE BOARD_ID = ? ");
				return query.toString();

			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, boardVO.getWriter());
				stmt.setString(2, boardVO.getSubject());
				stmt.setString(3, boardVO.getContent());
				stmt.setString(4, boardVO.getImg());
				stmt.setInt(5, boardVO.getBoardId());
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

}
