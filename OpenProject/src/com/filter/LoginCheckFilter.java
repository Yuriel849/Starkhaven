package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class LoginCheckFilter
 */
public class LoginCheckFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		boolean login = false;
		
		if(session != null && session.getAttribute("userId") != null) {
			login = true;
			System.out.println("logged in");
		} 
		System.out.println("1 : login filter checking......");
		if(login) {
			chain.doFilter(request, response);
		} else {
			String message = "로그인하셔야 합니다.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/OpenProject/login/loginForm.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}