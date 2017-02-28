<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
1. c:if
2. c:foreach
3. c:choose ~ c:when ~ c:otherwise  (switch, case, default)
4. c:set
5. c:out
6. c:import
 -->	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<img src="shiba.jpg" style="width: 260px; height: 228px;">
	<h3>시 to the 바</h3>

${introduce}

	<dl>
	
	
	<c:forEach items="${introList}" var="intro">
	
	${intro.name} <br/>
	${intro.home} <br/>
	${intro.age} <br/>
	
	</c:forEach>
	
	
		<dt>이름</dt>
		<dd>${introduce.name}</dd>  <!-- introduce의 getName을 호출하는 것 -->
		<dt>주소</dt>
		<dd>${introduce.home}</dd>
		<dt>나이</dt>
		<dd>${introduce.age}</dd>
	</dl>


	<h3>잘 하는 것</h3>
	<ul>
		<li>손</li>
		<li>점프</li>
		<li>사냥</li>

	</ul>

${introList[0].name}</br>
${introList[0].age}</br>
${introList[0].home}</br>



</body>
</html>