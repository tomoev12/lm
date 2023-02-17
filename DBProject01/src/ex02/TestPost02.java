package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPost02 {

	   // db 설정 - oracle 연결 문자열 connection string 
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuid  = "test";
	private static String dbpwd  = "1234";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 시도, 우편번호 갯수
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
		Statement  stmt = conn.createStatement();
		String sql = "";
		sql   +=  " SELECT SIDO , COUNT(ZIPCODE) SIDOCNT";
		sql   +=  " FROM POST ";
		sql   +=  " GROUP BY SIDO ";
		sql   +=  " ORDER BY SIDO ";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			String sido    = rs.getString("SIDO");
			int sidoCnt = rs.getInt("SIDOCNT");
			String msg     = String.format("%s %d", sido,sidoCnt);
			System.out.println(msg);
		}
		
		 rs.close();
		 stmt.close();
		 conn.close();
		
		
	}

}
