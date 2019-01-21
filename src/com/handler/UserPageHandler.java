package com.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.CommandHandler;
import com.dao.UserDAO;
import com.dto.User;

import connector.ConnectionProvider;
import connector.JDBCUtil;

public class UserPageHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String returnStatement = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			User result = new User();
			UserDAO uDao = UserDAO.getInstance();
			String userID = (String) session.getAttribute("userID");
			if(userID != null) { // session 속에 userID 속성이 있다면 이미 로그인된 상태 -> 회원가입 불가
				result = uDao.selectByID(conn, userID);
				request.setAttribute("result", result);
				returnStatement = "/OpenProject/user/myPage.jsp";
			} else {
				String message = "로그인하셔야 합니다.";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/login/loginForm.jsp";
			}
			conn.commit();
		} catch (Exception e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return returnStatement;
	}
}