<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css"
	href="/board/css/write_layout.css" />
	
<script type="text/javascript">
	
	
	$().ready(function(){
		
		$("#writeForm").find("input[type=button]").click(function(){
		//	$("#writeFrom").attr("method", "post");
		//	$("#writeFrom").attr("action", "/board2/doWrite"); 같은 방법도 있지만,
		
			$("#writeForm").attr({
			
				"method" : "post",
				"action" : "/board/doWrite"
			})
			$("#writeForm").submit();
			
		});
		
		$("#writeForm").find("img").click(function(){
			
			$.post("/board/doWrite", {
				//ajax는 form으로 전송하지 않는다.
				"subject" : $(".subject").val(),
				"content" : $(".content").val()
				
			}, function(response) {
				alert("완료")
				//원래 ajax는 응답받고 처리하는 일이 있으나, 여기서는 의미가 없다.
				//(어차피 list 페이지로 옮겨간다.)
			});
			
		});
		
		
		
		
	  });
	
	
	</script>

<div class="write">

	<h1>글쓰기</h1>
	<hr>
	<hr>
	<div class="insert">
		<form id="writeForm" enctype="multipart/form-data" accept=".jpg , .gif, .png">
			<div class="boxes">
				<!-- 이쪽으로 정보를 보내라!  doPost로 요청이 전달-->
				<!-- <input type="text" name="writer" placeholder="이름을 입력하세요." /><br />  -->
				<input type="text" class="subject" name="subject" placeholder="제목을 입력하세요." /><br />
				<input type="file" name="img" accept=".gif , jpg , png"/>
				<textarea name="content" class="content" placeholder="내용을 입력하세요."></textarea>
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
			</div>
			<div class="dowrite">
				<input type="button" value="글쓰기" />
				<img src="/board/img/ic_check_black_24dp_2x.png" style="cursor:pointer;"/>
			</div>
		</form>

	</div>
</div><!--
--><div class="login">
	<c:if test="${ empty sessionScope._USER_}">
		<jsp:include page="/view/users/login.jsp" />
	</c:if>
	<c:if test="${ not empty sessionScope._USER_}">
			${sessionScope._USER_.userName }님, 환영합니다.
			</c:if>
</div>

<jsp:include page="/template/common_footer.jsp" />