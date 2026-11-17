package com.karthik.Employee.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="app_users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
}
