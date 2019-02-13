package com.yuriel.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.yuriel.domain.UserListVO;
import com.yuriel.domain.UserVO;
import com.yuriel.service.UserService;

@Controller
public class ModifyController {
	private static final Logger logger = LoggerFactory.getLogger(ModifyController.class);

	@Inject
	private UserService service;
	
	// GET -> 항상 사용자가 직접 브라우저에서 접근 가능한 경우 사용한다.
	@PostMapping("/modifyUser")
	public String modifyPOST(UserVO user, int countPerPage, int pageNumber, Model model) throws Exception {
		logger.info("******************** modify user POST ********************");
		
		System.out.println(user + ", " + countPerPage + ", " + pageNumber);
		
		int res = service.modifyUser(user);
		
		if(res != 1) {
			model.addAttribute("message", "입력하는데 실패했습니다. 다시 시도해주세요.");
		}
		
		UserListVO result = service.getUserList(countPerPage, pageNumber);
		System.out.println("USERLISTVO IS..... " + result);
		model.addAttribute("result", result);
		
		return "user/changeResults";
	}
}
