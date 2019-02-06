<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                	<li><a href="/join/joinForm">회원가입</a></li>
                    <li><a href="/login/loginForm">로그인</a></li>
                    <li><a href="/login/logout">로그아웃</a></li>
                    <li><a href="/user/myPage">마이페이지 (회원)</a></li>
                    <li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=1">회원 리스트</a></li>
                    <li><a href="/starkhaven/EmpListHandler.do?countPerPage=5&pageNumber=1">사원 리스트</a></li>
                    <li><a href="<%=request.getContextPath() %>/OpenProject/emp/addEmployee.jsp">사원 등록</a></li>
            	</ul>
        	</div>
    	</div>
	</div>
</div>