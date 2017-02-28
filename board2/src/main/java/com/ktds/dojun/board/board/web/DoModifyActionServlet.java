package com.ktds.dojun.board.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.board.biz.BoardBiz;
import com.ktds.dojun.board.biz.BoardBizImpl;
import com.ktds.dojun.board.vo.BoardVO;

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
		
		String writer = request.getParameter("writer");
		String subject =request.getParameter("subject");
		String content =request.getParameter("content");
		String boardIdString =request.getParameter("boardId");
		
		System.out.println(writer);
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
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		boardVO.setBoardId(boardId);
		
		if (boardBiz.updateOneArticle(boardVO)){
			
			response.sendRedirect("/board/list");
			
		}
		
		else{
			response.sendRedirect("/board/modify");
		}
		
	}

	}


