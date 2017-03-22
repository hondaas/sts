package com.melon.artist.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.artist.biz.ArtistBiz;
import com.melon.artist.biz.ArtistBizImpl;
import com.melon.artist.vo.ArtistVO;
import com.melon.common.constants.AuthConst;
import com.melon.user.vo.UserVO;

public class ViewArtistWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArtistBiz artistBiz;

	public ViewArtistWriteServlet() {

		artistBiz = new ArtistBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");

		if (userVO.getAuthorizationId().equals(AuthConst.NORMAL_USER)) {
			response.sendError(404); // 가장 현명한 방법 (해커가 예측못하게)

		} else if (userVO.getAuthorizationId().equals(AuthConst.OPERATOR_USER)
				|| userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/artist/write.jsp");
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
			String artistName = request.getParameter("artistName");
			String debutDate = request.getParameter("debutDate");
			String debutTitle = request.getParameter("debutTitle");

			ArtistVO artistVO = new ArtistVO();
			artistVO.setMember(artistName);
			artistVO.setDebutDate(debutDate);
			artistVO.setDebutTitle(debutTitle);

			if (artistBiz.addNewArtist(artistVO)) {
				PrintWriter out = response.getWriter();
				out.write("OK");
				out.flush();
				out.close();
			} else {
				PrintWriter out = response.getWriter();
				out.write("FAIL");
				out.flush();
				out.close();
			}
		}

	}

}
