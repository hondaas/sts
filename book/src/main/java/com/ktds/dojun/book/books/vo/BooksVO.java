package com.ktds.dojun.book.books.vo;

import com.ktds.dojun.book.users.vo.UsersVO;

public class BooksVO {

	private int bookId;
	private String bookName;
	private String bookSubName;
	private String bookIndex;
	private UsersVO usersVO;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookSubName() {
		return bookSubName;
	}

	public void setBookSubName(String bookSubName) {
		this.bookSubName = bookSubName;
	}

	public String getBookIndex() {
		return bookIndex;
	}

	public void setBookIndex(String bookIndex) {
		this.bookIndex = bookIndex;
	}

	public UsersVO getUserVO() {
		if (usersVO == null) {
			usersVO = new UsersVO();
		}
		return usersVO;
	}

	public void setUserVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}

}
