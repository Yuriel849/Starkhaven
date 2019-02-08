package com.yuriel.service;

import com.yuriel.domain.UserVO;

public interface LoginService {
	public UserVO login(UserVO vo) throws Exception;
}