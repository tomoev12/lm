package ex04.view;

import java.util.ArrayList;
import java.util.Scanner;

import ex04.model.StudentDTO;
import ex04.model.StudentDao;

public class TestStudent {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		//번호입력 검색
		System.out.println("번호입력 : ");
		int in_stid = in.nextInt();
		
		StudentDao sDao = new StudentDao();
		
		// stud 는 oracle이 조회한 data를 담는 객체
		StudentDTO stud = sDao.getStudent(in_stid);
		if(stud == null)
			System.out.println("조회한 자료가 없습니다.");
		else
			System.out.println(stud);
		
		//---------------------------------
		ArrayList<StudentDTO> studList = sDao.getStudentList();
		if(studList.size() == 0)
			System.out.println("조회한 자료가 없습니다.");
		else
			for (StudentDTO student : studList) {
				System.out.println(student);
		}
		sDao.close();
	
	}

}
