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
	$().ready(function() {

		$("#writeform").find("input[type=button]").click(function() {

			$("#writeform").attr({
				
			
				"action" : "/melon/music/modify?musicId=${musicVO.musicId}&albumId=${param.albumId}",
				"method" : "post"
				

			});
			$("#writeform").submit();

		});

	});
</script>



</head>
<body>

	<form id="writeform" enctype="multipart/form-data">
		<input type="text" name="title" placeholder="제목" value="${musicVO.title}" /><br /> <input
			type="text" name="musician" placeholder="뮤지션" value="${musicVO.musician}"/><br /> <input
			type="text" name="director" placeholder="디렉터" value="${musicVO.director}"/><br /> <input
			type="file" name="mp3File" accept=".mp3" value="${musicVO.mp3File}"/><br />
		<textarea name="lyrics" placeholder="가사">${musicVO.lyrics}</textarea>
		<br /> <input type="button" value="등록">
	</form>


</body>
</html>