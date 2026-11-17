package com.karthik.Employee.dtos;

import lombok.Data;

@Data
public class ModelMapperResponseDTO {
	private String id;
	private String firstName;
	private String lastName;
	private String department;
	private Integer employeeCode;
}
