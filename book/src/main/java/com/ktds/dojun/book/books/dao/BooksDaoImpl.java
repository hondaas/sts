package com.ktds.dojun.book.books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.dojun.book.books.vo.BooksVO;

public class BooksDaoImpl implements BooksDao {

	@Override
	public List<BooksVO> getAllBooks() {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();

			query.append(" SELECT BOOK_ID, BOOK_NM, BOOK_SUB, IDX ");
			query.append(" FROM BOOKS ");
			query.append(" ORDER	BY BOOK_ID ");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();
			List<BooksVO> booksList = new ArrayList<BooksVO>();
			BooksVO booksVO = null;

			while (rs.next()) {
				booksVO = new BooksVO();
				booksVO.setBookId(rs.getInt("BOOK_ID"));
				booksVO.setBookName(rs.getString("BOOK_NM"));
				booksVO.setBookSubName(rs.getString("BOOK_SUB"));
				booksVO.setBookIndex(rs.getString("IDX"));

				booksList.add(booksVO);
			}
			return booksList;

		} catch (SQLException e) {
			System.out.println("인스턴스 연결 실패");
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public BooksVO getOneBook(int bookId) {
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
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();

			query.append(" SELECT BOOK_ID, BOOK_NM, BOOK_SUB, IDX ");
			query.append(" FROM BOOKS ");
			query.append(" WHERE	BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setInt(1, bookId);

			rs = stmt.executeQuery();

			BooksVO booksVO = null;

			if (rs.next()) {
				booksVO = new BooksVO();
				booksVO.setBookId(rs.getInt("BOOK_ID"));
				booksVO.setBookName(rs.getString("BOOK_NM"));
				booksVO.setBookSubName(rs.getString("BOOK_SUB"));
				booksVO.setBookIndex(rs.getString("IDX"));
			}
			return booksVO;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		

	}

	@Override
	public int modifyBook(BooksVO booksVO) {
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
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();

			query.append(" UPDATE BOOKS SET ");
			query.append(" BOOK_NM = ? , BOOK_SUB = ? , IDX = ? ");
			query.append(" WHERE	BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, booksVO.getBookName());
			stmt.setString(2, booksVO.getBookSubName());
			stmt.setString(3, booksVO.getBookIndex());
			stmt.setInt(4, booksVO.getBookId());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			
			System.out.println("인스턴스 연결 실패");
			
			return 0;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public int deleteBook(int bookId) {

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
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();

			query.append(" DELETE FROM BOOKS ");
			query.append(" WHERE	BOOK_ID = ? ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setInt(1, bookId);

			return stmt.executeUpdate();

		} catch (SQLException e) {
			
			System.out.println("인스턴스 연결 실패");
			
			return 0;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public int insertBook(BooksVO booksVO) {
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
			conn = DriverManager.getConnection(oracleUrl, "BOOK", "book");

			StringBuffer query = new StringBuffer();

			query.append(" INSERT INTO BOOKS ( ");
			query.append(" BOOK_ID, BOOK_NM , BOOK_SUB , IDX ) ");
			query.append(" VALUES (	BOOK_ID_SEQ.NEXTVAL , ? , ? ,? ) ");

			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, booksVO.getBookName());
			stmt.setString(2, booksVO.getBookSubName());
			stmt.setString(3, booksVO.getBookIndex());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			
			System.out.println("인스턴스 연결 실패");
			
			return 0;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {

					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
