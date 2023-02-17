package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Testlog01 {

	   // db 설정 - oracle 연결 문자열 connection string 
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@192.168.0.8:1521:xe";
	private static String dbuid  = "test";
	private static String dbpwd  = "1234";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 시도, 우편번호 갯수
		Scanner in  = new Scanner(System.in);
		System.out.println("아이디를 입력해주세요");
		String scan1 = in.nextLine();
		System.out.println("패스워드를 입력해주세요");
		String scan2 = in.nextLine();
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
		Statement  stmt = conn.createStatement();
		String sql = "";
		sql   +=  " SELECT * ";
		sql   +=  " FROM MEMBER ";
		sql   +=  " WHERE  LOWER(EMAIL)  = '" + scan1 +  "' AND  BYEAR ='" + scan2+"'";
				System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);
		
			if(rs.next()) {
			System.out.println("로그인성공");
			}
			else {
				System.out.println("로그인실패");
			}
			

		 rs.close();
		 stmt.close();
		 conn.close();
		
		
	}

}
