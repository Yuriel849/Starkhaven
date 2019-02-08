<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">
	<div class="header_wrap">
    	<div class="header_main">
        	<div class="top"><a href="/">STARKHAVEN</a></div>
		</div>
	</div>
    <div class="menu_wrap">
    	<div class="menu_main">
        	<div class="menu">
            	<ul>
                    <li><a href="/user/myPage">마이페이지 (회원)</a></li>
                    <li><a href="/user/list/5/1">회원 리스트</a></li>
                    <li><a href="/user/board?countPerPage=5&pageNumber=1">게시판</a></li>
                    <li><a href="/emp/list?countPerPage=5&pageNumber=1">사원 리스트</a></li>
                    <li><a href="/emp/addEmployee">사원 등록</a></li>
                    <c:choose>
                    	<c:when test="${user != null}">
		                    <li><a href="/login/logout" id="logout">로그아웃</a></li>                    	
                    	</c:when>
                    	<c:otherwise>
		                	<li><a href="/join/joinForm">회원가입</a></li>
	                    	<li><a href="/login/loginForm">로그인</a></li>                                        	
                    	</c:otherwise>
                    </c:choose>
            	</ul>
        	</div>
    	</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#logout").on('click', function() {
			return confirm('로그아웃하시겠습니까?');
		});
	});
</script>
