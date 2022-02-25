<%@page import="kr.co.ict.UserDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
	// 1. form에서 보낸 비번, 이름, 이메일을 변수로 저장해주세요.
		String pw = request.getParameter("fpw");
		String name = request.getParameter("fname");
		String email = request.getParameter("femail");
	// 2. session에 저장된 아이디를 변수로 저장해주세요.
		String sId = (String)session.getAttribute("session_id");
	
	/*
	// 3. DB접속정보 변수로 관리
		String dbType = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/jdbcprac1";
		String dbId = "root";
		String dbPw = "mysql";
	try{
	// 4. 연결
		Class.forName(dbType);
		Connection con = DriverManager.getConnection(dbUrl, dbId, dbPw);
	// 5. pstmt생성 및 ?에 값 세팅
	// 쿼리문 : 
		String sql = "UPDATE userinfo SET upw= ?, uname= ?, uemail= ? WHERE uid= ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, pw);
		pstmt.setString(2, name);
		pstmt.setString(3, email);
		pstmt.setString(4, sId);
	// 6. pstmt 실행 및 자원 회수
		pstmt.executeUpdate();
	
		con.close();
		pstmt.close();
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		
	}
	*/
	
	// 위쪽 3~6코드를 DAO로 대체해서 처리해주세요.
	// DAO 생성
	UserDAO dao = new UserDAO();
	
	// update로직 호출
	dao.updateCheck(sId, pw, name, email);
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<!-- 7. body태그에 xxx 회원의 정보가 수정되었습니다. 라고 안내해주기 -->
<body>
	<h1><%=sId %>(<%=name %>)님의 정보가 수정되었습니다.</h1>
	<a href="login_welcome.jsp">웰컴페이지 돌아가기!</a>
</body>
</html>