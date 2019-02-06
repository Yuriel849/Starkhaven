package com.yuriel.service;

import com.yuriel.domain.UserVO;

public interface UserService {
	public int register(UserVO vo) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;
	
	public UserVO get(String id) throws Exception;
}