package ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestStudent01 {

	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuid  = "study";
	private static String dbpwd  = "1234";
	
	// test 게정의 Student 에 자료 추가
	// INSERT INTO STUDENT VALUES ()
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection          conn  = DriverManager.getConnection(dburl,dbuid,dbpwd);
		String              sql   = "";
		sql  += " INSERT INTO STUDENT (STID, STNAME, PHONE) ";
		sql  += " VALUES ( (SELECT NVL(MAX(STID),0)+1 FROM STUDENT),  ?,  ?)";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "카리나");
		pstmt.setString(2, "010-1234-4121");
		int aftcnt = pstmt.executeUpdate(); // insert,delete,update,create,alter,drop
		if(aftcnt ==0) {
			System.out.println("저장실패");
		} else {
			System.out.println("저장되었습니다.");			
		}
	    pstmt.close();
	    conn.close();
	}

}
