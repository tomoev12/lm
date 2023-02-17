package ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Test002 {

	private static String driver = "oracle.jdbc.OracleDriver";
	private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuid  = "study";
	private static String dbpwd  = "1234";
	
	// test 게정의 Student 에 자료 추가
	// INSERT INTO STUDENT VALUES ()
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		adddata("강령2","011-1394-1111");
		adddata("봄2","011-2292-2222");
		adddata("추위2","011-2296-3333");
		System.out.println("------------추가끝---------------");
		*/
		//전체조회
		//getDataList(); // 자신이 출력
		
		//넘어온자료 = getDataList2(); // 자신이 출력x
		ArrayList<Student> studList = getDataList2(); 
		
		for (Student student : studList) {
	
			System.out.println(student.toString());
		}
		
		// 넘어온 자료 출력
		System.out.println("------------조회끝---------------");
		
	}

	private static ArrayList<Student> getDataList2() throws ClassNotFoundException, SQLException {
		ArrayList<Student> slist = new ArrayList<Student>();
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
		String sql = "";
		sql  += " SELECT STID, STNAME, PHONE, TO_CHAR(INDATE, 'YYYY-MM-DD') INDATE FROM STUDENT ";  
		sql  += " ORDER BY STID ASC ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet         rs    = pstmt.executeQuery();
		while( rs.next() ) {
//          생성자를 이용한 값 저장법
//			int     stid   = rs.getInt("STID");
//			String  stname = rs.getString("STNAME");
//			String  phone  = rs.getString("PHONE");
//			String  indate = rs.getString("INDATE");
//		    Student s      = new Student(stid,stname,phone,indate);

//          setter 를 이용한 값 저장법			
			Student s      = new Student();
			s.setStid(rs.getInt("STID"));
			s.setStname(rs.getString("STNAME"));
			s.setPhone(rs.getString("PHONE"));
			s.setIndate(rs.getString("INDATE"));
			slist.add(s);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return slist;
		
	}

	private static void getDataList() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dburl,dbuid,dbpwd);
		String sql = "";
		sql  += " SELECT STID, STNAME, PHONE, TO_CHAR(INDATE, 'YYYY-MM-DD') INDATE FROM STUDENT ";  
		sql  += " ORDER BY STID ASC ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet         rs    = pstmt.executeQuery();
		while( rs.next() ) {
			int     stid   = rs.getInt("STID");
			String  stname = rs.getString("STNAME");
			String  phone  = rs.getString("PHONE");
			String  indate = rs.getString("INDATE");
			String  msg    = String.format("%d %s %s %s", stid,stname,phone,indate); 
			System.out.println(msg);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
	}

	private static void adddata(String name, String phone) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection          conn  = DriverManager.getConnection(dburl,dbuid,dbpwd);
		String              sql   = "";
		sql  += " INSERT INTO STUDENT (STID, STNAME, PHONE ) ";
		sql  += " VALUES ( (SELECT NVL(MAX(STID),0)+1 FROM STUDENT ), ?,? )";
	
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		int aftcnt = pstmt.executeUpdate(); // insert,delete,update,create,alter,drop
		if(aftcnt ==0) {
			System.out.println("저장실패");
		} else {
			System.out.println(aftcnt + "건 저장되었습니다.");			
		}
	    pstmt.close();
	    conn.close();
		
	}
}