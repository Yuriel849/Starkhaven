package com.yuriel.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yuriel.domain.UserVO;
import com.yuriel.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private UserDAO dao;
	
	@Override
	public int register(UserVO vo) throws Exception {
		return dao.create(vo);
	}

	@Override
	public UserVO login(UserVO vo) throws Exception {
		return dao.login(vo);
	}
	
	@Override
	public UserVO get(String id) throws Exception {
		return dao.read(id);
	}
}