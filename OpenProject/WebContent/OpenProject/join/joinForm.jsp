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
	  	
	<c:if test="${not empty message}">
    	<script>
    		/* 속성 중 "message" 속성이 존재할 경우, 페이지 로딩 후 팝업창으로 보여준다. */
    		$(document).ready(function() { alert("${message}"); });
    	</script>
	</c:if>

	<div class="body_wrap">
    	<div class="body_main">
    		<div class="section">
				<h1>회원가입</h1>
				<hr>
				<div class="form_wrap">
        			<form action="<%=request.getContextPath() %>/JoinHandler.do" method="post">
            			<ul>
                			<li>
                    			<label for="ID">아이디 (이메일)</label>
                    			<input type="text" placeholder="&nbsp;&nbsp;ID (email address)" id="ID" name="ID" maxlength="50" required>
                			</li>
                			<li>
                    			<label for="PW">비밀번호</label>
                    			<input type="password" placeholder="&nbsp;&nbsp;password" id="PW" name="PW" maxlength="20" required>
                			</li>
                			<li>
                    			<label for="Name">이름</label>
                    			<input type="text" placeholder="&nbsp;&nbsp;name" id="Name" name="Name" maxlength="30" required>
                			</li>
                			<li>
                    			<label for="Img">사진</label>
                    			<input type="file" id="Img" name="Img" accept="image/">
                			</li>
                			<li class="sub_li">
                    			<input type="submit" value="등록">
                			</li>
            			</ul>
        			</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>