package com.yuriel.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.yuriel.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	private JdbcTemplate template;	
	
	@Override
	public int create(UserVO vo) throws Exception {
		int result = 0;
		try {
			result = template.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						String sql = "insert into users (userId, userName, userPassword) value (?, ?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, vo.getId());
						pstmt.setString(2, vo.getName());
						pstmt.setString(3, vo.getPw());
						
						return pstmt;
					}
				});
		} catch(Exception e) {}
		return result;
	}
	
	@Override
	public UserVO login(UserVO vo) throws Exception {
		UserVO result = null;
		try {
			String sql = "select * from users where userId = ?";
			
			result = template.queryForObject(sql, new UserRowMapper(), vo.getId());
		} catch(Exception e) {}
		return result;
	}
	
	@Override
	public UserVO read(String id) throws Exception {
		UserVO result = null;
		
		try {
			String sql = "select * from users where userId = ?";
			
			result = template.queryForObject(sql, new UserRowMapper(), id);
		} catch(Exception e) {}
		
		return result;
	}
	
	
	
	/*
	 * @Override public void update(UserVO vo) throws Exception {
	 * session.update(namespace + ".update", vo); }
	 * 
	 * @Override public void delete(Integer bno) throws Exception {
	 * session.delete(namespace + ".delete", bno); }
	 */
}