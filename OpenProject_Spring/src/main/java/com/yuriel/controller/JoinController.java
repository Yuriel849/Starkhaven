package com.yuriel.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuriel.domain.UserVO;
import com.yuriel.service.UserService;

@Controller
@RequestMapping("/join/*")
public class JoinController {
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

	@Inject
	private UserService service;
	
	// GET -> 항상 사용자가 직접 브라우저에서 접근 가능한 경우 사용한다.
	@RequestMapping(value ="/joinForm", method = RequestMethod.GET)
	public void registGET(UserVO user, Model model) throws Exception {
		logger.info("******************** register get ********************");
	}
		
	// POST -> 외부에서 많은 정보를 입력해야 하는 경우, 브라우저 주소창에 보여져서는 안 되는 정보를 전송할 때 사용한다.
	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
				  // 매개변수 -> 입력하는 데이터를 UserVO에서 수집하고, view까지 데이터를 보내야할 수도 있으니까 Model을 사용한다.
	public String registPOST(UserVO user, Model model) throws Exception {
		logger.info("******************** register post ********************");
		logger.info(user.toString());
		
		int result = service.register(user);
		
		if(result > 0) {
			model.addAttribute("message", "성공적으로 회원가입하셨습니다!");
			return "main/index";
		} else {
			model.addAttribute("message", "회원가입에 실패했습니다. 다시 입력해주세요.");
			return "join/joinForm";
		}		
	}
}
