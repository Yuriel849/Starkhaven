package handler;

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

import com.dao.EmpDAO;
import com.dto.Employee;

/**
 * Servlet implementation class EmpListHandler
 */
@WebServlet("/EmpListHandler")
public class DE_EmpListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public List<Employee> execute() {
//		RequestDispatcher reqDis = null;
    	
//		request.setCharacterEncoding("utf-8");

    	EmpDAO eDao = EmpDAO.getInstance();
						
		List<Employee> result = eDao.selectAllEmp();
			
//		if(result == null) {
//			String message = "사원 리스트 가져오기에 실패했습니다! .";
//			request.setAttribute("message", message);
//			reqDis = request.getRequestDispatcher("/OpenProject/emp/empList.jsp");
//			reqDis.forward(request, response);
//		}
		
		return result;
	}
}
