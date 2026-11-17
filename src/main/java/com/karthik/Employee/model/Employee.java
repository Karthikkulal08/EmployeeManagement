package com.karthik.Employee.model;

import com.karthik.Employee.dtos.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int empId;
    private String empName;
    private String empRole;
    private String empShift;
    private int empSalary;
    private int empAge;
    private DepartmentDTO department;
}
