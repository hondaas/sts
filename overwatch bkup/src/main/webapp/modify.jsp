<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="/overwatch/doModify">
	캐릭터 명<br />
		<input type="hidden" name="unitId" " value="${unitVO.unitId}"/><br />

		<input type="text" name="unitName" placeholder="이름을 입력하세요." value="${unitVO.unitName}"/><br />
		데미지 수치<br />
		<input type="text" name="unitDamage" placeholder="수치를 입력하세요." value="${unitVO.unitDamage}"/><br />

 <c:choose>
      <c:when test="${unitVO.unitType=='공격'}">
    <input type="radio" name="unitType" value="공격" checked>공격<br>
    <input type="radio" name="unitType" value="수비" >수비<br>
    <input type="radio" name="unitType" value="돌격" >돌격<br>
    <input type="radio" name="unitType" value="지원" >지원<br>
      </c:when>

      <c:when test="${unitVO.unitType=='수비'}">
     <input type="radio" name="unitType" value="공격" >공격<br>
    <input type="radio" name="unitType" value="수비" checked>수비<br>
    <input type="radio" name="unitType" value="돌격" >돌격<br>
    <input type="radio" name="unitType" value="지원" >지원<br>
      </c:when>
      
      <c:when test="${unitVO.unitType=='돌격'}">
     <input type="radio" name="unitType" value="공격" >공격<br>
    <input type="radio" name="unitType" value="수비" >수비<br>
    <input type="radio" name="unitType" value="돌격" checked>돌격<br>
    <input type="radio" name="unitType" value="지원" >지원<br>
      </c:when>
      
      <c:otherwise>
    <input type="radio" name="unitType" value="공격" >공격<br>
    <input type="radio" name="unitType" value="수비" >수비<br>
    <input type="radio" name="unitType" value="돌격" >돌격<br>
    <input type="radio" name="unitType" value="지원" checked>지원<br>
      </c:otherwise>
    </c:choose>

		<input type="submit" value="생성" />
	</form>

</body>
</html>