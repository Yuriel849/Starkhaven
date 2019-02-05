package com.handler;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.dao.EmpDAO;
import com.dto.Employee;

import connector.ConnectionProvider;
import connector.JDBCUtil;

public class AddEmpHandler implements CommandHandler {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String returnStatement = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
						
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
			
			int result = eDao.insert(conn, emp);
			
			if(result != -1 && result != 0) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
				String message = "사원등록에 성공했습니다!";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/main/index.jsp";
			} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
				String message = "사원등록에 실패했습니다.";
				request.setAttribute("message", message);
				returnStatement = "/OpenProject/emp/empList.jsp";
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