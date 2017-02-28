<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>생성하기</h1>
	<hr>
	<hr />



	<form method="post" action="/overwatch/doCreate">
		캐릭터 명<br /> <input type="text" name="unitName"
			placeholder="이름을 입력하세요." /><br />


		데미지 수치<br /> <input type="text" name="unitDamage"
			placeholder="수치를 입력하세요." /><br /> <input type="radio"
			name="unitType" value="공격">공격<br> <input type="radio"
			name="unitType" value="수비">수비<br> <input type="radio"
			name="unitType" value="돌격">돌격<br> <input type="radio"
			name="unitType" value="지원">지원<br> <input type="submit"
			value="생성" />

	</form>




</body>
</html>