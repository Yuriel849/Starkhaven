package com.yuriel.persistence;

import com.yuriel.domain.AuthDTO;

public interface AuthMapper {
	// namespace 설정 -> namespace는 인터페이스의 full name
	String namespace = "com.yuriel.persistence.AuthMapper";
	
	// userMapper.xml에서의 ID == UserMapper 메서드의 이름 -> selectOneAuth
	public AuthDTO selectOneAuth(String email) throws Exception;
	
	public void deleteAuthCode(String email) throws Exception;
}