<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오버워치임</title>
</head>

<h1>고급시계 영웅 목록</h1>

<body>

	<table>

		<tr>
			<th>번호</th>
			<th>캐릭터명</th>
			<th>데미지</th>
			<th>타입</th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${unitList}" var="unitList">
		
		<tr>
			<td>${unitList.unitId}</td>
			<td>${unitList.unitName}</td>
			<td>${unitList.unitDamage}</td>
			<td>${unitList.unitType}</td>
			<td><a href="/overwatch/modify?unitId=${unitList.unitId}"><input type="submit" value="수정" /></td>
			<td><a href="/overwatch/doDelete?unitId=${unitList.unitId}"><input type="submit" value="삭제" /></td>
		</tr>

		</c:forEach>
	</table>

<a href="/overwatch/create"><input type="submit" value="생성" /></a>

</body>
</html>