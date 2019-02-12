package com.yuriel.service;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.yuriel.domain.AuthDTO;
import com.yuriel.persistence.AuthMapper;

@Service
public class AuthServiceImpl implements AuthService {
	@Inject
	private SqlSessionTemplate template;
	
	@Inject
	private Aes256 aes256;
	
	private AuthMapper mapper;
	
	@Override
	public int authenticate(String email, String authCode) throws Exception {
		mapper = template.getMapper(AuthMapper.class);
		
		email = aes256.encrypt(email);
		
		AuthDTO res = mapper.selectOneAuth(email);
		int result = 0;	
		
		if(res != null && email.equals(res.getEmail()) && authCode.equals(res.getAuthCode())) {
			result = 1;
		}
		
		return result;
	}
	
	@Override
	public void deleteAuthCode(String email) throws Exception {
		mapper = template.getMapper(AuthMapper.class);
		
		email = aes256.encrypt(email);
		
		mapper.deleteAuthCode(email);
	}
}