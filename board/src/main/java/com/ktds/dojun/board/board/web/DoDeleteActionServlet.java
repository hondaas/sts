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

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public DoDeleteActionServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response); // 방법이 역전

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String boardIdString = request.getParameter("boardId");

		int boardId = 0; // try 밖에서도 써야 하니까 밖에서 선언.

		boardId = Integer.parseInt(boardIdString);

		HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_USER_");
		BoardVO boardVO = boardBiz.getOneArticle(boardId);

		if (userVO.getUserId().equals(boardVO.getWriter())) {

			if (boardBiz.deleteOneArticle(boardId)) {

				response.sendRedirect("/board/list");

			}

			else {

				response.sendRedirect("/board/list");
			}

		}else{response.sendError(404);}

	}

}
