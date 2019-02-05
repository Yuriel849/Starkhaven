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
				<h1>사원등록</h1>
				<hr>
				<div class="form_wrap">
					<form action="<%=request.getContextPath() %>/AddEmpHandler.do" method="post">
        				<ul>
            				<li>
                				<label for="empno">사원번호 : </label>
                				<input type="number" id="empno" name="empno" maxlength="4" required>
            				</li>
            				<li>
                				<label for="ename">사원이름 : </label>
                				<input type="text" id="ename" name="ename" maxlength="10" required>
            				</li>
            				<li>
                				<label for="job">직급 : </label>
                				<input type="text" id="job" name="job" maxlength="9" required>
            				</li>
            				<li>
                				<label for="mgr">메니저 : </label>
                				<select name="mgr" id="mgr" required>
                    				<option value="0">NONE</option>
                    				<option value="7839">KING</option>
                    				<option value="7698">BLAKE</option>
                    				<option value="7782">CLARK</option>
                    				<option value="7566">JONES</option>
                    				<option value="7788">SCOTT</option>
                    				<option value="7902">FORD</option>
                				</select>
            				</li>
            				<li>
                				<label for="hiredate">입사일 : </label>
                				<input type="date" id="hiredate" name="hiredate" required>
            				</li>
            				<li>
                				<label for="sal">급여 : </label>
                				<input type="number" id="sal" name="sal" maxlength="7" required>
            				</li>
            				<li>
                				<label for="comm">커미션 : </label>
                				<input type="number" id="comm" name="comm" maxlength="7" required>
            				</li>
            				<li>
                				<label for="deptno">부서번호 : </label>
                				<select name="deptno" id="deptno" required>
                    				<option value="10">ACCOUNTING</option>
                    				<option value="20">RESEARCH</option>
                    				<option value="30">SALES</option>
                    				<option value="40">OPERATIONS</option>
                				</select>
            				</li>
            				<li class="sub_li">
                				<input type="submit" value="전송">
            				</li>
        				</ul>
    				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>