<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$().ready(function() {

	$("#signInForm").find("input[type=button]").click(function() {
		
		
		$("#signInForm").attr({

			"method" : "post",
			"action" : "/melon/user/signIn"

		});
		$("#signInForm").submit();

	});

});



</script>
</head>
<body>

<form id="signInForm">
<input type="text" name="userId" placeholder="아이디를 입력하세요."/>
<input type="password" name="userPassword" placeholder="패스워드를 입력하세요."/>
<input type="button" value="로그인"/>

</form>

</body>
</html>