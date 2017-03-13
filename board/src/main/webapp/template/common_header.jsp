<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="/board/css/common_layout.css" />
<link rel="stylesheet" type="text/css" href="/board/css/list_layout.css" />
<script type="text/javascript" src="/board/js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div id="wrapper">

		<div id="nav">
		
			<c:if test="${ empty sessionScope._USER_}">
		<a href="/board/login">로그인</a> | <a href="/board/signUp">회원가입 </a>
	</c:if>
	
	<c:if test="${ not empty sessionScope._USER_}">
			<a href="/board/doLogout">로그아웃 </a>
			</c:if>

		</div>

		<div id="content">