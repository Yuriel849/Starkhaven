<%@page import="com.dto.User"%>
<%@page import="com.dao.UserDAO"%>
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
	<jsp:include page="../main/header.jsp" flush="false"></jsp:include>
	
	<div class="body_wrap">
    	<div class="body_main">
    		<div class="section">
				<h1>회원 마이페이지</h1>
				<hr>
				<div class="myPage_wrap">
					<img class="avatar" alt="Firenze" src="<%=request.getContextPath()%>/Pictures/Firenze2018.JPG">
            		<ul>
                		<li>
                    		<span>회원아이디</span>
                    		<span class="Snd_span">${result.id}</span>
						</li>
                		<li>
                    		<span>회원이름</span>
                    		<span class="Snd_span">${result.name}</span>
                    	</li>
            		</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>