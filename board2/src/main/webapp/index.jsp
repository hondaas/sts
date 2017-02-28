<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.ktds.dojun.board.biz.BoardBiz,
    				  com.ktds.dojun.board.biz.BoardBizImpl,
    				  com.ktds.dojun.board.vo.BoardVO,
    				  java.util.List,
    				  java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		//Scriptlet !! 

		BoardBiz boardBiz = new BoardBizImpl();
		List<BoardVO> boardList = boardBiz.getAllArticles();
	%>


	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>좋아요</th>
		</tr>
		<%
			BoardVO board = null;
			for (int i = 0; i < boardList.size(); i++){
				board = boardList.get(i);	
		%>
		<tr>
		
			<td><%=board.getBoardId()%></td>
			<td><%=board.getSubject()%></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getWriteDate()%></td>
			<td><%=board.getLikeCount()%></td>

		</tr>

		<% } %>
	</table>

</body>
</html>