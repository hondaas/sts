package com.melon.music.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.melon.music.service.MusicService;
import com.melon.music.service.MusicServiceImpl;

public class DoAddLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicService musicService;

	public DoAddLikeCountServlet() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String musicId = request.getParameter("musicId");
		System.out.println(musicId);
		boolean isSuccess = musicService.addLikeCount(musicId);
		System.out.println(isSuccess);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", isSuccess ? "success" : "fail");

		System.out.println(map.get("status"));

		Gson gson = new Gson();
		String json = gson.toJson(map);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();

	}

}
