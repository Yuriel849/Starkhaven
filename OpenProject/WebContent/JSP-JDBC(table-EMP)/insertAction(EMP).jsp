<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="emp" class="com.dto.Employee" />
<jsp:setProperty name="emp" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	/* emp 객체의 HIREDATE 변수 값을 가져온다 (이것은 insertForm(EMP)에서 사용자가 선택한 날짜 값이다) */
	String dateStr = emp.getHiredate();
	/* dateStr에 담긴 날짜를 "-"를 기준으로 나눈다 */
	String[] dateSplit = dateStr.split("-");
	/* insertForm(EMP)에서 넘어온 날짜 형태를 Oracle에 INSERT하기 위한 날짜 형태로 바꾼다. */
	String date = dateSplit[1] + "-" + dateSplit[2] + "-" + dateSplit[0];
	/* emp 객체의 HIREDATE 변수 값을 바꾼다 */
	emp.setHiredate(date);

	/* emp 객체를 가지고 Oracle에 INSERT한다 */
	EmpDAO eDAO = EmpDAO.getInstance();
	eDAO.insert(emp);
%>
</body>
</html>