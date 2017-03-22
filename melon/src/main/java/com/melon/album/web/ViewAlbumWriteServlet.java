package com.melon.album.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.album.biz.AlbumBiz;
import com.melon.album.biz.AlbumBizImpl;
import com.melon.album.vo.AlbumVO;
import com.melon.common.constants.AuthConst;
import com.melon.common.web.DownloadUtil;
import com.melon.common.web.MultipartHttpServletRequest;
import com.melon.common.web.MultipartHttpServletRequest.MultipartFile;
import com.melon.user.vo.UserVO;

public class ViewAlbumWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlbumBiz albumBiz;

	public ViewAlbumWriteServlet() {
		albumBiz = new AlbumBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER)) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/album/write.jsp");
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

			// enctype="multipart/form-data" 를 해주었다면, 반드시 위의 녀석을 거처서 나와야 한다.

			String artistId = request.getParameter("artistId");
			String albumTitle = multipart.getParameter("albumTitle");
			String releaseDate = multipart.getParameter("releaseDate");
			String publisher = multipart.getParameter("publisher");
			String entertainment = multipart.getParameter("entertainment");
			String genre = multipart.getParameter("genre");
			String postFileName = "";
			String downloadUrl = "";

			System.out.println(artistId);

			MultipartFile post = multipart.getFile("poster");
			if (post != null && post.getFileSize() > 0) {

				postFileName = post.getFileName();
				downloadUrl = post.getFileName();

				File dir = new File("D:\\uploadFiles\\post\\" + artistId + File.separator + albumTitle);
				dir.mkdirs();

				post.write(dir.getAbsolutePath() + File.separator + post.getFileName());
			}

			AlbumVO albumVO = new AlbumVO();
			albumVO.setAlbumTitle(albumTitle);
			albumVO.setArtistId(artistId);
			albumVO.setEntertainment(entertainment);
			albumVO.setGenre(genre);
			albumVO.setPoster(postFileName);
			albumVO.setPublisher(publisher);
			albumVO.setReleaseDate(releaseDate);

			if (albumBiz.addNewAlbum(albumVO)) {
				StringBuffer script = new StringBuffer();
				script.append(" <script type='text/javascript'> ");
				script.append(" opener.location.reload(); ");
				script.append(" self.close(); ");
				script.append(" </script> ");

				PrintWriter writer = response.getWriter();
				writer.write(script.toString());
				writer.flush();
				writer.close();
			} else {
				response.sendRedirect("/melon/album/write");
			}

		}

	}

}
