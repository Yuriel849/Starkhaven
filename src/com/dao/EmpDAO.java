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
				wrap.setEmpno(rs.getInt(1));
				wrap.setEname(rs.getString(2));
				wrap.setJob(rs.getString(3));
				wrap.setMgr(rs.getInt(4));
				wrap.setHiredate(rs.getString(5));
				wrap.setSal(rs.getDouble(6));
				wrap.setComm(rs.getDouble(7));
				wrap.setDeptno(rs.getInt(8));
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
			String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, to_date(?, 'yyyy-mm-dd'), ?, ?, ?)";

			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setString(5, emp.getHiredate());
			pstmt.setDouble(6, emp.getSal());
			pstmt.setDouble(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int update(Employee emp) {
		try {
			String query = "UPDATE EMP SET ENAME = ?, JOB = ?, MGR = ?, HIREDATE = to_date(?, 'yyyy-mm-dd'), SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";

			PreparedStatement pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getMgr());
			pstmt.setString(4, emp.getHiredate());
			pstmt.setDouble(5, emp.getSal());
			pstmt.setDouble(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpno());
			System.out.println(pstmt);
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