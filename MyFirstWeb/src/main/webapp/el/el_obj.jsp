<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 목적지가 "el_obj_ok.jsp인 폼을 만들어주세요
	이름과 별명을 넘겨주면 됩니다.
	제출버튼을 눌러 넘어가게 해주세요. -->
	<form action="el_obj_ok.jsp" method="get">
		<input type="text" name="name" placeholder="이름"><br/>
		<input type="text" name="nick" placeholder="별명"><br/>
		<input type="submit">
	</form>
</body>
</html>