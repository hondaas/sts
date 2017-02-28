package com.ktds.dojun.book.books.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.book.books.biz.BooksBiz;
import com.ktds.dojun.book.books.biz.BooksBizImpl;
import com.ktds.dojun.book.books.vo.BooksVO;

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BooksBiz booksBiz;

	public DoModifyActionServlet() {
		booksBiz = new BooksBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookIdStirng = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String bookSubName = request.getParameter("bookSubName");
		String bookIndex = request.getParameter("bookIndex");

		
		bookIndex = bookIndex.replaceAll("\n", "<br/>");
		bookIndex = bookIndex.replaceAll("\r", "");
		
		int bookId = 0;
		try {
			bookId = Integer.parseInt(bookIdStirng);
		} catch (NumberFormatException e) {
			throw new RuntimeException("존재하지 않음");
		}
		BooksVO booksVO = new BooksVO();
		booksVO.setBookId(bookId);
		booksVO.setBookName(bookName);
		booksVO.setBookSubName(bookSubName);
		booksVO.setBookIndex(bookIndex);

		if (booksBiz.modifyBook(booksVO)) {

			response.sendRedirect("/book/books/list");

		}

		else {

			response.sendRedirect("/book/books/modify");

		}

	}
}
