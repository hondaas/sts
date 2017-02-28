package com.ktds.dojun.book.books.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.book.books.biz.BooksBiz;
import com.ktds.dojun.book.books.biz.BooksBizImpl;
import com.ktds.dojun.book.books.vo.BooksVO;

public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BooksBiz booksBiz;
	
    public ViewListServlet() {
    	booksBiz = new BooksBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	List<BooksVO> booksList = booksBiz.getAllBooks();
	
	request.setAttribute("booksList", booksList);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/views/list.jsp");
	dispatcher.forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
