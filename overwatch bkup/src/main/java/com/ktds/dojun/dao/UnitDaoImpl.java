package com.ktds.dojun.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ktds.dojun.vo.UnitVO;

public class UnitDaoImpl implements UnitDao {

	@Override
	public int addUnit(UnitVO unitVO) {

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

			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO OVERWATCH ( 	");
			query.append("        UNIT_ID, 			");
			query.append("        UNIT_NAME,  		");
			query.append("        UNIT_DAMAGE,  	");
			query.append("        UNIT_TYPE )		");
			query.append(" VALUES( 					");
			query.append("        UNIT_ID_SEQ.NEXTVAL 		");
			query.append("        , ? 				");
			query.append("        , ? 				");
			query.append("        , ? 				");
			query.append(" ) 						");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, unitVO.getUnitName());
			stmt.setInt(2, unitVO.getUnitDamage());
			stmt.setString(3, unitVO.getUnitType());

			List<UnitVO> unitList = new ArrayList<UnitVO>();

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
	public List<UnitVO> getAllUnits() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println("오라클 드라이버 로딩 실패");

			return null;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Blob img;
		byte[] imgData = null;

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");

			StringBuffer query = new StringBuffer();
			query.append(" SELECT  UNIT_ID, 		");
			query.append("        UNIT_NAME,  		");
			query.append("        UNIT_DAMAGE,  	");
			query.append("        UNIT_TYPE 		");
			//query.append("        UNIT_IMG 		");
			query.append(" FROM		OVERWATCH		");
			query.append(" ORDER	BY  UNIT_ID ");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			List<UnitVO> unitList = new ArrayList<UnitVO>();
			UnitVO unitVO = null;

			while (rs.next()) {
				unitVO = new UnitVO();
				unitVO.setUnitId(rs.getInt("UNIT_ID"));
				unitVO.setUnitName(rs.getString("UNIT_NAME"));
				unitVO.setUnitDamage(rs.getInt("UNIT_DAMAGE"));
				unitVO.setUnitType(rs.getString("UNIT_TYPE"));
				//unitVO.setUnitImg(rs.getBlob("UNIT_IMG").getBytes("UNIT_IMG", (int) rs.getBlob("UNIT_IMG").length()));
				
				unitList.add(unitVO);

			}
			return unitList;

		} catch (SQLException e) {
			System.out.println("인스턴스 연결 실패.22");
			return null;

		}

		finally {

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
	public int modifyUnit(UnitVO unitVO) {

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

			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");

			StringBuffer query = new StringBuffer();
			query.append(" UPDATE OVERWATCH SET	");
			query.append("        UNIT_NAME = ?,  		");
			query.append("        UNIT_DAMAGE = ?,  	");
			query.append("        UNIT_TYPE = ?		");
			query.append(" WHERE  UNIT_ID = ?				");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, unitVO.getUnitName());
			stmt.setInt(2, unitVO.getUnitDamage());
			stmt.setString(3, unitVO.getUnitType());
			stmt.setInt(4, unitVO.getUnitId());

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
	public int deleleUnit(int unitId) {
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

			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");

			StringBuffer query = new StringBuffer();
			query.append(" DELETE FORM OVERWATCH 	");
			query.append(" WHERE  UNIT_ID = ?		");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, unitId);

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
	public UnitVO getOneUnit(int unitId) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println("오라클 드라이버 로딩 실패");

			return null;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		try {

			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  UNIT_ID, 		");
			query.append("        UNIT_NAME,  		");
			query.append("        UNIT_DAMAGE,  	");
			query.append("        UNIT_TYPE 		");
			query.append(" FROM		OVERWATCH		");
			query.append(" WHERE UNIT_ID = ? ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, unitId);

			rs = stmt.executeQuery();

			UnitVO unitVO = new UnitVO();
			while (rs.next()) {
				unitVO.setUnitId(rs.getInt("UNIT_ID"));
				unitVO.setUnitName(rs.getString("UNIT_NAME"));
				unitVO.setUnitDamage(rs.getInt("UNIT_DAMAGE"));
				unitVO.setUnitType(rs.getString("UNIT_TYPE"));

			}
			return unitVO;

		} catch (SQLException e) {
			System.out.println("인스턴스 연결 실패.22");
			return null;

		}

		finally {

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
	public byte[] getImage(int unitId) {
		String req = "";
		Blob img;
		byte[] imgData = null;
		int iNumPhoto = 0;

		Connection conn = null;

		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";

		try {

			conn = DriverManager.getConnection(oracleUrl, "BLIZZARD", "blizzard");

			PreparedStatement stmt = conn.prepareStatement(oracleUrl);

			// Query
			req = "Select UNIT_IMG From OVERWATCH Where UNIT_ID = ? ";

			stmt.setInt(1, unitId);

			ResultSet rset = stmt.executeQuery(req);

			while (rset.next()) {
				img = rset.getBlob(1);
				imgData = img.getBytes(1, (int) img.length());
			}

			rset.close();
			stmt.close();

			return imgData;

		} catch (SQLException e) {
			System.out.println("인스턴스 연결 실패.22");
			return null;
		}

	}
}
