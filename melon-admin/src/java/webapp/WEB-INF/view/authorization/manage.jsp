<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type ="text/javascript" src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type ="text/javascript">

	$().ready(function() {
		
		$.post(   
				
				"/melon-admin/authorization/all", {}, function(response) {
			//JSON Format String 을 javascript Object로 변환함.
			
			console.log(response);
			
			var auth = JSON.parse(response);
			var authList = auth.authorizations;
			
			var authorizationDiv = $("#authorization");
			var parentAuthorizationId = $("#parentAuthorizationId");
			
			
			for(var i in authList) {
				console.log(authList[i].authorizationName);
				
				/*
				 * 인증 : Authentication( Cridential )
				 * 인가 : Authorization
				 */
				
				var eachAuth = $("<div id='" + authList[i].authorizationId +"'></div>");
				eachAuth.text(authList[i].authorizationName);
				eachAuth.data("parent", authList[i].parentAuthorizationId);
				
				authorizationDiv.append(eachAuth);
	
				/*
				 * select tag의 필수 하위 요소
				 * <option value='값'> </option>
				 * value --> 서버로 전달될 값
				 * 이름 --> 사용자에게 보여질 의미있는 값
				 *
				 */
				
				var itemAuth = $("<option value='" + authList[i].authorizationId 
						+"'> "+ authList[i].authorizationName +   "</option>");
				parentAuthorizationId.append(itemAuth);
			}
		});
		

			
			$("#authorization").on("click", "div", function() {
			
				$("#authorizationId").val( $(this).attr("id") );
				$("#authorizationName").val( $(this).text() );
				
				/*
				 *	<select id='parentAuthorizationId' name='a'>
				 *		<option value='값1'>이름1</option>
				 *		<option value='값2'>이름2</option>
				 *	</select>
				 *	data-parent = 값2
				*/
				
				$("#parentAuthorizationId").val( $(this).data("parent"));
				
				$("#modifyBtn").remove();
				$("#deleteBtn").remove();
				
				var modifyBtn = $("<input type='button' id='modifyBtn' value='수정' />");
				var deleteBtn = $("<input type='button' id='deleteBtn' value='삭제' />");;
				$("#registBtn").after(modifyBtn);
				$("#registBtn").after(deleteBtn);
			});
			
			
			$("#registForm").on("click", "#modifyBtn", function() {
				
			
				$.post("/melon-admin/authorization/modify", {
					"authorizationId" : $("#authorizationId").val() ,
					"authorizationName" : $("#authorizationName").val() ,
					"parentAuthorizationId" : $("#parentAuthorizationId").val()
				}, function(response) {
					var jsonObj = JSON.parse(response);
					console.log(jsonObj);
					if(jsonObj.status == "success") {
						var modifiedAuth = $("#authorizationId").val();
						
						$("#" + modifiedAuth).text($("#authorizationName").val());
						$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
						
						$("#parentAuthorizationId").find("option[value="+modifiedAuth+"]").text($("#authorizationName").val() );
						
						$("#deleteBtn").remove();
						$("#modifyBtn").remove();
						$("#authorizationId").val("");
						$("#authorizationName").val("");
						$("#parentAuthorizationId").val("");
						
					}
				});
				
				
				
				
			});
			
			
			$("#registForm").on("click", "#deleteBtn", function() {
				
				
				$.post("/melon-admin/authorization/delete", {
					"authorizationId" : $("#authorizationId").val()
				}, function(response) {
					
					var jsonObj = JSON.parse(response);
					console.log(jsonObj);
					if(jsonObj.status == "success") {
						var deletedAuth = $("#authorizationId").val();
						
						$("#" + deletedAuth).remove();
						
						console.log("test = " + deletedAuth);
						
						$("#parentAuthorizationId").find("option[value="+ deletedAuth +"]").remove();
					
						$("#deleteBtn").remove();
						$("#modifyBtn").remove();
						$("#authorizationId").val("");
						$("#authorizationName").val("");
						$("#parentAuthorizationId").val("");
						
					}
					
			
				});
				
				
			});
		
		
		$("#registBtn").click(function() {
			$.post("/melon-admin/authorization/regist", {
				"authorizationName" : $("#authorizationName").val(),
				"parentAuthorizationId" : $("#parentAuthorizationId").val()
			}, function(response) {
				//alert(response);
				var auth = JSON.parse(response);
				var authInfo = auth.authorization;
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#parentAuthorizationId");
				
				var eachAuth = $("<div id='" + authInfo.authorizationId +"'></div>");
				eachAuth.text(authInfo.authorizationName);
				eachAuth.data("parent", authInfo.parentAuthorizationId);
				authorizationDiv.append(eachAuth);
				
				//console.log("before = " + authInfo.authorizationId);
				
				var itemAuthorization = $("<option value='" + authInfo.authorizationId  +"'> "+ authInfo.authorizationName +   "</option>");
				parentAuthorizationId.append(itemAuthorization);
			});
		});
		
	});


</script>

</head>
<body>

	<div id ="regist">
		<form id="registForm">
			<input type="hidden" id="authorizationId" />
			<span>권한 명</span><br />
			<input type="text" id="authorizationName" /> <br/>
			<br/>
			<span>상위 권한</span>
			<select id="parentAuthorizationId"></select> <br/>
			<br/>
			<input type="button" id="registBtn" value="저장" />
		</form>
	</div>
	
	<div id ="authorization"></div>

</body>
</html>