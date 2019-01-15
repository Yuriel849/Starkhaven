package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connector.ConnectionManagerOracle;
import dto.User;

public class UserDAO extends ConnectionManagerOracle {
	public UserDAO() {
		super();
	}
	
	// Execute "SELECT WHERE ID = ?" query
	public User selectByID(String ID) {
		ResultSet rs = null;
		User wrap = new User();
		
		try {
			String query = "SELECT * FROM USERS WHERE ID = ?";
			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, ID);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				wrap.setID(rs.getString(1));
				wrap.setPW(rs.getString(2));
				wrap.setName(rs.getString(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return wrap;
	} // selectByID() 끝.
	
	// Execute "INSERT" query
	public int insertIntoUser(User user) {
		try {
			String query = "INSERT INTO USERS VALUES(?, ?, ?)";

			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, user.getID());
			pstmt.setString(2, user.getPW());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int updateUser(User user) {
		try {
			String query = "UPDATE USERS SET ID = ?, PW = ?, NAME = ?";
					
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, user.getID());
			pstmt.setString(2, user.getPW());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // update() 끝.
	
	// Execute "DELETE" query
	public int deleteFromUser(String ID) {
		try {
			String query = "DELETE FROM USERS WHERE ID = ?";
			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, ID);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}

