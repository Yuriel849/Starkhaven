package com.yuriel.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuriel.domain.UserVO;
import com.yuriel.service.UserService;

@Controller
@RequestMapping("/download/*")
public class DownloadController {
	@Inject
	private UserService userService;
	
	@RequestMapping("/xlsx")
	public ModelAndView getUsersXlsx() throws Exception {
		List<UserVO> result = userService.getAllUsersList();

		return new ModelAndView("usersXlsx", "result", result);
	}
	
	@RequestMapping("/pdf")
	public ModelAndView getUsersPdf() throws Exception {
		List<UserVO> result = userService.getAllUsersList();
		
		return new ModelAndView("usersPdf", "result", result);
	}
}