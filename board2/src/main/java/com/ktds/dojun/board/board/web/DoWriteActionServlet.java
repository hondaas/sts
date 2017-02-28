package com.ktds.dojun.board.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.board.biz.BoardBiz;
import com.ktds.dojun.board.biz.BoardBizImpl;
import com.ktds.dojun.board.vo.BoardVO;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String writer = request.getParameter("writer");
		String subject =request.getParameter("subject");
		String content =request.getParameter("content");
		
		System.out.println(writer);
		System.out.println(subject);
		System.out.println(content);
		
		content = content.replaceAll("\n", "<br/>");
		content = content.replaceAll("\r", "");
		
		
		
		BoardVO boardVO = new BoardVO();
		boardVO.setWriter(writer);
		boardVO.setSubject(subject);
		boardVO.setContent(content);
		
		if (boardBiz.writeArticle(boardVO)){
			//list 페이지로
			
			response.sendRedirect("/board/list");
			
			
		}
		
		else{
			//write 페이지로
			response.sendRedirect("/board/write");
		}
		
		

		
	}

}
