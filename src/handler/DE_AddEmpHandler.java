package handler;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.EmpDAO;
import com.dto.Employee;

/**
 * Servlet implementation class JoinHandler
 */
@WebServlet("/AddEmpHandler")
public class DE_AddEmpHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DE_AddEmpHandler() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher reqDis = null;
		
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
			System.out.println(result);
			
			if(result != -1 && result != 0) { // DB에 INSERT 성공한 경우 -> 회원가입 성공, 이제 로그인으로
				String message = "사원등록에 성공했습니다!";
				request.setAttribute("message", message);
				reqDis = request.getRequestDispatcher("/OpenProject/main/index.jsp");
			} else { // DB에 INSERT 실패한 경우 -> 다시 회원가입 양식으로
				String message = "사원등록에 실패했습니다.";
				request.setAttribute("message", message);
				reqDis = request.getRequestDispatcher("/OpenProject/emp/empList.jsp");
			}
		
		reqDis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
