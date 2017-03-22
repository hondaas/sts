package com.melon.admin.music.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melon.admin.music.vo.MusicVO;

public class MusicDaoImpl implements MusicDao {

	@Override
	public List<MusicVO> getMusicsForAdmin() {
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

			query.append("				SELECT	ROWNUM AS RNUM                     ");
			query.append("						, RST.*                            ");
			query.append("							FROM(                                      ");
			query.append("								SELECT	MSC_ID                       ");
			query.append("										, ALBM_ID        ");
			query.append("										, TTL                        ");
			query.append("										, LK_CNT                     ");
			query.append("										, MP3_FL                     ");
			query.append("										, MSCN                       ");
			query.append("										, DRTR                       ");
			query.append("										, LRCS                       ");
			query.append("								FROM	MSC      ");
			query.append("								ORDER	BY LK_CNT DESC                   ");
			query.append("								) RST                                  ");
			query.append("							WHERE	ROWNUM <= 10                        ");

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			List<MusicVO> musicList = new ArrayList<MusicVO>();
			MusicVO musicVO = null;

			while (rs.next()) {
				musicVO = new MusicVO();
				musicVO.setMusicId(rs.getString("MSC_ID"));
				musicVO.setAlbumId(rs.getString("ALBM_ID"));
				musicVO.setTitle(rs.getString("TTL"));
				musicVO.setLikeCount(rs.getInt("LK_CNT"));
				musicVO.setMp3File(rs.getString("MP3_FL"));
				musicVO.setMusician(rs.getString("MSCN"));
				musicVO.setDirector(rs.getString("DRTR"));
				musicVO.setLyrics(rs.getString("LRCS"));
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

}
