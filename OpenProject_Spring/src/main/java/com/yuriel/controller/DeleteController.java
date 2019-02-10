package com.yuriel.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuriel.domain.UserListVO;
import com.yuriel.service.UserService;

@Controller
public class DeleteController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);

	@Inject
	private UserService service;
	
	// GET -> 항상 사용자가 직접 브라우저에서 접근 가능한 경우 사용한다.
	@RequestMapping(value ="/deleteUser", method = RequestMethod.GET)
	public String deleteGET(String ID, int countPerPage, int pageNumber, Model model) throws Exception {
		logger.info("******************** delete get ********************");
		
		System.out.println(ID + ", " + countPerPage + ", " + pageNumber);
		
		service.deleteUser(ID);
		
		UserListVO result = service.getUserList(countPerPage, pageNumber);
		System.out.println("USERLISTVO IS..... " + result);
		model.addAttribute("result", result);
		
		return "user/changeResults";
	}
}
