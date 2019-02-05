package com.yuriel.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.yuriel.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	private JdbcTemplate template;	
	
	@Override
	public void create(UserVO vo) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						String sql = "insert into users (userId, userName, userPassword) value (?, ?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, vo.getId());
						pstmt.setString(2, vo.getName());
						pstmt.setString(3, vo.getPw());
						
						return pstmt;
					}
				}, keyHolder);
		
		Number keyValue = keyHolder.getKey();
		System.out.println("keyValue : " + keyValue);
	}
	
	/*
	 * @Override public UserVO read(Integer bno) throws Exception { return
	 * session.selectOne(namespace + ".read", bno); }
	 * 
	 * @Override public void update(UserVO vo) throws Exception {
	 * session.update(namespace + ".update", vo); }
	 * 
	 * @Override public void delete(Integer bno) throws Exception {
	 * session.delete(namespace + ".delete", bno); }
	 */
}