<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style>
	form { /* form 태그 전체에 대한 border를 설정*/
		border: 3px solid #f1f1f1
	}
	
	input[type=text], input[type=password] { /* text타입과 password타입의 input태그에 적용된다. */
		width: 100%;
		padding: 12px 20px;
		margin: 8px;
		border: 3px solid #ccc;
		box-sizing: border-box;
	}
	
	.imgcontainer { /* img태그를 포함하는 태그의 위치 설정 */
		text-align: center;
		margin: 30px 0px 15px 0px;
	}
	img.avatar { /* img 태그 중 avatar 클래스의 크기를 조정하고 동그랗게 만든다. */
		width: 300px;
		height: 300px;
		border-radius: 50%;
		/* border-radius는 모서리가 동그랗게 만드는 것 -> 그런데 width & height이 같으니까 사실 원형이 된다. */
	}
	
	button { /* "login" & "cancel" 버튼에 적용된다. */
		background-color: #4CAF50;
		color: white;
		padding: 14px 20px;
		margin: 8px 0px;
		border: none;
		cursor: pointer;
		width: 100%
	}
	
	button:hover { /* button 위에 마우스를 올리면 반투명해진다. */
		opacity: 0.7;
	}
	
	.cancelbtn {
		width: auto;
		padding: 10px 18px;
		background-color: #F44336;
	}
	
	.container {
		padding: 16px;
	}
	
	span.psw { /* "forgot password?" 부분에게만 적용된다. */
		float: right;
  		padding-top: 16px;
	}
</style>
</head>
<body>
<h2>Login Form</h2>
<%
String msg = (String) request.getAttribute("msg");
if(msg!=null) {
%>
<label><b><%=msg%></b></label>
<%} %>

<form action="/starkhaven/LoginAction" method="post">
	<div class="imgcontainer">
		<img src="Pictures\loginPic.jpg" alt="Avatar" class="avatar"><br><br>
	</div>
	
	<div class="container">
		<label><b>Username</b></label>
		<input type="text" placeholder="Enter Username" name="id" required><br>
		
		<label><b>Password</b></label>
		<input type="password" placeholder="Enter Password" name="pw" required><br>
		
		<button type="submit">Login</button>
		<input type="checkbox" checked="checked"> Remember me<br><br>
		<button type="button" class="cancelbtn">Cancel</button>
		<span class="psw"><a href="#">Forgot password?</a></span>
	</div>
</form>
</body>
</html>