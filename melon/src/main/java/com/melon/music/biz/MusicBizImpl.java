package com.melon.music.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.common.web.pager.Pager;
import com.melon.music.dao.MusicDao;
import com.melon.music.dao.MusicDaoImpl;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class MusicBizImpl implements MusicBiz {

	private MusicDao musicDao;

	public MusicBizImpl() {

		musicDao = new MusicDaoImpl();

	}

	@Override
	public boolean addNewMusic(MusicVO musicVO) {
		return musicDao.insertNewMusic(musicVO) > 0;
	}


	@Override
	public List<MusicVO> getAllMusics(MusicSearchVO musicSearchVO) {

		int musicsCount = musicDao.selectAllMusicsCount(musicSearchVO);

		Pager pager = musicSearchVO.getPager();
		pager.setTotalArticleCount(musicsCount);

		if (musicsCount == 0) {
			return new ArrayList<MusicVO>();

		}

		return musicDao.selectAllMusics(musicSearchVO);
	}

	@Override
	public MusicVO getOneMusic(String musicId) {
		return musicDao.selectOneMusic(musicId);
	}

	@Override
	public boolean removeOneMusic(String musicId) {
		return musicDao.deleteOneMusic(musicId) > 0;
	}

	@Override
	public boolean addLikeCount(String musicId) {
		return musicDao.addLikeCount(musicId)>0;
	}

}
