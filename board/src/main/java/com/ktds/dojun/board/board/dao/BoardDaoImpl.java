package com.ktds.dojun.board.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ktds.dojun.board.board.vo.BoardVO;
import com.ktds.dojun.board.user.vo.UsersVO;
import com.ktds.dojun.dao.support.JDBCDaoSupport;
import com.ktds.dojun.dao.support.QueryHandler;
import com.ktds.dojun.dao.support.annotation.BindData;

public class BoardDaoImpl extends JDBCDaoSupport implements BoardDao {

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
				query.append("                  ) ");
				query.append(" VALUES         (      BOARD_ID_SEQ.NEXTVAL ");
				query.append("                , ? ");
				query.append("                , ? ");
				query.append("                , ? ");
				query.append("                , 0 ");
				query.append("                , SYSDATE ");
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
			}

			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}

	@Override
	public List<BoardVO> selectAllArticle() {
		return selectList(new QueryHandler() {
			@Override
			public String preparedQuery() {
				StringBuffer query = new StringBuffer();
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
				return query.toString();

			}

			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {

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
				query.append( " UPDATE BOARD SET WRITER = ? " );
				query.append( " , SUBJECT = ? , CONTENT = ?  WHERE BOARD_ID = ? " );
				return query.toString();
				
			}
			
			@Override
			public void mappingParameters(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, boardVO.getWriter());
				stmt.setString(2, boardVO.getSubject());
				stmt.setString(3, boardVO.getContent());
				stmt.setInt(4, boardVO.getBoardId());
			}
			
			@Override
			public Object getData(ResultSet rs) {
				return null;
			}
		});
	}
}
