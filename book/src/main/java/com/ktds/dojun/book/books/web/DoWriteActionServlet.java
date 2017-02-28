package com.ktds.dojun.book.books.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.book.books.biz.BooksBiz;
import com.ktds.dojun.book.books.biz.BooksBizImpl;
import com.ktds.dojun.book.books.vo.BooksVO;

public class DoWriteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BooksBiz booksBiz;
	
	public DoWriteActionServlet() {
		booksBiz = new BooksBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookName = request.getParameter("bookName");
		String bookSubName = request.getParameter("bookSubName");
		String bookIndex = request.getParameter("bookIndex");

		bookIndex = bookIndex.replaceAll("\n", "<br/>");
		bookIndex = bookIndex.replaceAll("\r", "");
		
		
		BooksVO booksVO = null;

		booksVO = new BooksVO();

		booksVO.setBookName(bookName);
		booksVO.setBookSubName(bookSubName);
		booksVO.setBookIndex(bookIndex);
		
		
		if(booksBiz.insertBook(booksVO)){
			
			response.sendRedirect("/book/books/list");
			
		}
		else{
			
			response.sendRedirect("/book/books/write");
			
		}
		

	}

}
