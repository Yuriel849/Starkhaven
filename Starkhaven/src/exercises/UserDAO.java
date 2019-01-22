package exercises;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Dao {
	private static UserDAO udao = new UserDAO();

	private UserDAO() {
		super(null,"");
	}
	
	public static UserDAO getInstance() {
		if(udao == null) {
			udao = new UserDAO();
		}
		return udao;
	}
	
    List<User> selectAllUsers() {
    	String query = "SELECT * FROM book_ex.user_info;";
    	
    	try {
    		if(conn == null) {
    			new Dao();
    		}
    		pstmt= conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		List<User> list = new ArrayList<>();
    		while (rs.next()) {
    			String user_id = rs.getString(1);
    			String password = rs.getString(2);
    			String name = rs.getString(3);
    			String email = rs.getString(4);
    			String in_date = rs.getString(5);
    			String up_date = rs.getString(6);
    			list.add(new User(user_id, password, name, email, in_date, up_date));
    		}
    		return list;
    	} catch(Exception e) {
    		e.printStackTrace();
       	}
		return null;
    }

    User selectUser(String uId) {
    	String query = "SELECT * FROM book_ex.user_info WHERE user_id='" +uId+ "';";
    	
    	try {
    		if(conn == null) {
    			new Dao();
    		}
    		pstmt= conn.prepareStatement(query);  		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) { 
    			String user_id = rs.getString(1);
    			String password = rs.getString(2);
    			String name = rs.getString(3);
    			String email = rs.getString(4);
    			String in_date = rs.getString(5);
    			String up_date = rs.getString(6);
    			return new User(user_id, password, name, email, in_date, up_date);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
       	}
		return null;
    }
}

class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String in_date = "";
	private String up_date = "";
	
	public User(String userId, String password, String name, String email, String in_date, String up_date) {
		super();
		this.setUserId(userId);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(email);
		this.setIn_date(in_date);
		this.setUp_date(up_date);
	}

	@Override
	public String toString() {
		return "User [userId=" + getUserId() + ", password=" + getPassword() + ", name=" + getName()
				+ ", email=" + getEmail() + ", in_date=" + getIn_date() + ", up_date=" + getUp_date() + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
}