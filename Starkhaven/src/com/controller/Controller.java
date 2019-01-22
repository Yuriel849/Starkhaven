package com.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CommandHandler;
import com.handler.*;

// 외부 설정 파일을 이용하는 Controller -> Controller 서블릿을 한 번 컴파일한 후 다시 수정할 필요가 없다는 것이 장점!

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// <command, handler instance> 매핑 정보를 저장하는 Map
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	public void init() throws ServletException {
	// 경로를 web.xml에서 parameter로 가져온 이유는 경로가 바뀔 수 있기 때문이다 -> 경로 바꾸려면 Controller.java 수정 없이 web.xml만 수정하면 된다!
		String configFile = getInitParameter("configFile");
	// HashMap의 구버전 -> HashTable -> HashTable과 달리 String & String을 이름과 값으로 사용하는 것이 Properties
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile); // getRealPath("X") -> "X"의 절대경로 반환
		try(FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis); // prop 객체에 configFile 파일의 내용이 저장된다.
		} catch(IOException e) {
			throw new ServletException(e);
		}
	// prop 객체의 key들만 set으로 받아서 반복해서 처리하기 위한 iterator를 생성한다.
		Iterator<Object> keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command); // 특정 key와 짝인 value를 반환받는다.
			try {
			// handlerCLassName을 이름으로 갖는 클래스의 클래스객체를 반환한다.
				Class<?> handlerClass = Class.forName(handlerClassName); 
			// 객체생성을 여기서 newInstance()를 이용해서 실행하기에, Handler 클래스에 별도로 singleton pattern 적용할 필요가 없다!
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
			// Map에다가 uri & Handler 클래스의 인스턴스를 한 쌍 씩 저장한다 (Handler 클래스는 모두 CommandHandler 구현!)
				commandHandlerMap.put(command, handlerInstance);

			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	} // init() 끝.
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getRequestURI();
	// 다형성 -> CommandHandler 참조변수로 CommandHandler 인터페이스를 구현한 클래스의 인스턴스들을 참조한다!
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			viewPage = handler.execute(request, response);
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}

}
