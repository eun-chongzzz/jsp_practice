<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ServletCustom을 통해 건너온 페이지입니다.(Get방식)</h1>
	<!-- 해당 페이지 내에 선언구문이 없는 자료 출력시는 EL을 씁니다. -->
	<p>jsp에 변수에 들어있던 값 : ${jsp }</p>
	<p>boot에 변수에 들어있던 값 : ${boot }</p>
	<p>jpa에 변수에 들어있던 값 : ${jpa }</p>
</body>
</html>