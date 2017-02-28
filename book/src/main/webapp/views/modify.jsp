<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 수정</title>
<head />
<h3>도서 수정</h3>
<body>

	<hr />

	<form method="Post" action="/book/books/doModify">
		<input type="hidden" name="bookId" value="${booksVO.bookId}">
		<input type="text" name="bookName" value="${booksVO.bookName}" /><br />
		<input type="text" name="bookSubName" value="${booksVO.bookSubName}" /><br />
		<textarea name="bookIndex">${booksVO.bookIndex}</textarea>
		<br /> <input type="submit" value="수정하기">
	</form>

</body>
</html>