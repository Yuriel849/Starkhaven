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

public class LoginHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String returnStatement = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
						
			if(session.getAttribute("userID") != null) { // session 속에 userID 속성이 있다면 이미 로그인된 상태 -> 회원가입 불가
				String message = "이미 로그인된 상태입니다.";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/main/index.jsp";
			} else {
				User user = new User();
				UserDAO uDao = UserDAO.getInstance();
				
				String inID = request.getParameter("ID");
				String inPW = request.getParameter("PW");
				user.setId(inID);
				user.setPw(inPW);
				
				User result = uDao.selectByID(conn, user.getId());
				
				if(inID.equals(result.getId()) && inPW.equals(result.getPw())) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
					session.setAttribute("userID", inID);
					returnStatement = "/OpenProject/main/index.jsp";
				} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
					String message = "로그인에 실패했습니다.";
					request.setAttribute("message", message);
					returnStatement = "/OpenProject/login/loginForm.jsp";
				}
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