package ex04.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ex03.Student;
import oracle.net.aso.s;

// Dao : Data Access Object
// insert, delete, update, select
public class StudentDao {

	Connection conn = null;
	
	//생성자
	public  StudentDao() {
		conn = DBConn.getInstance();
	}
	
	//close
	public  void close() {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// getStudent(in_stid)
	public StudentDTO getStudent(int in_stid) {
		
		StudentDTO        dto   = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null; 
		String            sql   = "";
		sql   += " SELECT STID, STNAME, PHONE, INDATE ";
		sql   += " FROM STUDENT ";
		sql   += " WHERE STID = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, in_stid);
			rs    = pstmt.executeQuery();
			if(rs.next()) {
				dto = new StudentDTO();
				dto.setStid(rs.getInt("STID"));
				dto.setStname(rs.getString("STNAME"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setIndate(rs.getString("INDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
		}
		}
		
		return dto;
	}
	// getStudentList() : 목록 조회
	public ArrayList<StudentDTO> getStudentList() {
		ArrayList<StudentDTO> studList = new ArrayList<StudentDTO>();
		String sql = " SELECT STID, STNAME, PHONE, INDATE ";
		sql       += " FROM STUDENT ";
		sql       += " ORDER BY STID ASC ";
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				int     stid    = rs.getInt("STID");
				String  stname  = rs.getString("STNAME");
				String  phone  = rs.getString("PHONE");
				String  indate  = rs.getString("INDATE");
				StudentDTO stud = new StudentDTO(stid, stname, phone, indate);
				studList.add(stud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)  pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
		return studList;
	}

}
