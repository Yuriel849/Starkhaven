package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connector.ConnectionManagerOracle;
import dto.Employee;

public class EmpDAO extends ConnectionManagerOracle {
	public EmpDAO() {
		super();
	}
	
	// Execute "SELECT WHERE EMPNO = ?" query
	public Employee selectByEmpNo(String empno) {
		ResultSet rs = null;
		Employee wrap = new Employee();
		
		try {
			String query = "SELECt * FROM EMP WHERE EMPNO = ?";
			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
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
	
	// Execute "SELECT *" query
	public List<Employee> selectAllEmp() {
		ResultSet rs = null;
    	String query = "SELECT * FROM EMP";
    	
    	try {
    		PreparedStatement pstmt = this.conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		List<Employee> list = new ArrayList<>();
    		while (rs.next()) {
    			int empno = rs.getInt(1);
    			String ename = rs.getString(2);
    			String job = rs.getString(3);
    			int mgr = rs.getInt(4);
    			String hiredate = rs.getString(5);
    			double sal = rs.getDouble(6);
    			double comm = rs.getDouble(6);
    			int deptno = rs.getInt(6);
    			list.add(new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno));
    		}
    		return list;
    	} catch(Exception e) {
    		e.printStackTrace();
       	}
		return null;
    } // selectAllEmp() 끝.
	
	// Execute "INSERT" query
	public int insert(Employee emp) {
		try {
			String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, to_date(?, 'dd-mm-yyyy'), ?, ?, ?)";

			PreparedStatement pstmt = this.conn.prepareStatement(query);
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
					
			PreparedStatement pstmt = this.conn.prepareStatement(query);
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
			
			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, empno);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}

