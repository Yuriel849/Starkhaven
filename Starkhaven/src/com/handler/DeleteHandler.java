package com.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.dao.UserDAO;
import com.dto.ListView;
import com.dto.User;

import connector.ConnectionProvider;
import connector.JDBCUtil;

public class DeleteHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("ID");
		String returnStatement = null;
		Connection conn = null;

		System.out.println("parameter : " + search);
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			UserDAO uDao = UserDAO.getInstance();
			int result = uDao.deleteFromUser(conn, search);

			List<User> list = null;
			int firstRow = 0;
			int lastRow = 0;
			int countPerPage = Integer.parseInt(request.getParameter("countPerPage"));
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int totalCount = uDao.selectCount(conn);
			int pageTotalCount = 0;			
			
			if(totalCount == 0) {
				pageTotalCount = 0;
			} else {
				pageTotalCount = totalCount / countPerPage + (totalCount % countPerPage > 0 ? 1 : 0);
			}
			if(pageNumber <= 0) { pageNumber = 1; }
			else if(pageNumber > pageTotalCount) { pageNumber = pageTotalCount; }
			
			if(countPerPage > 0) {
				firstRow = (pageNumber - 1) * countPerPage + 1;
				lastRow = firstRow + countPerPage - 1;
				System.out.println(firstRow);
				System.out.println(pageNumber);
				list = uDao.selectUser(conn, firstRow, lastRow);
			} else {
				list = Collections.emptyList();
			}

			System.out.println(firstRow);
			System.out.println(list);
			request.setAttribute("information", list);
			request.setAttribute("lineNum", firstRow);
			
			if(result != -1) {
				returnStatement = "/OpenProject/user/changeResults.jsp";
			} else {
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