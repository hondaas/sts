package com.melon.music.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.common.web.pager.ClassicPageExplorer;
import com.melon.common.web.pager.PageExplorer;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicSearchVO;
import com.melon.music.vo.MusicVO;

public class ViewMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicBiz musicBiz;

	public ViewMusicListServlet() {
		musicBiz = new MusicBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageNo = request.getParameter("pageNo");
		String artistId = request.getParameter("artistId");
		String albumId = request.getParameter("albumId");

		MusicSearchVO musicSearchVO = new MusicSearchVO();
		musicSearchVO.setAlbumId(albumId);
		musicSearchVO.setArtistId(artistId);
		musicSearchVO.getPager().setPageNumber(pageNo);

		PageExplorer pager = new ClassicPageExplorer(musicSearchVO.getPager());

		List<MusicVO> musicList = musicBiz.getAllMusics(musicSearchVO);

		request.setAttribute("musicList", musicList);
		request.setAttribute("musicCount", musicSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "prev", "next", "searchform"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/list.jsp");
		dispatcher.forward(request, response);
	}

}
