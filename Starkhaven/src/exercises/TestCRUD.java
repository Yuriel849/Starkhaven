package exercises;

import com.dao.EmpDAO;
import com.dto.Employee;

class TestCRUD {
	public static void main(String[] args) {
		EmpDAO eDAO = EmpDAO.getInstance();

		// Test "INSERT"
		Employee testee = new Employee(1234, "TESTEE", "TEST", 5678, "1-1-2000", 500.0, 500.0, 10);
		eDAO.insert(testee);
		
//		// Test "UPDATE"
//		Employee testee = new Employee(1234, "john", "shooter", 8754, "1-1-2010", 500.0, 500.0, 10);
//		eDAO.update(testee);
//		
//		// Test "DELETE"
//		eDAO.delete("1234");
//		
//		// Test "SELECT"
//		Employee recipient = eDAO.selectByEmpNo("7369");
//		System.out.println(recipient.getEMPNO());
//		System.out.println(recipient.getENAME());
//		System.out.println(recipient.getJOB());
//		System.out.println(recipient.getMGR());
//		System.out.println(recipient.getHIREDATE());
//		System.out.println(recipient.getSAL());
//		System.out.println(recipient.getCOMM());
//		System.out.println(recipient.getDEPTNO());
	}
}