package com.yuriel.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuriel.domain.UserVO;

public class UserRowMapper implements RowMapper<UserVO> {
	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO vo = new UserVO();
		
		vo.setId(rs.getString("userId"));
		vo.setName(rs.getString("userName"));
		vo.setPw(rs.getString("userPassword"));
		
		return vo;
	}
}