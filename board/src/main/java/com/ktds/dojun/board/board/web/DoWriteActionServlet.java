package com.ktds.dojun.board.board.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.dojun.board.board.biz.BoardBiz;
import com.ktds.dojun.board.board.biz.BoardBizImpl;
import com.ktds.dojun.board.board.vo.BoardVO;
import com.ktds.dojun.board.user.vo.UsersVO;
import com.ktds.dojun.common.web.MultipartHttpServletRequest;
import com.ktds.dojun.common.web.MultipartHttpServletRequest.MultipartFile;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public DoWriteActionServlet() {
		boardBiz = new BoardBizImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response); // 방법이 역전

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardVO boardVO = new BoardVO();
		MultipartHttpServletRequest multipart = new MultipartHttpServletRequest(request);

		HttpSession session = request.getSession();
		UsersVO user = (UsersVO) session.getAttribute("_USER_");

		// 로그인하고 있는 사용자의, VO정보를 session에서 가져온다.

		String subject = multipart.getParameter("subject");
		String content = multipart.getParameter("content");
		MultipartFile img = multipart.getFile("img");
		String postFileName = "";
		String downloadUrl = "";

		if (content.isEmpty()) {

			content = " ";

		}

		String ip = request.getRemoteAddr();

		System.out.println(subject);
		System.out.println(content);

		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");

		boardVO.setWriter(user.getUserId());
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		boardVO.setImg(img.getFileName());
		boardVO.setIp(ip);

		if (img != null && img.getFileSize() > 0) {

			postFileName = img.getFileName();
			downloadUrl = img.getFileName();

			String path = "C:\\Users\\Admin\\Documents\\sts\\board\\src\\main\\webapp\\img\\";

			path += boardVO.getWriter();
			File dir = new File(path);

			dir.mkdirs();

			img.write(path + File.separator + img.getFileName());
			
		}

		if (boardBiz.writeArticle(boardVO)) {
			response.sendRedirect("/board/list");
		} else {
			response.sendRedirect("/board/write");
		}

	}

}
