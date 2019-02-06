<%@page import="com.handler.UserListHandler"%>
<%@page import="com.dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="current" class="java.util.Date" />
<fmt:formatDate var="time" value="${current}" pattern="yyyy.MM.dd 'at' HH:mm:ss z" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Starkhaven</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/OpenProject.css">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%-- 	<c:if test="${empty userID}">
    	<script>
    		alert("로그인하셔야 합니다");
    		window.location.href = "<%=request.getContextPath() %>/OpenProject/login/loginForm.jsp"
    	</script>
	</c:if> --%>

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
				<h1 class="empH1">회원 리스트</h1>
				<hr>
				<div class="list">
					<table>
						<thead>
            				<tr>
                				<td>번호</td>
                    			<td>회원 아이디 (이메일)</td>
                    			<td>회원 이름</td>
                    			<td>관리</td>
							</tr>
            			</thead>
        				<tbody class="tableBody">
        					<c:forEach var="item" items="${result.list}">
	        					<tr>
    	    						<td class="idx"></td>
        							<td class="ident">${item.id}</td>
         							<td>${item.name}</td>
    	    						<td>
        								<a href="#" class="modify" id="modBtn">수정</a>&nbsp;<a href="#" class="delete" id="delBtn">삭제</a>
        							</td>
        						</tr>
        					</c:forEach>
        				</tbody>
    				</table>
				</div>
				<h5>Last Updated : ${time}</h5>
				
				<ul class="pageShift">
					<li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=1">&lt;&lt;</a></li>
					<li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=${result.currentPageNumber-1}">&lt;</a></li>
					<c:forEach var="i" begin="1" end="${result.pageTotalCount}">
						<li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=${i}">${i}</a></li>
					</c:forEach>
					<li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=${result.currentPageNumber+1}">&gt;</a></li>
					<li><a href="/starkhaven/UserListHandler.do?countPerPage=5&pageNumber=${result.pageTotalCount}">&gt;&gt;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		var cnt = 0;
		var lineNum = ${result.firstRow}
    	var span = document.getElementsByClassName("close")[0];
    	var pageNum = ${result.currentPageNumber};
    	
    	$.each($('.idx').parent(), function(puppy, wolf) {
			console.log(cnt);
			$('.idx:eq('+(cnt++)+')').append(lineNum++);
		});
    	
    	$(".tableBody").on('click', '#delBtn', function() {
        	console.log("pageNum : " + pageNum);
            if(confirm('삭제하시겠습니까?')) {
            	$.ajax({
            		url : '/starkhaven/DeleteHandler.do',
            		type : 'GET',
            		data : {ID : $(this).parent().siblings('.ident').text(), countPerPage : 5, pageNumber : pageNum},
            		error : function() {
            			alert("삭제하는데 에러가 발생했습니다.");
            		},
            		success : function(data) {
            			alert("성공적으로 삭제했습니다.");
            			console.log("data : " + data);
						$('.tableBody').empty().append(data);
						cnt = 0;
						lineNum = ${result.firstRow};
           		    	$.each($('.idx').parent(), function(puppy, wolf) {
           					console.log(cnt);
           					$('.idx:eq('+(cnt++)+')').append(lineNum++);
           				});

            		}
            	});
            }
    	});
	</script>
</body>
</html>