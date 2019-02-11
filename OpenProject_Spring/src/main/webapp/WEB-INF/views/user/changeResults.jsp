<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach var="item" items="${result.list}">
		<tr>
    	    <td class="idx"></td>
    	    <td class="ident">${item.id}</td>
        	<td>${item.email}</td>
         	<td>${item.name}</td>
    		<td>
        		<a href="#" class="modify" id="modBtn">수정</a>&nbsp;<a href="#" class="delete" id="delBtn">삭제</a>
        	</td>
        </tr>
	</c:forEach>