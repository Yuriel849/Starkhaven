package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.User;

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
	public User selectByID(Connection conn, String ID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User wrap = new User();
		
		try {
			String query = "SELECT * FROM USERS WHERE ID = ?";
			
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
		}
		
		return wrap;
	} // selectByID() 끝.
	
	// Execute "SELECT *" query
	public List<User> selectAllUsers(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	   	try {
	   		String query = "SELECT * FROM USERS";
	   		
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
		}
		return null;
	} // selectAllUsers() 끝.
	
	// Execute "SELECT * WHERE ROWNUM, * BETWEEN A AND B" query
	public List<User> selectUser(Connection conn, int firstRow, int lastRow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	
    	try {
			String query = "SELECT * FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM USERS ORDER BY USERS.NAME ASC) S) WHERE RN BETWEEN ? AND ?";
    		
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, firstRow);
    		pstmt.setInt(2, lastRow);
    		rs = pstmt.executeQuery();
    		
    		List<User> list = new ArrayList<>();
    		while (rs.next()) {
	   			String id = rs.getString(2);
	   			String pw = rs.getString(3);
	   			String name = rs.getString(4);
	   			list.add(new User(id, pw, name));
    		}
    		return list;
    	} catch(Exception e) {
    		e.printStackTrace();
       	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
    } // selectEmp() 끝.
	
	// Execute "SELECT COUNT(*)" query
	public int selectCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
    	
    	try {
			String query = "SELECT COUNT(*) FROM USERS";
    		
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			cnt = rs.getInt(1); // EMP 테이블에 레코드가 몇 개있는지 int 값을 저장한다.
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
       	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
    	return cnt;
    } // selectCount() 끝.
	
	// Execute "INSERT" query
	public int insertIntoUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO USERS VALUES(?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int updateUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE USERS SET ID = ?, PW = ?, NAME = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // update() 끝.
	
	// Execute "DELETE" query
	public int deleteFromUser(Connection conn, String ID) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "DELETE FROM USERS WHERE ID = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ID);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}