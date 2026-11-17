package com.karthik.Employee.BeforeAdviceService;

import com.karthik.Employee.BeforeAdviceModal.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	public Student addStudent(String fname,String sname){
		System.out.println("Add student service method called");
		Student stud = new Student();
		stud.setFirstName(fname);
		stud.setSecondName(sname);
		return stud;
	}

	public String greetStudent(String studentName){
		return "<h1>Hello "+studentName+"</h1>";
	}
}
