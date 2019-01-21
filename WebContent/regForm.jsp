<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="regMember2.jsp" method="post" enctype="multipart/form-data">
		ID  : <input type="text" name="userId"><br>
		PW  : <input type="password" name="userPw"><br>
		IMG : <input type="file" name="userImg"><br>
		<input type="submit" value="전송"> 
	</form>
</body>
</html>