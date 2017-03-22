package com.melon.admin.music.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.music.service.MusicService;
import com.melon.admin.music.service.MusicServiceImpl;
import com.melon.admin.music.vo.MusicVO;

public class ViewMusicRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicService musicService;

	public ViewMusicRankServlet() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MusicVO> musicList = musicService.getMusicsForAdmin();
		System.out.println(musicList.size());

		request.setAttribute("musicList", musicList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/rank.jsp");
		dispatcher.forward(request, response);

	}

}
