package com.melon.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class MusicDaoImpl implements MusicDao {

	@Override
	public int insertNewMusic(MusicVO musicVO) {

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

			query.append("	INSERT INTO MSC (                                                                      ");
			query.append("						MSC_ID                                                             ");
			query.append("						, ALBM_ID                                                          ");
			query.append("						, TTL                                                              ");
			query.append("						, LK_CNT                                                           ");
			query.append("						, MP3_FL                                                           ");
			query.append("						, MSCN                                                             ");
			query.append("						, DRTR                                                             ");
			query.append("						, LRCS )                                                           ");
			query.append("	VALUES			(                                                                      ");
			query.append("					'MC-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(MSC_ID_SEQ.NEXTVAL, 6 ,'0')      ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("					, ?                                                                    ");
			query.append("			)                                                                              ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicVO.getAlbumId());
			stmt.setString(2, musicVO.getTitle());
			stmt.setInt(3, musicVO.getLikeCount());
			stmt.setString(4, musicVO.getMp3File());
			stmt.setString(5, musicVO.getMusician());
			stmt.setString(6, musicVO.getDirector());
			stmt.setString(7, musicVO.getLyrics());

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
	public int selectAllMusicsCount(MusicSearchVO musicSearchVO) {
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

			query.append("								SELECT 	COUNT(1) CNT                         ");
			query.append("								FROM	ALBM A , ARTST AR , MSC M      ");
			query.append("								WHERE	A.ARTST_ID = AR.ARTST_ID       ");
			query.append("								AND		A.ALBM_ID = M.ALBM_ID          ");
			query.append("								AND		AR.ARTST_ID = ?          ");
			query.append("								AND		A.ALBM_ID = ?          ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicSearchVO.getArtistId());
			stmt.setString(2, musicSearchVO.getAlbumId());

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

	@Override
	public List<MusicVO> selectAllMusics(MusicSearchVO musicSearchVO) {
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

			query.append("	SELECT *                                                           ");
			query.append("	FROM(                                                              ");
			query.append("							SELECT	ROWNUM AS RNUM                     ");
			query.append("									, RST.*                            ");
			query.append("							FROM(                                      ");
			query.append("								SELECT	M.MSC_ID                       ");
			query.append("										, M.ALBM_ID M_ALBM_ID          ");
			query.append("										, M.TTL                        ");
			query.append("										, M.LK_CNT                     ");
			query.append("										, M.MP3_FL                     ");
			query.append("										, M.MSCN                       ");
			query.append("										, M.DRTR                       ");
			query.append("										, M.LRCS                       ");
			query.append("										, A.ALBM_ID  A_ALBM_ID         ");
			query.append("										, A.ARTST_ID A_ARTST_ID        ");
			query.append("										, A.ALBM_TTL                   ");
			query.append("										, TO_CHAR(A.RLS_DT, 'YYYY-MM-DD') RLS_DT                     ");
			query.append("										, A.PBLSHR                     ");
			query.append("										, A.ENTMNT                     ");
			query.append("										, A.GNR                        ");
			query.append("										, A.PST                        ");
			query.append("										, AR.ARTST_ID AR_ARTST_ID      ");
			query.append("										, AR.MBR                       ");
			query.append("										, TO_CHAR(AR.DEBUT_DT, 'YYYY-MM-DD') DEBUT_DT                  ");
			query.append("										, AR.DEBUT_TTL                 ");
			query.append("								FROM	ALBM A , ARTST AR , MSC M      ");
			query.append("								WHERE	A.ARTST_ID = AR.ARTST_ID       ");
			query.append("								AND		A.ALBM_ID = M.ALBM_ID          ");
			query.append("								AND		AR.ARTST_ID = ?          ");
			query.append("								AND		A.ALBM_ID = ?          ");
			query.append("								ORDER	BY M.MSC_ID                    ");
			query.append("								) RST                                  ");
			query.append("							WHERE	ROWNUM <= ?                        ");
			query.append("									)                                  ");
			query.append("	WHERE RNUM >= ?                                                    ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicSearchVO.getArtistId());
			stmt.setString(2, musicSearchVO.getAlbumId());
			stmt.setInt(3, musicSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(4, musicSearchVO.getPager().getStartArticleNumber());

			rs = stmt.executeQuery();

			List<MusicVO> musicList = new ArrayList<MusicVO>();
			MusicVO musicVO = null;

			while (rs.next()) {
				musicVO = new MusicVO();
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("M_ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector("DRTR");
				musicVO.setLyrics(rs.getString("LRCS"));
				musicVO.getAlbumVO().setAlbumId(rs.getString("A_ALBM_ID"));
				musicVO.getAlbumVO().setArtistId(rs.getString("A_ARTST_ID"));
				musicVO.getAlbumVO().setAlbumTitle(rs.getString("ALBM_TTL"));
				musicVO.getAlbumVO().setEntertainment(rs.getString("ENTMNT"));
				musicVO.getAlbumVO().setReleaseDate(rs.getString("RLS_DT"));
				musicVO.getAlbumVO().setPublisher(rs.getString("PBLSHR"));
				musicVO.getAlbumVO().setGenre(rs.getString("GNR"));
				musicVO.getAlbumVO().setPoster(rs.getString("PST"));
				musicVO.getAlbumVO().getArtistVO().setArtistId(rs.getString("AR_ARTST_ID"));
				musicVO.getAlbumVO().getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
				musicVO.getAlbumVO().getArtistVO().setMember(rs.getString("MBR"));
				musicVO.getAlbumVO().getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));

				musicList.add(musicVO);
			}
			return musicList;

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
	public MusicVO selectOneMusic(String musicId) {
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

			query.append("								SELECT	M.MSC_ID                       ");
			query.append("										, M.ALBM_ID M_ALBM_ID          ");
			query.append("										, M.TTL                        ");
			query.append("										, M.LK_CNT                     ");
			query.append("										, M.MP3_FL                     ");
			query.append("										, M.MSCN                       ");
			query.append("										, M.DRTR                       ");
			query.append("										, M.LRCS                       ");
			query.append("										, A.ALBM_ID  A_ALBM_ID         ");
			query.append("										, A.ARTST_ID A_ARTST_ID        ");
			query.append("										, A.ALBM_TTL                   ");
			query.append("										, TO_CHAR(A.RLS_DT, 'YYYY-MM-DD') RLS_DT                     ");
			query.append("										, A.PBLSHR                     ");
			query.append("										, A.ENTMNT                     ");
			query.append("										, A.GNR                        ");
			query.append("										, A.PST                        ");
			query.append("										, AR.ARTST_ID AR_ARTST_ID      ");
			query.append("										, AR.MBR                       ");
			query.append("										, TO_CHAR(AR.DEBUT_DT, 'YYYY-MM-DD') DEBUT_DT                  ");
			query.append("										, AR.DEBUT_TTL                 ");
			query.append("								FROM	ALBM A , ARTST AR , MSC M      ");
			query.append("								WHERE	A.ARTST_ID = AR.ARTST_ID       ");
			query.append("								AND		A.ALBM_ID = M.ALBM_ID          ");
			query.append("								AND		M.MSC_ID = ?          ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicId);

			rs = stmt.executeQuery();

			MusicVO musicVO = null;

			if (rs.next()) {
				musicVO = new MusicVO();
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("M_ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector("DRTR");
				musicVO.setLyrics(rs.getString("LRCS"));
				musicVO.getAlbumVO().setAlbumId(rs.getString("A_ALBM_ID"));
				musicVO.getAlbumVO().setArtistId(rs.getString("A_ARTST_ID"));
				musicVO.getAlbumVO().setAlbumTitle(rs.getString("ALBM_TTL"));
				musicVO.getAlbumVO().setEntertainment(rs.getString("ENTMNT"));
				musicVO.getAlbumVO().setReleaseDate(rs.getString("RLS_DT"));
				musicVO.getAlbumVO().setPublisher(rs.getString("PBLSHR"));
				musicVO.getAlbumVO().setGenre(rs.getString("GNR"));
				musicVO.getAlbumVO().setPoster(rs.getString("PST"));
				musicVO.getAlbumVO().getArtistVO().setArtistId(rs.getString("AR_ARTST_ID"));
				musicVO.getAlbumVO().getArtistVO().setDebutDate(rs.getString("DEBUT_DT"));
				musicVO.getAlbumVO().getArtistVO().setMember(rs.getString("MBR"));
				musicVO.getAlbumVO().getArtistVO().setDebutTitle(rs.getString("DEBUT_TTL"));

			}
			return musicVO;

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
	public int deleteOneMusic(String musicId) {

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

			query.append("	DELETE FROM MSC                                                                     ");
			query.append("	WHERE MSC_ID = ?                                                            ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicId);

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
	public int addLikeCount(String musicId) {
		
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

			query.append("	UPDATE MSC LK_CNT                                                                     ");
			query.append("	SET MSC_ID = MSC_ID + 1                                                            ");
			query.append("	WHERE MSC_ID = ?                                                            ");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, musicId);
			
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

}
