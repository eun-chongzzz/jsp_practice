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
	String trip  = request.getParameter("trip");
	
	if (trip.equals("Europe")){
		response.sendRedirect("https://www.youtube.com/watch?v=wuMqk1t39Wo");
	} else if (trip.equals("Asia")){
		response.sendRedirect("https://www.youtube.com/watch?v=rxe5yWsFKmg");
	} else if(trip.equals("America")){
		response.sendRedirect("https://www.youtube.com/watch?v=2ueNVUd1c94");
	} else if(trip.equals("Africa")){
		response.sendRedirect("https://www.youtube.com/watch?v=cb4GnrLHB0g");
	}
	%>
</body>
</html>