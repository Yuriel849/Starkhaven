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

import com.dao.UserDAO;
import com.dto.User;

/**
 * Servlet implementation class EmpListHandler
 */
@WebServlet("/UserListHandler")
public class DE_UserListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public List<User> execute() {
//		RequestDispatcher reqDis = null;
    	
//		request.setCharacterEncoding("utf-8");

    	UserDAO uDao = UserDAO.getInstance();
						
		List<User> result = uDao.selectAllUsers();
			
//		if(result == null) {
//			String message = "사원 리스트 가져오기에 실패했습니다! .";
//			request.setAttribute("message", message);
//			reqDis = request.getRequestDispatcher("/OpenProject/emp/empList.jsp");
//			reqDis.forward(request, response);
//		}
		
		return result;
	}
}
