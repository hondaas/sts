package com.ktds.dojun.board.board.web;

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

		HttpSession session = request.getSession();
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		//로그인하고 있는 사용자의, VO정보를 session에서 가져온다.
		

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();

		System.out.println(subject);
		System.out.println(content);

		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");

		boardVO.setWriter(user.getUserId());
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		boardVO.setIp(ip);

		if (boardBiz.writeArticle(boardVO)) {
			// list 페이지로

			response.sendRedirect("/board/list");

		}

		else {
			// write 페이지로
			response.sendRedirect("/board/write");
		}

	}

}
