package exercises;

import java.io.*;
import java.net.URLEncoder;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// LoginAction03_DB_connect.java servlet & DB를 연동하기

@WebServlet("/LoginAction_DB_connect")
public class LoginAction_DB_connect extends HttpServlet { // HttpServlet 상속
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 시 넘어온 값을 읽는다.
		HttpSession session = request.getSession();
		
//		System.out.println("getting");
		// DB 연동이 제대로 되는지 확인하는 코드
//		String id = "dolphin";
//		String pw = "22";
		
		// id & pw가 null이 될 수 없다 -> loginForm에서 "required" 속성을 지녔기에
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String chk = request.getParameter("checked");
		String compareId = "";
		String comparePwd = "";

		// request로 값이 제대로 넘어왔는지 확인하는 코드
//		System.out.println(id);
//		System.out.println(pw);
//		System.out.println(chk);

		UserDAO udao = UserDAO.getInstance(); // UserDAO 객체를 만든다.
        User user = udao.selectUser(id); // id와 일치하는 user_id를 가진 정보를 DB에서 받아온다.
        if(user!=null) {
        	compareId = user.getUserId();
        	comparePwd = user.getPassword();
        }
		
        // DB 연동이 제대로 되는지 확인하는 코드
//      System.out.println(user);
//      System.out.println(compareId);
//      System.out.println(comparePwd);
        
        // user가 null인지 체크 -> null이라면 다시 id와 일치하는 user_id가 DB에 없는 것 -> 다시 loginForm으로 돌아가기
        // user가 null이 아니라면 id & compareId, pw & comparePwd를 비교해서
        	// 일치하지 않으면 다시 loginForm으로 돌아가기
        	// 일치하면 index.jsp로 넘어가기
        
		
		if(user!=null && id.equals(compareId) && pw.equals(comparePwd)) {
			// DB에서 정보를 가져왔고, 해당 정보와 id & pw가 일치하는 경우
			session.removeAttribute("loginFailure");
			response.sendRedirect("/starkhaven");
		} else { // DB에서 정보를 가져오지 못했거나 (user==null) DB에서 정보를 가져왔으나 해당 정보와 id & pw가 일치하지 않을 경우
//			request.setAttribute("msg", "아이디 또는 비밀번호가 틀립니다."); // request 객체에 메시지를 저장
			if(chk!=null && chk.equals("on")) {
				Cookie cookie = new Cookie("id", URLEncoder.encode(id, "utf-8"));
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("id", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		
		session.setAttribute("loginFailure", "아이디 또는 비밀번호를 잘못 입력했습니다.");

		response.sendRedirect("/starkhaven/loginForm_DB_connect.jsp");
		// forward로는 cookie가 보내지지만 자동으로 로딩되지 않는다 (내가 새로고침해야 된다) -> redirect 사용할 것!
//		RequestDispatcher reqDis = request.getRequestDispatcher("/loginForm03_DB_connect.jsp");
//		reqDis.forward(request, response);
		}
	} // doGet() 끝.

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 요청이 GET 요청과 동일하게 처리되는 경우.
		// doPost()를 작성하는 대신, doGet()을 호출한다.
//		System.out.println("posting");
		doGet(request, response);
	} // doPost() 끝.
}