package com.yuriel.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuriel.domain.UserVO;
import com.yuriel.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserService service;
	
	// GET -> 항상 사용자가 직접 브라우저에서 접근 가능한 경우 사용한다.
	@RequestMapping(value ="/myPage", method = RequestMethod.GET)
	public void goMyPage(HttpSession session, Model model) throws Exception {
		logger.info("******************** myPage get ********************");
		
		UserVO result = service.get("yuriel@starkhaven.net");
		model.addAttribute("user", result);
	}
		
	// POST -> 외부에서 많은 정보를 입력해야 하는 경우, 브라우저 주소창에 보여져서는 안 되는 정보를 전송할 때 사용한다.
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
				  // 매개변수 -> 입력하는 데이터를 UserVO에서 수집하고, view까지 데이터를 보내야할 수도 있으니까 Model을 사용한다.
	public String loginPOST(UserVO user, Model model) throws Exception {
		logger.info("******************** login post ********************");
		logger.info(user.toString());
		
		UserVO result = service.login(user);
		if(result != null) {
			model.addAttribute("message", "성공적으로 로그인하셨습니다!");
			return "main/index";
		} else {
			model.addAttribute("message", "로그인에 실패했습니다. 다시 시도해주세요.");
			return "login/loginForm";
		}
	}
}