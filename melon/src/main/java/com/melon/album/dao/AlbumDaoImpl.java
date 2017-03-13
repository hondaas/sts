package com.melon.album.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.album.vo.AlbumSearchVO;
import com.melon.album.vo.AlbumVO;

public class AlbumDaoImpl implements AlbumDao{

	@Override
	public int insertNewAlbum(AlbumVO albumVO) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException (e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
		query.append( " INSERT INTO ALBM ( ALBM_ID , ARTST_ID , ALBM_TTL, RLS_DT, PBLSHR , ENTMNT , GNR , PST )             	 ");
		query.append( " VALUES			 ( 'AL-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD( ALBM_ID_SEQ.NEXTVAL, 6, '0')  	");
		query.append( " 					, ?                                                                        ");
		query.append( " 					, ?                                                                        ");
		query.append( " 					, TO_DATE(?, 'YYYY-MM-DD')                                                 ");
		query.append( " 					, ?                                                                       ");
		query.append( " 					, ?                                                                       ");
		query.append( " 					, ?                                                                       ");
		query.append( " 					, ? )                                                                     ");
			
		
		stmt = conn.prepareStatement(query.toString());
		
		stmt.setString(1, albumVO.getArtistId());
		stmt.setString(2, albumVO.getAlbumTitle());
		stmt.setString(3, albumVO.getReleaseDate());
		stmt.setString(4, albumVO.getPublisher());
		stmt.setString(5, albumVO.getEntertainment());
		stmt.setString(6, albumVO.getGenre());
		stmt.setString(7, albumVO.getPoster());
		
		return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage(),e );
		} finally {
			
			try {if(stmt != null){
				stmt.close();}
			} catch (SQLException e) {
			}
			try {if(conn != null){
				conn.close();}
			} catch (SQLException e) {
			}
			
		}

	}

	@Override
	public int selectAllAlbumsCount(AlbumSearchVO albumSearchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException (e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
			
			
		query.append("	SELECT	COUNT(1) CNT        ");
		query.append("	FROM	ALBM AL"
				+ "				,ARTST AR "
				+ "    WHERE AL.ARTST_ID = AR.ARTST_ID "
				+ "		AND	AR.ARTST_ID = ?	             ");

			
		stmt = conn.prepareStatement(query.toString());
		
		stmt.setString(1, albumSearchVO.getArtistId());
		
		rs = stmt.executeQuery();

		
		if(rs.next()){
			return rs.getInt("CNT");
			
		}
		
		return 0;
		
		
		
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage(),e );
		} finally {
			
			try {if(stmt != null){
				stmt.close();}
			} catch (SQLException e) {
			}
			try {if(conn != null){
				conn.close();}
			} catch (SQLException e) {
			}
			
		}	}

	@Override
	public List<AlbumVO> selectAllAlbums(AlbumSearchVO albumSearchVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException (e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
			
			
		query.append("	SELECT	*                                                                        ");
		query.append("	FROM							(                                                ");
		query.append("									SELECT	ROWNUM AS RNUM                           ");
		query.append("											, RST.*                                  ");
		query.append("									FROM	(                                        ");
		query.append("												SELECT	A.ALBM_ID                    ");
		query.append("														, A.ARTST_ID A_ARTST_ID      ");
		query.append("														, A.ALBM_TTL      ");
		query.append("														, TO_CHAR(A.RLS_DT, 'YYYY-MM-DD') RLS_DT  ");
		query.append("														, A.PBLSHR                  ");
		query.append("														, A.ENTMNT                   ");
		query.append("														, A.GNR                      ");
		query.append("														, A.PST                      ");
		query.append("														, AR.ARTST_ID AR_ARTST_ID    ");
		query.append("														, AR.MBR                     ");
		query.append("														, AR.DEBUT_DT                ");
		query.append("														, AR.DEBUT_TTL               ");
		query.append("												FROM	ALBM A , ARTST AR              ");
		query.append("												WHERE	A.ARTST_ID = AR.ARTST_ID     ");
		query.append("												AND 	AR.ARTST_ID = ?    ");
		query.append("												ORDER	BY A.RLS_DT DESC                 ");
		query.append("												) RST                                ");
		query.append("									WHERE	ROWNUM <= ?                              ");
		query.append("								)                                                    ");
		query.append("	WHERE	RNUM	>= ?                                                             ");
			
		
		stmt = conn.prepareStatement(query.toString());
		
		stmt.setString(1, albumSearchVO.getArtistId());
		stmt.setInt(2, albumSearchVO.getPager().getEndArticleNumber());
		stmt.setInt(3, albumSearchVO.getPager().getStartArticleNumber());
		
		rs = stmt.executeQuery();
			
		List<AlbumVO> albumList = new ArrayList<AlbumVO>();
		AlbumVO albumVO = null;
		
		while(rs.next()){
			
			albumVO = new AlbumVO();
			
			albumVO.setAlbumId(rs.getString("ALBM_ID"));
			albumVO.setArtistId(rs.getString("A_ARTST_ID"));
			albumVO.setAlbumTitle(rs.getString("ALBM_TTL"));
			albumVO.setEntertainment(rs.getString("ENTMNT"));
			albumVO.setReleaseDate(rs.getString("RLS_DT"));
			albumVO.setPublisher(rs.getString("PBLSHR"));
			albumVO.setGenre(rs.getString("GNR"));
			albumVO.setPoster(rs.getString("PST"));
			albumVO.getArtistVO().setArtistId(rs.getString("AR_ARTST_ID"));
			albumVO.getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
			albumVO.getArtistVO().setMember(rs.getString("MBR"));
			albumVO.getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));
			
			
			System.out.println(albumVO.getAlbumId());
			
			albumList.add(albumVO);

		}
		
		return albumList;
		
		
		
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage(),e );
		} finally {
			try {if(rs != null){
				rs.close();}
			} catch (SQLException e) {
			}
			try {if(stmt != null){
				stmt.close();}
			} catch (SQLException e) {
			}
			try {if(conn != null){
				conn.close();}
			} catch (SQLException e) {
			}
			
		}		}

	@Override
	public AlbumVO selectOneAlbum(String albumId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException (e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
			
			
		query.append("												SELECT	A.ALBM_ID                    ");
		query.append("														, A.ARTST_ID A_ARTST_ID      ");
		query.append("														, A.ALBM_TTL      ");
		query.append("														, A.RLS_DT                   ");
		query.append("														, A.PBLSHR                  ");
		query.append("														, A.ENTMNT                   ");
		query.append("														, A.GNR                      ");
		query.append("														, A.PST                      ");
		query.append("														, AR.ARTST_ID AR_ARTST_ID    ");
		query.append("														, AR.MBR                     ");
		query.append("														, AR.DEBUT_DT                ");
		query.append("														, AR.DEBUT_TTL               ");
		query.append("												FROM	ALBM A , ARTST AR               ");
		query.append("												WHERE	A.ARTST_ID = AR.ARTST_ID     ");
		query.append("												AND		A.ALBM_ID = ?     ");
		
		stmt = conn.prepareStatement(query.toString());
		
		stmt.setString(1, albumId);
		
		rs = stmt.executeQuery();
		
		AlbumVO albumVO = null;
		
		if(rs.next()){
			
			albumVO = new AlbumVO();
			
			albumVO.setAlbumId(rs.getString("ALBM_ID"));
			albumVO.setArtistId(rs.getString("A_ARTST_ID"));
			albumVO.setAlbumTitle(rs.getString("ALBM_TTL"));
			albumVO.setEntertainment(rs.getString("ENTMNT"));
			albumVO.setReleaseDate(rs.getString("RLS_DT"));
			albumVO.setPublisher(rs.getString("PBLSHR"));
			albumVO.setGenre(rs.getString("GNR"));
			albumVO.setPoster(rs.getString("PST"));
			albumVO.getArtistVO().setArtistId(rs.getString("AR_ARTST_ID"));
			albumVO.getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
			albumVO.getArtistVO().setMember(rs.getString("MBR"));
			albumVO.getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));
			
		
		}
		
		return albumVO;
		
		
		
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage(),e );
		} finally {
			try {if(rs != null){
				rs.close();}
			} catch (SQLException e) {
			}
			try {if(stmt != null){
				stmt.close();}
			} catch (SQLException e) {
			}
			try {if(conn != null){
				conn.close();}
			} catch (SQLException e) {
			}
			
		}		
	}

	@Override
	public int deleteOneAlbum(String albumId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException (e.getMessage(), e);
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			conn = DriverManager.getConnection(url, "MELON", "melon");
			
			StringBuffer query = new StringBuffer();
			
		query.append( " DELETE FROM ALBM  ");
		query.append( " WHERE  ALBM_ID = ?  ");
			
		
		stmt = conn.prepareStatement(query.toString());
		
		stmt.setString(1, albumId);
		
		return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage(),e );
		} finally {
			
			try {if(stmt != null){
				stmt.close();}
			} catch (SQLException e) {
			}
			try {if(conn != null){
				conn.close();}
			} catch (SQLException e) {
			}
			
		}
	}

}
