<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 추가</title>
</head>

<h3>도서 추가</h3>
<body>

<hr/>

<form method="Post" action="/book/books/doWrite">
<input type="text" name = "bookName" placeholder = "책 제목을 입력하세요"/><br/>
<input type="text" name = "bookSubName" placeholder = "부제목을 입력하세요"/><br/>
<textarea name = "bookIndex" placeholder = "목차를 입력하세요"></textarea><br/>
<input type="submit" value="추가하기">
</form>



</body>
</html>