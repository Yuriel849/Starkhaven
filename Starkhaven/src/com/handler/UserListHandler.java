package com.handler;

import java.sql.Connection;
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

public class UserListHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnStatement = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			UserDAO uDao = UserDAO.getInstance();
			List<User> list = null;
			int firstRow = 0;
			int lastRow = 0;
			int countPerPage = Integer.parseInt(request.getParameter("countPerPage"));
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int totalCount = uDao.selectCount(conn);
			int pageTotalCount = 0;
			/* Page Total Count:
			 * 	   divides the number of messages by the number of messages per page, then calculates the remainder;
			 *     if the remainder is greater than 0 (cannot be a negative number),
			 *     then another page is required to show the remainder, so +1 to the total number of pages
			 */
			if(totalCount == 0) {
				pageTotalCount = 0;
			} else {
				pageTotalCount = totalCount / countPerPage + (totalCount % countPerPage > 0 ? 1 : 0);
			}
			if(pageNumber <= 0) { pageNumber = 1; }
			else if(pageNumber > pageTotalCount) { pageNumber = pageTotalCount; }
			int currentPageNumber = pageNumber;
			
			if(countPerPage > 0) {
				firstRow = (pageNumber - 1) * countPerPage + 1;
				lastRow = firstRow + countPerPage - 1;
				list = uDao.selectUser(conn, firstRow, lastRow);
			} else {
				currentPageNumber = 0;
				list = Collections.emptyList();
			}
			
			ListView result = new ListView(list, totalCount, currentPageNumber, countPerPage, pageTotalCount, firstRow, lastRow);
			request.setAttribute("result", result);
			
			returnStatement = "/OpenProject/user/userList.jsp";
			
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