<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Starkhaven</title>
</head>
<body>
<%
	// session에 "userID"라는 이름의 속성이 없다면 로그인하지 않았다는 뜻이다. -> session 종료하지 않고 메인페이지로 가도록 한다. 
	if(null == session.getAttribute("userID")) {
	%>
		로그인 상태가 아닙니다.
	<%
	// session에 "userID"라는 속성이 있으면, 로그인한 상태라는 뜻이다. -> session을 종료하고 메인페이지로 가도록한다.
	} else {
		session.invalidate();
%>
		로그아웃하였습니다.
<% } %>

<script>
	// 0.5초 후 메인페이지로 이동하도록 한다.
	self.setTimeout(function() {
		window.location.href = "<%=request.getContextPath() %>/OpenProject/main/index.jsp"
	}, 500);
</script>
</body>
</html>