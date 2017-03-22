package com.melon.music.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.common.constants.AuthConst;
import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicVO;
import com.melon.user.vo.UserVO;

public class ViewMusicWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicBiz musicBiz;

	public ViewMusicWriteServlet() {
		musicBiz = new MusicBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER)) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/music/write.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER)) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {
			MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

			String albumId = request.getParameter("albumId");
			String title = multipart.getParameter("title");
			String musician = multipart.getParameter("musician");
			String director = multipart.getParameter("director");
			String lyrics = multipart.getParameter("lyrics");
			MultipartFile mp3File = multipart.getFile("mp3File");

			if (mp3File != null && mp3File.getFileSize() > 0) {

				String path = "C:\\Users\\Admin\\Documents\\sts\\melon\\src\\main\\webapp\\mp3\\";

				path += albumId;
				File dir = new File(path);

				dir.mkdirs();

				mp3File.write(path + File.separator + mp3File.getFileName());
				
				System.out.println(path);

			}

			MusicVO musicVO = new MusicVO();
			musicVO.setAlbumId(albumId);
			musicVO.setDirector(director);
			musicVO.setLikeCount(0);
			musicVO.setLyrics(lyrics);
			musicVO.setMp3File(mp3File.getFileName());
			musicVO.setMusician(musician);
			musicVO.setTitle(title);

			if (musicBiz.addNewMusic(musicVO)) {

				PrintWriter writer = response.getWriter();
				StringBuffer sb = new StringBuffer();
				sb.append(" <script type='text/javascript' > ");
				sb.append(" opener.location.reload(); ");
				sb.append(" self.close(); ");
				sb.append(" </script> ");

				writer.write(sb.toString());
				writer.flush();
				writer.close();

			} else {
				response.sendRedirect("/melon/music/write?albumId=" + albumId);
			}
		}

	}

}
