package com.ktds.dojun.book.books.biz;

import java.util.List;

import com.ktds.dojun.book.books.dao.BooksDao;
import com.ktds.dojun.book.books.dao.BooksDaoImpl;
import com.ktds.dojun.book.books.vo.BooksVO;

public class BooksBizImpl implements BooksBiz {

	private BooksDao booksDao;

	public BooksBizImpl() {

		booksDao = new BooksDaoImpl();

	}

	@Override
	public List<BooksVO> getAllBooks() {
		return booksDao.getAllBooks();
	}

	@Override
	public BooksVO getOneBook(int bookId) {
		return booksDao.getOneBook(bookId);
	}

	@Override
	public boolean modifyBook(BooksVO booksVO) {
		return booksDao.modifyBook(booksVO) > 0;
	}

	@Override
	public boolean deleteBook(int bookId) {
		return booksDao.deleteBook(bookId) > 0;
	}

	@Override
	public boolean insertBook(BooksVO booksVO) {
		return booksDao.insertBook(booksVO)>0;
	}

}
