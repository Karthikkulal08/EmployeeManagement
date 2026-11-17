package com.karthik.Employee.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private int departmentId;
    private String departmentName;
    private String departmentManager;
}
