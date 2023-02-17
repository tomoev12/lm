package ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test001 {
	
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuid  = "hr";
	private static String dbpwd  = "1234";
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
		String kname = "%" + "kimberely" + "%"; // 조건식 절대안됨
	
		Class.forName(driver);
		Connection          conn  = DriverManager.getConnection(dburl,dbuid,dbpwd);
		String              sql   = "";
		sql     = " SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY , RANK() OVER (ORDER BY SALARY DESC) SALARYRANK\r\n"
				+ " FROM EMPLOYEES\r\n"
				+ " WHERE SALARY > (SELECT SALARY\r\n"
				+ " FROM EMPLOYEES\r\n"
				+ " WHERE LOWER(FIRST_NAME) LIKE ?)";


		PreparedStatement   pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,kname); 
		
		ResultSet           rs    = pstmt.executeQuery(); 
		int cnt = 0;
		while( rs.next() ) {
			int employee_id = rs.getInt("EMPLOYEE_ID");
			String first_name = rs.getString("FIRST_NAME");
			String last_name = rs.getString("LAST_NAME");
			int salary = rs.getInt("SALARY");
			int salaryrank = rs.getInt("SALARYRANK");
			String fmt = " %d %s %s %d %d";
			String msg     = String.format(fmt, employee_id, first_name,
					                       last_name, salary,salaryrank); 
			System.out.println(msg);
			cnt++;
		}
		System.out.println(cnt + "건 조회되었습니다");
		rs.close();
		pstmt.close();
		conn.close();
	}

}
