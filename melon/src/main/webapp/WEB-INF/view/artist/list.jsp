<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("input[type=button]").click(function() {

			var writeDiv = $("<div id='writeDiv'></div>");
			writeDiv.css({

				position : 'absolute',
				top : '20px',
				left : '20px',
				border : '1px solid #333',
				padding : '15px',
				"z-index" : 3,
				"background-color" : '#FFFFFF'
			// write 페이지 css를 덮어쓴다.
			// 하이픈이 있으면 감싼다.

			});

			writeDiv.load("/melon/artist/write");

			$(this).before(writeDiv);

			writeDiv.mouseleave(function() {
				var q = true;

				if (q == true) {

				}

			})

			//jsp는 서버사이드 랭귀지, 자바스크립트는 클라이언트 사이드 랭귀지

		});

	});
</script>
</head>
<body>

	<input type="button" value="아티스트 등록" />

	<p>${artistCount}명의아티스트가검색되었습니다.</p>
	<table>

		<tr>

			<th>번호</th>
			<th>아티스트 명</th>
			<th>데뷔 일자</th>

		</tr>

		<c:forEach items="${artistList}" var="artist">
			<tr>
				<td><fmt:parseNumber> ${fn:split(artist.artistId, '-')[2]}
					</fmt:parseNumber></td>

				<td><a href="/melon/album/list?artistId=${artist.artistId}">${artist.member}</a></td>
				<td>${artist.debutDate}</td>
			</tr>

		</c:forEach>

	</table>
	<div>
		<form id="searchform">${pager}</form>
	</div>

</body>
</html>