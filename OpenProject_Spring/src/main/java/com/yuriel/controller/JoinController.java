package com.yuriel.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuriel.domain.UserVO;
import com.yuriel.service.AuthService;
import com.yuriel.service.UserService;

@Controller
@RequestMapping("/join/*")
public class JoinController {
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

	@Inject
	private UserService userService;
	
	@Inject
	private AuthService authService;
	
	@RequestMapping(value ="/joinForm", method = RequestMethod.GET)
	public void registGET(UserVO user, Model model) throws Exception {
		logger.info("******************** register get ********************");
	}
		
	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
	public String registPOST(UserVO user, HttpServletRequest request, Model model) throws Exception {
		logger.info("******************** register post ********************");
		
		// 저장될 경로를 계산한다.
		String uploadPath = "/resources/photos/";
		String dir = request.getSession().getServletContext().getRealPath(uploadPath);

		// service에게 UserVO 객체, 고유번호 UUID, 경로 dir를 제공한다.
		int result = userService.createUser(user, dir);
		
		if(result > 0) {
			model.addAttribute("message", "성공적으로 회원가입하셨습니다!");
			return "main/index";
		} else {
			model.addAttribute("message", "회원가입에 실패했습니다. 다시 입력해주세요.");
			return "join/joinForm";
		}		
	}
	
	@RequestMapping(value = "/auth/{email}/{code}", method = RequestMethod.GET)
	public String joinAuth(@PathVariable("email") String email, @PathVariable("code") String code, Model model) throws Exception {
		if(email != null && code != null) {
			int result = authService.authenticate(email, code);
			if(result == 1) {
				authService.deleteAuthCode(email);
				model.addAttribute("message", "본인 인증에 성공하셨습니다!");
			} else {
				model.addAttribute("message", "본인 인증에 실패했습니다. 관리자에게 문의 주십시요.");
			}
		}
		
		return "main/index";
	}
}
