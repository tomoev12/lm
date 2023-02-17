package ex01;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestEmp01 {

   // db 설정 - oracle 연결 문자열 connection string 
   private static String driver = "oracle.jdbc.OracleDriver";
   private static String dburl  = "jdbc:oracle:thin:@localhost:1521:xe";
   private static String dbuid  = "hr";
   private static String dbpwd  = "1234";
   
   public static void main(String[] args) throws ClassNotFoundException, SQLException {
      //이름을 입력받아 kim을 포함하는 직원번호, 이름을 출력
      Class.forName(driver); // Oracle driver 를 사용하겠다
      Connection conn = DriverManager.getConnection(dburl, dbuid, dbpwd); 
      //지정된 db를 사용하겠다
      
      Statement stmt =conn.createStatement(); //sql 문장이 담길 객체를 생성
      String     sql = "select employee_id, first_name, last_name from employees";
      ResultSet   rs = stmt.executeQuery(sql);
      while ( rs.next() ) {
         int empid = rs.getInt("employee_id");
         String fname = rs.getString("first_name");
         String lname = rs.getString("last_name");
         
         System.out.printf("%d %s %s\n", empid, fname, lname);
      }
      rs.close();
      stmt.close();
      conn.close();
   }
}