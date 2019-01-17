package com.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.dao.EmpDAO;
import com.dto.Employee;

public class EmpListHandler implements CommandHandler {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnStatement = null;
		
		request.setCharacterEncoding("utf-8");

		EmpDAO eDao = EmpDAO.getInstance();
		
		List<Employee> result = eDao.selectAllEmp();
		request.setAttribute("list", result);
		
		returnStatement = "/OpenProject/emp/empList.jsp";
		
		return returnStatement;
	}
}