package ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestMember02_PreparedStatement {

	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@192.168.0.8:1521:xe";
	private static String dbuid  = "test";
	private static String dbpwd  = "1234";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Scanner in = new Scanner(System.in);
	System.out.println("아이디 : ");
	String uid = in.nextLine();
	System.out.println("비밀번호 : ");
	int    pwd = in.nextInt();
		
	Class.forName(driver);
	Connection          conn  = DriverManager.getConnection(dburl,dbuid,dbpwd);
	String              sql   = "";
	sql                       = "";
	sql  +=  " SELECT NUM, NAME, BYEAR, GENDER, PHONE, EMAIL, JOINDATE ";
	sql  +=  " FROM  MEMBER ";
	sql  +=  " WHERE EMAIL = ? "; // 첫번째 파라미터 문자열
	sql  +=  " AND   BYEAR = ? "; // 두번째 파라미터 문자열
	System.out.println(sql);
	PreparedStatement   pstmt = conn.prepareStatement(sql);
	// 파라미터 설정 : 준비된 sql문의 ?에 파라미터를 type에 맞게 지정한다-?갯수만큼
	pstmt.setString(1,uid); // 첫번째 ?는 .setString()명령은 자동으로 '' 추가해줌 -중요 
	pstmt.setInt(2, pwd);
	ResultSet           rs    = pstmt.executeQuery(); // sql 변수사용하면 error 
	if( rs.next() ) { // 결과가 하나이거나 없을때는 if, 여러개면 while()
		System.out.println("로그인완료" + rs.getString("name") + "님");
	} else {
		System.out.println("아이디와 암호가 틀립니다.");
	}
	
	
	rs.close();
	pstmt.close();
	conn.close();
	}

}
