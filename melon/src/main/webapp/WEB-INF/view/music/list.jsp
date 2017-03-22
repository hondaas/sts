<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {
						<c:if test="${isAdminUser || isOperatorUser}">
						
						$("#add")
						.click(
								function() {
									window
											.open(
													"/melon/music/write?albumId=${param.albumId}",
													"음악 등록",
													"resizable=no, scrollbars=yes, toolbar=no,width=300px , height=500px, menubar=no")

								});
						
						
						$(".delete")
						.click(
								function() {
								
									
									$.post("/melon/music/delete" , {
										
										"musicId" : $(this).data("musicidd")
										
									} , function(response){
										
										var jsonObj = JSON.parse(response);
										
										if(jsonObj.status=="success"){
											
											location.reload();
											
											
										}
										
									});

								});
						
						
						$(".edit")
								.click(
										function() {
											var musicide = $(this).data("musicide")
											window
													.open(
															"/melon/music/modify?albumId=${param.albumId}&musicId="+ musicide,
															"음악 등록",
															"resizable=no, scrollbars=yes, toolbar=no,width=300px , height=500px, menubar=no");

										});
						</c:if>
						$(".play")
								.click(
										function() {
											var mp = $(this).data("mp");
											var albumid = $(this).data(
													"albumid");

											var source = $("<source src='' type='audio/mp3'></source>");
											source.attr("src", '/melon/mp3/'
													+ albumid + "/" + mp);

											$("#mp3player").find("video").html(
													source);
											//	$("#mp3player").find("video")[0].load();
											$("#mp3player").find("video")[0]
													.play();

										});

					});
</script>

</head>
<body>
	<c:choose>
		<c:when test="${isAdminUser || isOperatorUser}">
	<input id="add" type="button" value="앨범 등록" />
		</c:when>
	</c:choose>
	<table>

		<tr>
			<th>번호</th>
			<th>곡명</th>
			<th>아티스트</th>
			<th>앨범</th>
			<th>좋아요</th>
			<th>듣기</th>
			<th></th>
			<th></th>

		</tr>


		<c:forEach items="${musicList}" var="music" varStatus="index">
			<tr>

				<td>${index.index+1}</td>
				<td><a href="/melon/music/detail?musicId=${music.musicId}">${music.title}</a></td>
				<td>${music.albumVO.artistVO.member}</td>
				<td>${music.albumVO.albumTitle}</td>
				<td>${music.likeCount}</td>
				<td class="play" data-albumid="${music.albumId}"
					data-mp="${music.mp3File}">듣기</td>
					
					
					<td><c:choose>
		<c:when test="${isAdminUser || isOperatorUser}">
	<input class="delete" data-musicidd="${music.musicId}"  type="button" value="삭제" />
		</c:when>
	</c:choose></td>
					<td><c:choose>
		<c:when test="${isAdminUser || isOperatorUser}">
	<input class="edit" data-musicide="${music.musicId}" type="button" value="수정" />
		</c:when>
	</c:choose></td>
	
	

			</tr>
		</c:forEach>
	</table>

	<form id="searchform">${pager}</form>

	<div id="mp3player">
		<video controls="controls" preload="auto" buffered></video>
	</div>


</body>
</html>