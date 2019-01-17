package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.User;

import connector.ConnectionProvider;
import connector.JDBCUtil;

public class UserDAO {
	// Singleton 패턴 -> UserDAO 객체가 단 하나만 만들어질 수 있도록 제어
	private static UserDAO uDao = new UserDAO();
	
	private UserDAO() {
		super();
	}
	
	// 외부에서 생성자에 직접 접근 못하니까 getInstance()로 대신 객체를 반환받도록.
	public static UserDAO getInstance() {
		if(uDao == null) { uDao = new UserDAO(); }
		
		return uDao;
	}
	
	// Execute "SELECT WHERE ID = ?" query
	public User selectByID(String ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User wrap = new User();
		
		try {
			String query = "SELECT * FROM USERS WHERE ID = ?";

			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				wrap.setId(rs.getString(1));
				wrap.setPw(rs.getString(2));
				wrap.setName(rs.getString(3));
			}
		} catch(Exception e) {
		 	e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
			JDBCUtil.close(conn);
		}
		
		return wrap;
	} // selectByID() 끝.
	
	// Execute "SELECT *" query
	public List<User> selectAllUsers() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	   	try {
	   		String query = "SELECT * FROM USERS";
	   		
			conn = ConnectionProvider.getConnection();

	   		pstmt = conn.prepareStatement(query);
	   		rs = pstmt.executeQuery();
	   		
	   		List<User> list = new ArrayList<>();
	   		while (rs.next()) {
	   			String id = rs.getString(1);
	   			String pw = rs.getString(2);
	   			String name = rs.getString(3);
	   			list.add(new User(id, pw, name));
	   		}
	   		return list;
	   	} catch(Exception e) {
	   		e.printStackTrace();
	   	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
			JDBCUtil.close(conn);
		}
		return null;
	} // selectAllUsers() 끝.
	
	// Execute "INSERT" query
	public int insertIntoUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO USERS VALUES(?, ?, ?)";

			conn = ConnectionProvider.getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int updateUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE USERS SET ID = ?, PW = ?, NAME = ?";

			conn = ConnectionProvider.getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // update() 끝.
	
	// Execute "DELETE" query
	public int deleteFromUser(String ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String query = "DELETE FROM USERS WHERE ID = ?";

			conn = ConnectionProvider.getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ID);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(conn);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}