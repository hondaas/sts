<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = 'c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type ="text/javascript" src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type ="text/javascript">
	
	$().ready(function() {
		
		
		

	
		
		var changeAuthorization = $("#changeAuthorization");
		
		$("#changeAuthorization").find("input[type=button]").click(function() {
		
		
			$("#changeAuthorization").attr({
						action:"/melon-admin/authorization/doChange", 
						method:'post'
						}).submit();
					
		});

		
	});


</script>
<title>리스트</title>
</head>
<body>
	<table>
		<tr>
			<th> 선택 </th>
			<th> 번호 </th>
			<th> ID  </th>
			<th> 이름 </th>
			<th> 포인트 </th>
			<th> 권한 </th>
		</tr>
		
		<c:forEach items="${userList}" var="user">
			<tr>
				<td> <input type="checkbox" id="isChecked" name="myCheck" value="${user.userId}" form="changeAuthorization"/></td>
				<td> ${user.index} </td>
				<td> <a href="/melon-admin/user/detail?userId=${user.userId}">${user.userId}</a></td>
				<td> ${user.userName} </td>
				<td> ${user.userPoint} </td>
				<td> <c:if test = "${empty user.authorizationVO.authorizationName}" >권한없음</c:if><c:if test = "${not empty user.authorizationVO.authorizationName}" >${user.authorizationVO.authorizationName}</c:if></td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<form id="searchFrom">
			${pager}
		</form>
	</div>
	
	<div id = "regist">
	</div>
	
	<div id = "divChangeAuthorization">
		<form id = "changeAuthorization" >
			<select name="beforeAuthorization">
				<option>없음</option>
				<c:forEach items="${authorizationList}" var="authorization">
					<option value="${authorization.authorizationId}">${authorization.authorizationName}</option>
				</c:forEach>
			</select> 
			
			권한을	
			
			<select name="afterAuthorization">
			<option>없음</option>
				<c:forEach items="${authorizationList}" var="authorization">
					<option value="${authorization.authorizationId}">${authorization.authorizationName}</option>
				</c:forEach>
			</select>
			
			
		<input type="button" value="변경"/>
			
		</form>
	</div>
	
</body>
</html>