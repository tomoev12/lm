package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestPost01 {
	   // 동명, 건물명 검색 우편번호, 주소 출력하기
	   // db 설정 - oracle 연결 문자열 connection string 
   private static String driver = "oracle.jdbc.OracleDriver";
   private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
   private static String dbuid  = "test";
   private static String dbpwd  = "1234";
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner    in = new Scanner(System.in);
		
		System.out.println("조회할 동명/건물명을 입력하세요");
		String findDong  = in.nextLine();
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
		Statement  stmt = conn.createStatement();
		String     sql  = "";
		sql += "SELECT ZIPCODE, SIDO, GUGUN, DONG, NVL(BUNJI,' ') BUNJI";
		sql += " FROM  POST ";
	    sql += " WHERE DONG LIKE '% " + findDong + "%' ";
		sql += " ORDER BY SEQ ASC ";
		System.out.println(sql);
		ResultSet  rs   = stmt.executeQuery(sql);
		while( rs.next() ) {
			String zipcode = rs.getString("ZIPCODE");
			String sido    = rs.getString("SIDO");
			String gugun   = rs.getString("GUGUN");
			String dong    = rs.getString("DONG");
			String bunji   = rs.getString("BUNJI");
			String fmt     = "%s %s %s %s %s";
			String msg     = String.format(fmt, zipcode,sido,gugun,dong,bunji);
			System.out.println( msg );
		}
		rs.close();
		stmt.close();
		conn.close();
	}

}
