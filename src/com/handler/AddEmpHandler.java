package com.handler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.dao.EmpDAO;
import com.dto.Employee;

public class AddEmpHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String returnStatement = null;
		
		request.setCharacterEncoding("utf-8");
		
		Employee emp = new Employee();
		EmpDAO eDao = EmpDAO.getInstance();
		
		emp.setEmpno((int) Integer.parseInt(request.getParameter("empno")));
		emp.setEname(request.getParameter("ename"));
		emp.setJob(request.getParameter("job"));
		emp.setMgr((int) Integer.parseInt(request.getParameter("mgr")));
		emp.setHiredate(request.getParameter("hiredate"));
		emp.setSal((double) Double.parseDouble(request.getParameter("sal")));
		emp.setComm((double) Double.parseDouble(request.getParameter("comm")));
		emp.setDeptno((int) Integer.parseInt(request.getParameter("deptno")));
		File img = null;
		
		int result = eDao.insert(emp);
		
		if(result != -1 && result != 0) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
			String message = "사원등록에 성공했습니다!";
			request.setAttribute("message", message);
			returnStatement = "/OpenProject/main/index.jsp";
		} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
			String message = "사원등록에 실패했습니다.";
			request.setAttribute("message", message);
			returnStatement = "/OpenProject/emp/empList.jsp";
		}
		
		return returnStatement;
	}
}