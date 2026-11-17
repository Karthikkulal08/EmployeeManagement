package com.karthik.Employee.BeforeadviceAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentServiceAspect {

	@Pointcut("execution(* com.karthik.Employee.BeforeAdviceService.StudentService.*(..)) ")
	public void anyStudentService(){}

	@Before("anyStudentService() && args(fname,sname)")
	public void beforeAdvice(JoinPoint joinPoint,String fname,String sname){
		System.out.println("Before method:" + joinPoint.getSignature()+ "\n Adding Student with first name- "+ fname + ", second name- "+sname);
	}
}
