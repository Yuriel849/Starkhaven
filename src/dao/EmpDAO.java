package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connector.ConnectionManagerOracle;
import dto.Employee;

public class EmpDAO extends ConnectionManagerOracle {
	// Singleton pattern
	private static EmpDAO eDAO = new EmpDAO();
	private EmpDAO() {}
	
	// 외부에서 생성자에 직접 접근 못하니까 getInstance()로 대신 객체를 반환받도록.
	public static EmpDAO getInstance() {
		if(eDAO == null) { // Singleton 패턴 -> EmpDAO 객체가 단 하나만 만들어질 수 있도록 제어
			eDAO = new EmpDAO();
		}
		return eDAO;
	}
	
	// Execute "SELECT WHERE EMPNO = ?" query
	public Employee selectByEmpNo(String empno) {
		ResultSet rs = null;
		Employee wrap = new Employee();
		
		try {
			String query = "SELECt * FROM EMP WHERE EMPNO = ?";
			
			PreparedStatement pstmt = eDAO.conn.prepareStatement(query);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				wrap.setEMPNO(rs.getInt(1));
				wrap.setENAME(rs.getString(2));
				wrap.setJOB(rs.getString(3));
				wrap.setMGR(rs.getInt(4));
				wrap.setHIREDATE(rs.getString(5));
				wrap.setSAL(rs.getDouble(6));
				wrap.setCOMM(rs.getDouble(7));
				wrap.setDEPTNO(rs.getInt(8));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return wrap;
	} // selectByEmpNo() 끝.
	
	// Execute "INSERT" query
	public int insert(Employee emp) {
		try {
			String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, to_date(?, 'dd-mm-yyyy'), ?, ?, ?)";

			PreparedStatement pstmt = eDAO.conn.prepareStatement(query);
			pstmt.setInt(1, emp.getEMPNO());
			pstmt.setString(2, emp.getENAME());
			pstmt.setString(3, emp.getJOB());
			pstmt.setInt(4, emp.getMGR());
			pstmt.setString(5, emp.getHIREDATE());
			pstmt.setDouble(6, emp.getSAL());
			pstmt.setDouble(7, emp.getCOMM());
			pstmt.setInt(8, emp.getDEPTNO());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int update(Employee emp) {
		try {
			String query = "UPDATE EMP SET ENAME = ?, JOB = ?, MGR = ?, HIREDATE = to_date(?, 'dd-mm-yyyy'), SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";
					
			PreparedStatement pstmt = eDAO.conn.prepareStatement(query);
			pstmt.setString(1, emp.getENAME());
			pstmt.setString(2, emp.getJOB());
			pstmt.setInt(3, emp.getMGR());
			pstmt.setString(4, emp.getHIREDATE());
			pstmt.setDouble(5, emp.getSAL());
			pstmt.setDouble(6, emp.getCOMM());
			pstmt.setInt(7, emp.getDEPTNO());
			pstmt.setInt(8, emp.getEMPNO());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // update() 끝.
	
	// Execute "DELETE" query
	public int delete(String empno) {
		try {
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			
			PreparedStatement pstmt = eDAO.conn.prepareStatement(query);
			pstmt.setString(1, empno);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}

