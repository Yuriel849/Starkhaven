package com.yuriel.persistence;

import com.yuriel.domain.UserVO;

public interface UserDAO {
	// namespace 설정 -> namespace는 인터페이스의 full name
	String namespace = "com.yuriel.persistence.UserDAO";
	
	// Mapper ID -> 메서드의 이름 -> createUser
	public int createUser(UserVO vo) throws Exception;
	
	// Mapper ID -> 메서드의 이름 -> login
	public UserVO login(UserVO vo) throws Exception;

	// Mapper ID -> 메서드의 이름 -> selectOne
	public UserVO selectOne(String email) throws Exception;
	
	/* 
	 * public void update(UserVO vo) throws Exception;
	 * 
	 * public void delete(Integer bno) throws Exception;
	 */
}