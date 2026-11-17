package com.karthik.Employee.PerformanceMonitorAround;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
public class PerformanceMonitorStudentService {
	@Pointcut("execution(* com.karthik.Employee.BeforeAdviceService.StudentService.*(..))")
	public void anyStudentService(){}

	@Around("anyStudentService()")
	public Object measureTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long endTime = System.currentTimeMillis();

		long duration = endTime - startTime;

		System.out.println("Method " + proceedingJoinPoint.getSignature().getName() +
				" took " + duration + " ms to execute.");

		return result;
	}

}
