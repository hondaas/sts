<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(
					function() {
						$("input[type=button]").click(
										function() {

											window.open("/melon/album/write?artistId=${param.artistId}",
															"앨범 등록",
															"resizable=no, scrollbars=yes, toolbar=no,width=300px , height=500px, menubar=no");

										});
						
						$(".showimage").css({
							
							width : '200px',
							height : '200px'
							
						})

					});
</script>

</head>
<body>

	<input type="button" value="앨범 등록" />
	<br />

	<table>

		<tr>
			<c:forEach items="${albumList}" var="album" varStatus="index">
 			
 	
 		<!-- varStatus를 통해 몇번째 반복인지 알 수 있다. -->
				
				<td>${index.index+1}<br />
					<div>
					<a href="/melon/music/list?albumId=${album.albumId}&artistId=${album.artistId}">
						<img class="showimage" src="/melon/album/post?albumId=${album.albumId}" /></a>  <br /> ${album.albumTitle}<br />
						${album.artistVO.member}
					</div>
				</td>

				<c:if test="${(index.index + 1) % 5 == 0 }">
		</tr>
		<tr>
			</c:if> 
			</c:forEach>
		</tr>

	</table>
	<div>
		<form id="searchform">${pager}</form>
	</div>

</body>
</html>