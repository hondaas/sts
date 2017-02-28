<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// Scriptlet
		// HTML 문서 중간에 JAVA Code를 작성 할 수 있는 영역

		String name = "김도준";
		String address = "서울시 동대문구 이문동";
				
		//String은 java.lang 패키지는 임포트 해주지 않아도 되므로.
	%>

	<img src="shiba.jpg" style="width: 260px; height: 228px;">
	<h3>
		<%
			out.print(name);
		%>
		시 to the 바
	</h3>

	<dl>
		<dt>이름</dt>
		<dd>
			<%
				out.print(name);
			%>
		</dd>
		<dt>주소</dt>
		<dd><%=address%></dd>
		<dt>견종</dt>
		<dd>시바견</dd>
	</dl>

	<%
		List<String> subjects = new ArrayList<String>();
		subjects.add("손");
		subjects.add("점프");
		subjects.add("사냥");

		//임포트는 직접 해줄 수 밖에 없다.(디렉티브를 활용)
	%>

	<h3>잘 하는 것</h3>
	<ul>

		<%
			for (String subject : subjects) {
		%>
		
		<li><%=subject%></li>
		
		<% } %>

	</ul>

</body>
</html>