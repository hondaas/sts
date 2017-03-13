<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/template/common_header.jsp"/>


	<form method="post" action="/board/doSignUp">
	<input type="text" name="userId" placeholder = "ID를 입력하세요"/>
	<input type="password" name= "userPassword" placeholder="비밀번호를 입력하세요" />
	<input type="text" name="userName" placeholder="이름을 입력 하세요"/>
	<input type="submit" value="가입하기"/>
	
	
	</form>

<jsp:include page="/template/common_footer.jsp"/>