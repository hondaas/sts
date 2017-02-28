<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>책 목록</title>
<style>
table, th, td {
    border: 0.5px solid pink;
}
</style>
</head>

<h1>도서 목록입니다.</h1>

<body>

<table >

	<colgroup>
	
	<col width="100"/>
	<col width="300"/>
	<col width="300"/>

	</colgroup>
	
<tr>
	<th>번호</th>
	<th>도서명</th>
	<th>부제</th>
</tr>
<c:forEach items="${booksList}" var="book">
<tr>
	<td>${book.bookId}</td>
	<td><a href="/book/books/detail?bookId=${book.bookId}">${book.bookName}</a></td>
	<td>${book.bookSubName}</td>
</tr>
</c:forEach>
</table>

<a href="/book/books/write"><input type="submit" value = "도서추가"/></a>
</body>
</html>