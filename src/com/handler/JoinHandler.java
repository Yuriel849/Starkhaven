package com.handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.CommandHandler;
import com.dao.UserDAO;
import com.dto.User;

public class JoinHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String returnStatement = null;
		
		request.setCharacterEncoding("utf-8");
		
		if(session.getAttribute("userID") != null) { // session 속에 userID 속성이 있다면 이미 로그인된 상태 -> 회원가입 불가
			String message = "이미 로그인된 상태입니다.";
			request.setAttribute("message", message);
			returnStatement = "/OpenProject/main/index.jsp";
		} else {
			User user = new User();
			UserDAO uDao = UserDAO.getInstance();
			
			user.setId(request.getParameter("ID"));
			user.setPw(request.getParameter("PW"));
			user.setName(request.getParameter("Name"));
			File img = null;
			
			int result = uDao.insertIntoUser(user);
			
			if(result != -1) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
				String message = "회원가입에 성공했습니다! 이제 로그인해주세요.";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/main/index.jsp";
			} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
				String message = "회원가입에 실패했습니다.";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/join/joinForm.jsp";
			}
		}
		
		return returnStatement;
	}
}