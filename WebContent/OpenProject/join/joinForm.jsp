<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Starkhaven</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/OpenProject.css">
</head>
<body>
	<jsp:include page="../main/header.jsp" flush="false"></jsp:include>
	<h1 class="joinH1">회원가입</h1>
	<div class="form_wrap">
        <form action="joinAction.jsp" method="post">
            <ul>
                <li>
                    <label for="userID">아이디 (이메일)</label>
                    <input type="text" placeholder="ID (email address)" id="userID" name="userID" maxlength="20">
                </li>
                <li>
                    <label for="userPW">비밀번호</label>
                    <input type="password" placeholder="password" id="userPW" name="userPW" maxlength="20">
                </li>
                <li>
                    <label for="userName">이름</label>
                    <input type="text" placeholder="name" id="userName" name="userName" maxlength="20">
                </li>
                <li>
                    <label for="userImg">사진</label>
                    <input type="file" id="userImg" name="userImg" accept="image/">
                </li>
                <li>
                    <input type="submit" value="등록">
                </li>
            </ul>
        </form>
	</div>
</body>
</html>