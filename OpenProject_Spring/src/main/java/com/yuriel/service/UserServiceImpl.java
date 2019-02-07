package com.yuriel.service;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.yuriel.domain.UserVO;
import com.yuriel.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private SqlSessionTemplate template;
	
	// 인터페이스 사용하기
	private UserDAO dao;
 // private UserDAO dao = template.getMapper(UserDAO.class); -> IMPOSSIBLE! -> DI는 객체 생성 후 실행, 이것은 생성할 떄 실행
	
	@Override
	public int createUser(UserVO vo) throws Exception {
		dao = template.getMapper(UserDAO.class);
		return dao.createUser(vo);
	}
	
	@Override
	public UserVO login(UserVO vo) throws Exception {
		dao = template.getMapper(UserDAO.class);
		return dao.login(vo);
	}
	
	@Override
	public UserVO getUser(String email) throws Exception {
		dao = template.getMapper(UserDAO.class);
		return dao.selectOne(email);
	}
}