package com.yuriel.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuriel.domain.UserListVO;
import com.yuriel.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserService service;
	
	// GET -> 항상 사용자가 직접 브라우저에서 접근 가능한 경우 사용한다.
	@RequestMapping(value="/myPage", method = RequestMethod.GET)
	public void goMyPage(HttpSession session, Model model) throws Exception {
		logger.info("******************** myPage get ********************");
	}
	
	@RequestMapping(value="/list/{count}/{page}", method = RequestMethod.GET)
	public String getUserList(@PathVariable("count") int count, @PathVariable("page") int page, Model model) throws Exception {
		logger.info("******************** userList get ********************");

		UserListVO result = service.getUserList(count, page);
		
		model.addAttribute("result", result);
		
		return "user/userList";
	}
}