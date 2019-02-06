package com.yuriel.persistence;

import com.yuriel.domain.UserVO;

public interface UserDAO {
	public int create(UserVO vo) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;
	
	public UserVO read(String id) throws Exception;
	
	/* 
	 * public void update(UserVO vo) throws Exception;
	 * 
	 * public void delete(Integer bno) throws Exception;
	 */
}