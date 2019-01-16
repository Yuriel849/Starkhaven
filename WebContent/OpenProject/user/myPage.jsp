<%@page import="dto.User"%>
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Starkhaven</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/OpenProject.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>	
	<c:if test="${empty userID}">
    	<script>
    		/* 속성 중 "message" 속성이 존재할 경우, 페이지 로딩 후 팝업창으로 보여준다. */
    		alert("로그인하셔야 합니다");
    		window.location.href = "<%=request.getContextPath() %>/OpenProject/login/loginForm.jsp"
    	</script>
	</c:if>
	
	<jsp:include page="../main/header.jsp" flush="false"></jsp:include>
	
	<%
		User result = new UserDAO().selectByID((String) session.getAttribute("userID"));
		int cnt = 0;
	%>
	<div class="body_wrap">
    	<div class="body_main">
    		<div class="section">
				<h1>회원 마이페이지</h1>
				<hr>
				<div class="myPage_wrap">
					<img class="avatar" alt="Firenze" src="<%=request.getContextPath()%>/Pictures/Firenze2018.JPG">
            		<ul>
                		<li>
                    		<span>회원번호</span>
                    		<span class="Snd_span"><%=++cnt %></span>
                		</li>
                		<li>
                    		<span>회원아이디</span>
                    		<span class="Snd_span"><%=result.getID() %></span>
						</li>
                		<li>
                    		<span>회원이름</span>
                    		<span class="Snd_span"><%=result.getName() %></span>
                    	</li>
            		</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>