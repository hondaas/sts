package com.ktds.dojun.book.books.dao;

import java.util.List;

import com.ktds.dojun.book.books.vo.BooksVO;

public interface BooksDao {

	public List<BooksVO> getAllBooks();
	
	public BooksVO getOneBook(int bookId);
	
	public int insertBook(BooksVO booksVO);
	
	public int modifyBook(BooksVO booksVO);
	
	public int deleteBook(int bookId);
	
}
