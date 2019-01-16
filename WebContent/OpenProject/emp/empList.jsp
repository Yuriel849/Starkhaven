<%@page import="dto.Employee"%>
<%@page import="java.util.List"%>
<%@page import="handler.EmpListHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<Employee> result = new EmpListHandler().execute();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
				<h1 class="empH1">사원 리스트</h1>
				<hr>
				<div class="list">
					<table>
						<thead>
            				<tr>
                				<td>INDEX</td>
                    			<td>사원번호</td>
                    			<td>사원이름</td>
                    			<td>직책</td>
                    			<td>관리자</td>
                    			<td>입사일</td>
                    			<td>월급</td>
                    			<td>커미션</td>
                    			<td>부서번호</td>
							</tr>
            			</thead>
        				<tbody class="tableBody">
<%--         					<c:forEach>
        									newRows += '<tr><td>'+(++cnt)+'</td><td>'+tableID+'</td><td>'+tablePwd+'</td><td>'+tableName+
            '</td><td><a href="#" class="modify">수정</a>&nbsp;<a href="#" class="delete">삭제</a></td></tr>';
        						
        					</c:forEach> --%>
        				</tbody>
    				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>