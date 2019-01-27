<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="header">
	<div class="header_wrap">
    	<div class="header_main">
        	<div class="top"><a href="<%=request.getContextPath() %>/OpenProject/main/index.jsp">STARKHAVEN</a></div>
		</div>
	</div>
    <div class="menu_wrap">
    	<div class="menu_main">
        	<div class="menu">
            	<ul>
                	<li><a href="<%=request.getContextPath() %>/OpenProject/join/joinForm.jsp">회원가입</a></li>
                    <li><a href="<%=request.getContextPath() %>/OpenProject/login/loginForm.jsp">로그인</a></li>
                    <li><a href="/starkhaven/LogoutHandler.do">로그아웃</a></li>
                    <li><a href="/starkhaven/UserPageHandler.do">마이페이지 (회원)</a></li>
                    <li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=1">회원 리스트</a></li>
                    <li><a href="/starkhaven/EmpListHandler.do?countPerPage=5&pageNumber=1">사원 리스트</a></li>
                    <li><a href="<%=request.getContextPath() %>/OpenProject/emp/addEmployee.jsp">사원 등록</a></li>
            	</ul>
        	</div>
    	</div>
	</div>
</div>