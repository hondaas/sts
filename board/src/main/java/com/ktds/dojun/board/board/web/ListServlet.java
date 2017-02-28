package com.ktds.dojun.board.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.dojun.board.board.biz.BoardBiz;
import com.ktds.dojun.board.board.biz.BoardBizImpl;
import com.ktds.dojun.board.board.vo.BoardVO;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardBiz boardBiz;
	
	
	public ListServlet() {
		// super();
		boardBiz = new BoardBizImpl();
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// session.invalidate();
		
		List<BoardVO> artcleList = boardBiz.getAllArticles();
		BoardVO boardVO = new BoardVO();
		
		request.setAttribute("articleList", artcleList);
		System.out.println(boardVO.getUser().getUserName());
		
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/board/list.jsp"); ///view/board/list.jsp
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
