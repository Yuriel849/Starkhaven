package com.yuriel.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuriel.domain.UserVO;

public interface UserMapper {
	// namespace 설정 -> namespace는 인터페이스의 full name
	String namespace = "com.yuriel.persistence.UserMapper";
	
	// userMapper.xml에서의 ID == UserMapper 메서드의 이름 -> createUser
	public int createUser(@Param("vo") UserVO vo, @Param("authCode") String authCode) throws Exception;
	
	public UserVO login(UserVO vo) throws Exception;

	public UserVO selectOne(String email) throws Exception;

	public int selectCount() throws Exception;
	
	public List<UserVO> selectUsers (@Param("frontRow") int frontRow, @Param("countPerPage") int countPerPage) throws Exception;

	public List<UserVO> selectAllUsers () throws Exception;
	
	public int addAttach(String imgName) throws Exception;

	public void deleteAttach(@Param("ID") String ID) throws Exception;
	
	public void deleteUser(@Param("ID") String ID) throws Exception;
	
	public int modifyUser(UserVO vo) throws Exception;
}