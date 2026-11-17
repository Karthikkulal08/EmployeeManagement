package com.karthik.Employee.CustomException;

import org.springframework.stereotype.Component;


public class CustomException extends RuntimeException{
	public CustomException(String message){
		super(message);
	}
}
