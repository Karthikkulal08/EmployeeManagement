package com.karthik.Employee.BeforeAdviceController;

import com.karthik.Employee.BeforeAdviceModal.Student;
import com.karthik.Employee.BeforeAdviceService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping(value="/add")
	public Student addStudent(
			@RequestParam("firstName")String firstName,
			@RequestParam("secondName")String secondName){
		return studentService.addStudent(firstName,secondName);
	}

	@GetMapping("/student/{name}")
	public String greet(@PathVariable String name){
		return studentService.greetStudent(name);
	}

}
