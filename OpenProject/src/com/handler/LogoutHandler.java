package com.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.CommandHandler;


public class LogoutHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String returnStatement = null;
		try {
			session.invalidate();
			String message = "로그아웃하였습니다.";
			request.setAttribute("message", message);
			returnStatement = "/OpenProject/main/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnStatement;
	}
}