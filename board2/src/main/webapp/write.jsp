<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글쓰기</h1>
	<hr>
	<hr />



	<form method="post" action ="/board/doWrite"><!-- 이쪽으로 정보를 보내라!  doPost로 요청이 전달-->
		<input type="text" name="writer" placeholder="이름을 입력하세요." /><br /> 
		<input type="text" name="subject" placeholder="이름을 입력하세요." /><br />
		<textarea name="content" placeholder="내용을 입력하세요."></textarea>
		<!-- textarea는 띄어쓰기하면 text로 인식하므로 닫기를 바로 붙여준다. -->
		
		<!-- writer, subject, content 라는 이름으로 데이터가 전달된다. 이들은 parameter이다. -->
		
		<!-- 페이지 갱신 방법   1.url입력(doGet)    2.링크 클릭(doGet)   3. 입력해서 전달 (doPost)-->
		
		<!-- Get 방식으로 사용하면  url?key=value&keyvalue식으로 나타난다.-->
		<!-- Get 방식의 전달은 255글자의 제한이 있다. 1byte-->
		<!-- Get 은 url이나 링크 걸때 사용 -->
		<!-- Post 방식으로 사용하면  url?이하가 나타나지 않는다. (로그인)-->
		<!-- Post 방식의 전달은 2GB.-->
		<!-- form 쓰면 무조건 Post -->
		<!-- Post Get은 복합하여 사용가능 -->
		
		
		<input type="submit" value="글쓰기" />
	</form>




</body>
</html>