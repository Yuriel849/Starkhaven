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
	<link rel="stylesheet" href="/resources/css/OpenProject.css">
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
				<h1 class="empH1">회원 리스트</h1>
				<span>
					<a href="/download/xlsx" class="download" id="downloadXlsx">EXCEL 다운로드</a>
					<a href="/download/pdf" class="download" id="downloadPdf">PDF 다운로드</a>
				</span>
				<hr>
				<div class="list">
					<table>
						<thead>
            				<tr>
                				<td>번호</td>
                				<td>회원 번호</td>
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
        							<td class="iEmail">${item.email}</td>
         							<td class="iName">${item.name}</td>
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
					<li><a href="/user/list/5/1">&lt;&lt;</a></li>
					<li><a href="/user/list/5/${result.currentPageNumber-1}">&lt;</a></li>
					<c:forEach var="i" begin="1" end="${result.pageTotalCount}">
						<li><a href="/user/list/5/${i}">${i}</a></li>
					</c:forEach>
					<li><a href="/user/list/5/${result.currentPageNumber+1}">&gt;</a></li>
					<li><a href="/user/list/5/${result.pageTotalCount}">&gt;&gt;</a></li>
				</ul>
				
				<!-- 회원 정보 수정용 modal -->
				<div id="modal" class="modal">
                    <div class="modal-content">
                        <div class="reg">데이터 수정</div>
                        <span class="close">&times;</span>
                        <div class="modal-format">
                        	<span class="id">
                        		<label for="newID">회원번호</label>
                        		<input type="text" class="newID" name="modal-ID" id="modal-ID" readonly>
                        	</span>
                            <span class="id">
                                <label for="newID">이메일</label>
                                <input type="text" class="newID" name="modal-newEmail" id="modal-newEmail" maxlength="15">
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
		var lineNum = ${result.firstRow} + 1;
    	var span = document.getElementsByClassName("close")[0];
    	var pageNum = ${result.currentPageNumber};
    	
    	// 회원 명단 번호 매기기
    	$.each($('.idx').parent(), function(puppy, wolf) {
			console.log("cnt " + cnt);
			console.log("lineNum " + lineNum);
			$('.idx:eq('+(cnt++)+')').append(lineNum++);
		});

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
    	            	
    	// 회원 수정하기 기능
    	$(".tableBody").on('click', '#modBtn', function() {
            /* "수정" 버튼을 누르면 modal이 나타난다 (안보이던게 보이게 된다) */
            modal.style.display = "block";
            var userId = $(this).parent().siblings(".ident").text();
            document.getElementById('modal-ID').value = userId;            
            var userEm = $(this).parent().siblings(".iEmail").text();
            document.getElementById('modal-newEmail').value = userEm;
            var userNa = $(this).parent().siblings(".iName").text();
            document.getElementById('modal-newName').value = userNa;
            
            document.getElementById('modal-ID').focus();
            idx = $(this).parent().parent().index();
        	$("#modal-submit").on('click', function() {
            	$.ajax({
            		url : '/modifyUser',
            		type : 'POST',
            		data : {id : userId,
            				email : $("#modal-newEmail").val(),
            				pw : $("#modal-newPwd").val(),
            				name : $("#modal-newName").val(),
            				countPerPage : 5,
            				pageNumber : pageNum},
            		error : function() {
            			alert("회원정보를 수정하는데 실패했습니다. 다시 시도해주세요.");
            		},
            		success : function(data) {
            			alert("성공적으로 수정했습니다.");
            			console.log("data : " + data);
						$('.tableBody').empty().append(data);
						cnt = 0;
						lineNum = ${result.firstRow} + 1;
           		    	$.each($('.idx').parent(), function(puppy, wolf) {
           					console.log(cnt);
           					$('.idx:eq('+(cnt++)+')').append(lineNum++);
           				});
           	            modal.style.display = "none";
            		}
            	});
        	});
    	});
    	
    	// 회원 삭제하기 기능
    	$(".tableBody").on('click', '#delBtn', function() {
            if(confirm('삭제하시겠습니까?')) {
            	$.ajax({
            		url : '/deleteUser',
            		type : 'GET',
            		data : {id : $(this).parent().siblings('.ident').text(), countPerPage : 5, pageNumber : pageNum},
            		error : function() {
            			alert("삭제하는데 에러가 발생했습니다.");
            		},
            		success : function(data) {
            			alert("성공적으로 삭제했습니다.");
            			console.log("data : " + data);
						$('.tableBody').empty().append(data);
						cnt = 0;
						lineNum = ${result.firstRow} + 1;
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