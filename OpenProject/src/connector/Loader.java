package connector;

import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Loader
 */
@SuppressWarnings("serial")
@WebServlet("/Loader")
public class Loader extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		try {
			String drivers = config.getInitParameter("jdbcdriver"); // web.xml에서 init-param을 가져온다
			StringTokenizer st = new StringTokenizer(drivers, ","); // 쉼표를 기준으로 drivers의 내용을 구분짓는다 (web.xml에 복수의 드라이버가 등록되었을 경우 필요!)
			while(st.hasMoreTokens()) {
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver); // DB 드라이버 로딩
				System.out.println("DATABASE DRIVER LOADED!");
			}
		} catch(Exception e) {
			throw new ServletException(e);
		}
	} // init() 끝.
} // Loader 클래스 끝.