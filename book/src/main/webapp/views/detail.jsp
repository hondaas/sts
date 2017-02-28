<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${booksVO.bookName}</title>
</head>
<h3>책 상세 정보</h3>
<body>
<hr>
책명 : <span>${booksVO.bookName}</span><br/>
부제 : <span>${booksVO.bookSubName}</span><br/>
목차 : <p>${booksVO.bookIndex}</p><br/>
<hr>
<a href = "/book/books/modify?bookId=${booksVO.bookId}">수정</a> | 
<a href = "/book/books/doDelete?bookId=${booksVO.bookId}">삭제</a><br/>

</body>
</html>