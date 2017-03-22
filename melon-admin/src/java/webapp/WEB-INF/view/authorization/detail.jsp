<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
		var userId = "${param.userId}";
		$("#auth").val("${user.authorizationId}");
		
		$("#pointBtn").click(function() {
			
			
			var buttonText = $(this).val();
			
			if(buttonText == "변경하기") {
				$(this).val("변경완료");	
				$("#point").removeAttr("disabled");
			}
				
			else if(buttonText == "변경완료") {
				$(this).val("변경하기");
				$("#point").attr("disabled", "disabled");
				
				
				$.post("/melon-admin/user/modify", {
					"userPoint" : $("#point").val() ,
					"userPassword" : $("#password").val() ,
					"authorizationId" : $("#auth").val(),
					"userId" : userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					console.log(jsonObj);
					if(jsonObj.status == "success") {	
					}
				});
				
			}
			
			
		
			
			
			
			
		});
		
		
		$("#passwordBtn").click(function() {
			
		
			var buttonText = $(this).val();
			
			if(buttonText == "변경하기") {
				$(this).val("변경완료");	
				$("#password").removeAttr("disabled");
			}
				
			else if(buttonText == "변경완료") {
				$(this).val("변경하기");
				$("#password").attr("disabled", "disabled");
				
				$.post("/melon-admin/user/modify", {
					"userPoint" : $("#point").val() ,
					"userPassword" : $("#password").val() ,
					"authorizationId" : $("#auth").val(),
					"userId" : userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					console.log(jsonObj);
					if(jsonObj.status == "success") {
					}
				});
			}
			
			
			
		
		});
		
		
		
			$("#authBtn").click(function() {
	
				
				var buttonText = $(this).val();
	
				
				if(buttonText == "변경하기") {
					$(this).val("변경완료");	
					$("#auth").removeAttr("disabled");
				}
		
				else if(buttonText == "변경완료") {
						$(this).val("변경하기");
						$("#auth").attr("disabled", "disabled");
						
						$.post("/melon-admin/user/modify", {
							"userPoint" : $("#point").val() ,
							"userPassword" : $("#password").val() ,
							"authorizationId" : $("#auth").val(),
							"userId" : userId
						}, function(response) {
							var jsonObj = JSON.parse(response);
							console.log(jsonObj);
							if(jsonObj.status == "success") {
							
							}
						});
				}
				
			
				
			});
		
	});

</script>
<title>Insert title here</title>
</head>
<body>
	${user.userId} <br/>
	${user.userName} <br/>
	
	<form id="modifyForm">
		<input type="text" id="point" disabled="disabled" value="${user.userPoint}"/> 
		<input type="button" id="pointBtn" value="변경하기" />
		<br/>
		<input type="password" id="password" disabled="disabled"/>
		<input type="button" id="passwordBtn" value="변경하기"/>
		 <br/>
		<select id="auth" disabled="disabled">
			
			<c:forEach items="${authList}" var="auth">
				<option value="${auth.authorizationId}">${auth.authorizationName}</option>
			</c:forEach>
		
		</select>
		<input type="button" id="authBtn" value="변경하기" />
	</form>
</body>
</html>