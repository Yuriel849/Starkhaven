package com.yuriel.service;

import javax.servlet.http.HttpServletRequest;

import com.yuriel.domain.UserListVO;
import com.yuriel.domain.UserVO;

public interface UserService {
	public int createUser(UserVO vo, HttpServletRequest request) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;
	
	public UserVO getUser(String email) throws Exception;
	
	public UserListVO getUserList(int count, int page) throws Exception;
	
	public void deleteUser(String ID) throws Exception;
}