package com.yuriel.service;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.yuriel.domain.UserVO;
import com.yuriel.persistence.UserMapper;

@Service
public class LoginServiceImpl implements LoginService {
	@Inject
	private SqlSessionTemplate template;
	
	// 인터페이스 사용하기
	private UserMapper mapper;
 // private UserMapper mapper = template.getMapper(UserMapper.class); -> IMPOSSIBLE! -> DI는 객체 생성 후 실행, 이것은 생성할 떄 실행
	
	@Override
	public UserVO login(UserVO vo) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		return mapper.login(vo);
	}
}