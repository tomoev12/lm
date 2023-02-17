package ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestMember01 {

	
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@192.168.0.8:1521:xe";
	private static String dbuid  = "test";
	private static String dbpwd  = "1234";
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Scanner in = new Scanner(System.in);
	System.out.println("아이디 : ");
	String  uid = in.nextLine();
	System.out.println("암호 : ");
	String  pwd = in.nextLine();
	
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
	Statement  stmt = conn.createStatement();
	String     sql  = "";
	sql   =  " SELECT NUM, NAME, BYEAR, GENDER, PHONE, EMAIL, JOINDATE ";
	sql  +=  " FROM MEMBER";
	sql  +=  " WHERE LOWER(EMAIL)  = '" + "tomoev12@kakao.com" + "' ";
	sql  +=  " AND BYEAR = " + pwd;
	System.out.println(sql);
	ResultSet  rs   = stmt.executeQuery(sql);
	if( rs.next() ) {
		String msg = rs.getString("name")+ "님 환영합니다.";
		System.out.println(msg);
	}
	else {
		System.out.println("로그인실패하였습니다.");
	}
	
	rs.close();
	stmt.close();
	conn.close();
	}

}
