package com.melon.music.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.melon.common.constants.AuthConst;
import com.melon.music.service.MusicService;
import com.melon.music.service.MusicServiceImpl;
import com.melon.user.vo.UserVO;

public class DoMusicDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicService musicService;

	public DoMusicDeleteServlet() {
		musicService = new MusicServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER)) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {

			String musicId = request.getParameter("musicId");

			if (musicService.removeOneMusic(musicId)) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", "success");

				Gson gson = new Gson();
				String json = gson.toJson(map);

				PrintWriter writer = response.getWriter();
				writer.write(json);
				writer.flush();
				writer.close();

			}

		}

	}
}
