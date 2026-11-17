package com.karthik.Employee.schedulars;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainSchedular {
	@Scheduled(cron = "1 * * * * *")
	public void GreetSchedular(){
		System.out.println("Greetings from schedular");
	}
}
