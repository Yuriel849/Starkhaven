package com.yuriel.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuriel.domain.UserVO;

public interface UserMapper {
	// namespace 설정 -> namespace는 인터페이스의 full name
	String namespace = "com.yuriel.persistence.UserDAO";
	
	// Mapper ID -> 메서드의 이름 -> createUser
	public int createUser(UserVO vo) throws Exception;
	
	// Mapper ID -> 메서드의 이름 -> login
	public UserVO login(UserVO vo) throws Exception;

	// Mapper ID -> 메서드의 이름 -> selectOne
	public UserVO selectOne(String email) throws Exception;

	// Mapper ID -> 메서드의 이름 -> selectCount
	public int selectCount() throws Exception;
	
	// Mapper ID -> 메서드의 이름 -> selectUsers
	public List<UserVO> selectUsers (@Param("frontRow") int frontRow, @Param("countPerPage") int countPerPage) throws Exception;
	
	// Mapper ID -> 메서드의 이름 -> addAttach
	public int addAttach(String imgName) throws Exception;

	// Mapper ID -> 메서드의 이름 -> deleteAttach
	public void deleteAttach(@Param("ID") String ID) throws Exception;
	
	// Mapper ID -> 메서드의 이름 -> deleteUser
	public void deleteUser(@Param("ID") String ID) throws Exception;
}