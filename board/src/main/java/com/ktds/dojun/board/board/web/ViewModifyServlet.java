package com.ktds.dojun.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.board.board.biz.BoardBiz;
import com.ktds.dojun.board.board.biz.BoardBizImpl;
import com.ktds.dojun.board.board.vo.BoardVO;

/**
 * Servlet implementation class ViewModifyServlet
 */
public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public ViewModifyServlet() {

		boardBiz = new BoardBizImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String boardIdString = request.getParameter("boardId");
		int boardId = 0;

		try {
			boardId = Integer.parseInt(boardIdString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("존재하지 않는 게시글이거나 잘못된 접근");

		}

		BoardVO boardVO = boardBiz.getOneArticle(boardId);
		String content = boardVO.getContent();
		content = content.replaceAll("<br/>", "\n");
		boardVO.setContent(content);
		
		
		
		
		request.setAttribute("board", boardVO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/modify.jsp");
		dispatcher.forward(request, response);

	}

}
