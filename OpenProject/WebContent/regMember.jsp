<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	/* 값이 제대로 전송되는지 확인하기 위한 파일 (실제로 저장되지는 않는다!) */

	String userId = "";
	String userPw = "";
	String userImg = "";
	
	// 1. multipart/form-data 여부 확인
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if(isMultipart) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 3. 업로드 요청을 처리하는 ServletFileUpload 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 4. 업로드 요청에서 FileItem 목록 가져오기
		List<FileItem> items = upload.parseRequest(request);
		
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			// 5. FileItem이 폼 입력 항목인지 여부에 따라 알맞게 처리
			if(item.isFormField()) { // 텍스트 필드인 경우 -> value 값만 필요!
				String name = item.getFieldName();
				if(name.equals("userId")) { userId = item.getString("UTF-8"); }
				if(name.equals("userPw")) { userPw = item.getString("UTF-8"); }
			} else { // 파일인 경우
				String name = item.getFieldName();
				if(name.equals("userImg")) {
					userImg = item.getName() + " : " + item.getSize();					
				}
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Registration</title>
</head>
<body>
	<h1>
		 ID  : <%=userId %><br>
		 PW  : <%=userPw %><br>
		File : <%=userImg %><br>
	</h1>

</body>
</html>