package com.yuriel.service;

import com.yuriel.domain.UserVO;

public interface UserService {
	public int createUser(UserVO vo) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;
	
	public UserVO getUser(String email) throws Exception;
}