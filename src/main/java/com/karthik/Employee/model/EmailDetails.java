package com.karthik.Employee.model;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;

	@Data
	public static class GenericTypeClassAndMethod<T> {
		private T value1;
		private T value2;
	}
}
