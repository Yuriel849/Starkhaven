<%@page import="com.handler.EmpListHandler"%>
<%@page import="com.dto.Employee"%>
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
				<h1 class="empH1">사원 리스트</h1>
				<hr>
				<div class="list">
					<table>
						<thead>
            				<tr>
                				<td>번호</td>
                    			<td>사원번호</td>
                    			<td>사원이름</td>
                    			<td>직책</td>
                    			<td>관리자</td>
                    			<td>입사일</td>
                    			<td>월급</td>
                    			<td>커미션</td>
                    			<td>부서번호</td>
                    			<td>관리</td>
							</tr>
            			</thead>
        				<tbody class="tableBody">
        					<c:forEach var="item" items="${result.list}">
	        					<tr>
    	    						<td class="idx"></td>
        							<td>${item.empno}</td>
        							<td>${item.ename}</td>
        							<td>${item.job}</td>
        							<td>${item.mgr}</td>
        							<td>${item.hiredate}</td>
        							<td>${item.sal}</td>
        							<td>${item.comm}</td>
	        						<td>${item.deptno}</td>
    	    						<td>
        								<a href="#" class="modify">수정</a>&nbsp;<a href="#" class="delete">삭제</a>
        							</td>
        						</tr>
        					</c:forEach>
        				</tbody>
    				</table>
				</div>
				<h5>Last Updated : ${time}</h5>
				
				<ul class="pageShift">
					<li><a href="/starkhaven/EmpListHandler.do?countPerPage=3&pageNumber=1">&lt;&lt;</a></li>
					<li><a href="/starkhaven/EmpListHandler.do?countPerPage=3&pageNumber=${result.currentPageNumber-1}">&lt;</a></li>
					<c:forEach var="i" begin="1" end="${result.pageTotalCount}">
						<li><a href="/starkhaven/EmpListHandler.do?countPerPage=3&pageNumber=${i}">${i}</a></li>
					</c:forEach>
					<li><a href="/starkhaven/EmpListHandler.do?countPerPage=3&pageNumber=${result.currentPageNumber+1}">&gt;</a></li>
					<li><a href="/starkhaven/EmpListHandler.do?countPerPage=3&pageNumber=${result.pageTotalCount}">&gt;&gt;</a></li>
				</ul>
				
				<div id="modal" class="modal">
                    <div class="modal-content">
                        <div class="reg">데이터 수정</div>
                        <span class="close">&times;</span>
                        <div class="modal-format">
                            <span class="id">
                                <label for="newID">아이디(이메일)</label>
                                <input type="text" class="newID" name="modal-newID" id="modal-newID" maxlength="15">
                            </span>
                            <span class="pwd">
                                <label for="newPwd">비밀번호</label>
                                <input type="password" class="newPwd" name="modal-newPwd" id="modal-newPwd" maxlength="15">
                            </span>
                            <span class="name">
                                <label for="newName">이름</label>
                                <input type="text" class="newName" name="modal-newName" id="modal-newName" maxlength="15">
                            </span>
                            <span class="modal-btnSpan">
                                <input type="button" class="modal-submit" name="modal-submit" id="modal-submit" value="수정">
                            </span>
                        </div>
                    </div>
                </div>
			</div>
		</div>
	</div>
	<script>
		var cnt = 0;
		var lineNum = ${result.firstRow}
        var span = document.getElementsByClassName("close")[0];
		
		$.each($('.idx').parent(), function(puppy, wolf) {
			console.log(cnt);
			$('.idx:eq('+(cnt++)+')').append(lineNum++);
		});
		
		
        /* "수정" 버튼을 누르면 modal을 띄워서 사용자 입력을 기다린다. */
        $('body').on('click', '.modify', function() {
            /* "수정" 버튼을 누르면 modal이 나타난다 (안보이던게 보이게 된다) */
            modal.style.display = "block";
            document.getElementById('modal-newID').focus();
            idx = $(this).parent().parent().index();
        });
        
/*         /* modal에서 "수정" 버튼을 누르면 해당 <tr>의 값을 바꾼다. /
        $('#modal-submit').on('click', function() {
            changeRegistry();
        }); */

        /* modal의 "X" 표시를 누르면 modal을 닫는다 (안보이게 바꾼다) */
        span.onclick = function() {
            modal.style.display = "none";
        }
        
        /* modal 밖의 영역을 누르면 modal을 닫는다 (안보이게 바꾼다) */
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
	</script>
</body>
</html>