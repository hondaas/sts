<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/common_header.jsp" />

<link rel="stylesheet" type="text/css"
	href="/board/css/detail_layout.css" />
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {

		
	

		});

	
</script>

<div class="detail">
	<h1>${board.subject}</h1>

	<hr />
	<div class="author">
		<span>${board.writer}</span> / <span>${board.writeDate}</span> / <span>${board.likeCount}</span>
	</div>
	<br /> 
	<img class="showimage" src="/melon/img/${board.user.userId}/${board.img}" />
	<p>${board.content}</p>
	<br />

	<hr>
	<div class="control">
		<a href="/board/modify?boardId=${board.boardId}">수정</a> | <a
			href="/board/delete?boardId=${board.boardId}">삭제</a> | <a
			href="/board/list">목록으로 돌아가기</a> | <a href="/board/write">글쓰기</a>
	</div>
</div>
<!--
-->
<div class="login">
	<c:if test="${ empty sessionScope._USER_}">
		<jsp:include page="/view/users/login.jsp" />
	</c:if>
	<c:if test="${ not empty sessionScope._USER_}">
			${sessionScope._USER_.userName }님, 환영합니다.
			</c:if>
</div>

<jsp:include page="/template/common_footer.jsp" />