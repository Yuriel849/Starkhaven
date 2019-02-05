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
	public void register(UserVO vo) throws Exception {
		dao.create(vo);
	}

}