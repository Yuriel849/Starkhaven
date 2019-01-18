package com.handler;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.dao.EmpDAO;
import com.dto.Employee;
import com.dto.ListView;

public class EmpListHandler implements CommandHandler {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String returnStatement = null;
		EmpDAO eDao = EmpDAO.getInstance();
		List<Employee> list = null;
		int firstRow = 0;
		int lastRow = 0;
		int countPerPage = Integer.parseInt(request.getParameter("countPerPage"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int totalCount = eDao.selectCount();
		int currentPageNumber = pageNumber;
					
		if(countPerPage > 0) {
			firstRow = (pageNumber - 1) * countPerPage + 1;
			lastRow = firstRow + countPerPage - 1;
			list = eDao.selectEmp(firstRow, lastRow);
		} else {
			currentPageNumber = 0;
			list = Collections.emptyList();
		}

		ListView result = new ListView(list, totalCount, currentPageNumber, countPerPage, firstRow, lastRow);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", result.getPageTotalCount());
		
		returnStatement = "/OpenProject/emp/empList.jsp";
		
		return returnStatement;
	}
}