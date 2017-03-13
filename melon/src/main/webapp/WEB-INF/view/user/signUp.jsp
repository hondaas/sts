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
<script type="text/javascript" src="/melon/static/js/json2.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("#userId").keyup(function() {
			$.post("/melon/user/checkDuplicate",

			{
				"userId" : $("#userId").val()
			},

			function(response) {
				var jsonObj = JSON.parse(response);
				console.log(jsonObj);

				if (jsonObj.duplicated) {

					$("#duplicateState").text('이미 사용중인 아이디 입니다.')
				} else {
					$("#duplicateState").text('사용 가능한 아이디 입니다.')

				}

			});

		});

		$("#signUpForm").find("input[type=button]").click(function() {

			//필수 입력 값 체크 (validation check)
			if ($("#userId").val() == "") {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return; //리턴 할 것이 없으므로, 종료를 의미

			}

			if ($("#userPass1").val().length < 8) {
				alert("최소 8글자 입니다.");
				$("#userId").focus();
				return;
			}

			if ($("#userPass1").val() != $("#userPass2").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#userId").focus();
				return;
			}

			$.post("/melon/user/checkDuplicate", {
				"userId" : $("#userId").val()
			}, function(response) {

				var jsonObj = JSON.parse(response);

				if (jsonObj.duplicated) {
					alert("입력한 아이디는 이미 사용중입니다.")
					return;

				} else {

					$("#signUpForm").attr({

						"method" : "post",
						"action" : "/melon/user/signUp"

					});
					$("#signUpForm").submit();

				}
			});

		});

	});
</script>

</head>
<body>

	<c:if test="${ not empty param.errorCode}">
		<div>
			<c:choose>
				<c:when test="${param.errorCode=='0'}">ID는 필수 </c:when>
				<c:when test="${param.errorCode=='1'}">패스워드는 필수 </c:when>
				<c:when test="${param.errorCode=='2'}">이름은 필수 </c:when>
				<c:when test="${param.errorCode=='3'}">이미 사용중인 아이디 입니다.</c:when>
				<c:otherwise>??</c:otherwise>

			</c:choose>
		</div>
	</c:if>

	<form id="signUpForm">
		<input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요" />
		<span id=duplicateState></span> <br /> <input type="text"
			name="userName" placeholder="이름을 입력하세요" /><br /> <input
			type="password" name="userPassword" id="userPass1"
			placeholder="비밀번호를 입력하세요" /><br /> <input type="password"
			name="userPasswordre" id="userPass2" placeholder="비밀번호를 입력하세요" /><br />
		<input type="button" value="가입" /><br />
	</form>
</body>
</html>