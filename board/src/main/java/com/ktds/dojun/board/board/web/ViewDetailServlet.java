package com.ktds.dojun.board.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.dojun.board.board.biz.BoardBiz;
import com.ktds.dojun.board.board.biz.BoardBizImpl;
import com.ktds.dojun.board.board.vo.BoardVO;
import com.ktds.dojun.board.user.vo.UsersVO;

public class ViewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;

	public ViewDetailServlet() {
		boardBiz = new BoardBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String boardIdString = request.getParameter("boardId");
		// getParameter는 String으로 밖에 받지 못한다.

		int boardId = 0; // try 밖에서도 써야 하니까 밖에서 선언.

		try {

			boardId = Integer.parseInt(boardIdString);

			// 교과서에 봤던 것 처럼, int 값이 될 수 없는 String 값이 들어왔을때 에러남.
			// try catch 해줘야 함

		} catch (NumberFormatException e) {
			throw new RuntimeException("존재하wl 않는 게시물 입니다.");
			// 웹에서 예외처리는 거의 이렇게 함.
		}

		BoardVO board = boardBiz.getOneArticle(boardId);

		request.setAttribute("board", board);

		
		
		
		HttpSession session = request.getSession();
		UsersVO userVO = (UsersVO) session.getAttribute("_USER_");
		
		
		boolean isWriter =  userVO.getUserId().equals(board.getWriter()) ;

		
		request.setAttribute("isWriter", isWriter);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/detail.jsp");
		dispatcher.forward(request, response);

	}

}
