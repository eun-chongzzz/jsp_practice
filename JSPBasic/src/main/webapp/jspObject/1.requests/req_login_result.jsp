<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id1 = "전사";
	String pw1 = "1234";
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	%>
	
	<%
	if(id1.equals(id) && pw1.equals(pw)){ %>
		<b><%=id1%>님 환영합니다!</b>
	<%  } else{ %>
		<b>로그인에 실패했습니다.</b>
	<% } %><br>
	<a href="req_login_form.jsp">로그인창으로 돌아갑니다.</a>
</body>
</html>