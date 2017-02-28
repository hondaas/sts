package com.ktds.dojun.book.books.biz;

import java.util.List;

import com.ktds.dojun.book.books.vo.BooksVO;

public interface BooksBiz {

	public List<BooksVO> getAllBooks();
	
	public BooksVO getOneBook(int bookId);
	
	public boolean modifyBook(BooksVO booksVO);
	
	public boolean deleteBook(int bookId);

	public boolean insertBook(BooksVO booksVO);
}
