package com.handler;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

public class UserListHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnStatement = null;
		
		request.setCharacterEncoding("utf-8");
		
    	UserDAO uDao = UserDAO.getInstance();
		
		List<User> result = uDao.selectAllUsers();
		request.setAttribute("list", result);
		
		returnStatement = "/OpenProject/user/userList.jsp";
		
		return returnStatement;
	}
}