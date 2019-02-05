package com.yuriel.persistence;

import com.yuriel.domain.UserVO;

public interface UserDAO {
	public void create(UserVO vo) throws Exception;
	
	/*
	 * public UserVO read(Integer bno) throws Exception;
	 * 
	 * public void update(UserVO vo) throws Exception;
	 * 
	 * public void delete(Integer bno) throws Exception;
	 */
}