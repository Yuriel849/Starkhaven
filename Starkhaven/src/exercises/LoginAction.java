package exercises;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet { // HttpServlet 상속
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 시 넘어온 값을 읽는다.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if((id!=null) && (pw!=null) && id.equals("asdf") && pw.equals("1234")) {
			// id & pw가 일치하는 경우
			response.sendRedirect("/starkhaven");
		} else {
			// id & pw가 일치하지 않는 경우
			request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다."); // request 객체에 메시지를 저장
			
			RequestDispatcher reqDis = request.getRequestDispatcher("/loginForm.jsp");
			reqDis.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		// POST 요청이 GET 요청과 동일하게 처리되는 경우.
			// doPost()를 작성하는 대신, doGet()을 호출한다.		

	}

}
