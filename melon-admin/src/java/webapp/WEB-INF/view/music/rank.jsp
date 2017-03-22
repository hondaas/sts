<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>


		<tr>

			<th>순위</th>
			<th>곡명</th>
			<th>작곡가</th>
			<th>디렉터</th>
			<th>좋아요 수</th>

		</tr>


		<c:forEach items="${musicList }" var="music" varStatus="index">
		
			<tr>


				<td>${index.index+1 }</td>
				<td>${music.title }</td>
				<td>${music.musician }</td>
				<td>${music.director }</td>
				<td>${music.likeCount }</td>



			</tr>
		</c:forEach>



	</table>









</body>
</html>