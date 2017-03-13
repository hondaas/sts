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

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardBiz boardBiz;
	
    public DoModifyActionServlet() {
    	boardBiz = new BoardBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		String subject =request.getParameter("subject");
		String content =request.getParameter("content");
		String boardIdString =request.getParameter("boardId");
		
		System.out.println(subject);
		System.out.println(content);
		
		int boardId = 0;
		try {

			boardId = Integer.parseInt(boardIdString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("존재하지 않는 게시물 입니다.");
		}
		
		
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");
		
		
		BoardVO boardVO = new BoardVO();
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		boardVO.setBoardId(boardId);
		boardVO.setWriter(user.getUserId());
		
		if (boardBiz.updateOneArticle(boardVO)){
			
			response.sendRedirect("/board/list");
			
		}
		
		else{
			response.sendRedirect("/board/modify");
		}
		
	}

	}


