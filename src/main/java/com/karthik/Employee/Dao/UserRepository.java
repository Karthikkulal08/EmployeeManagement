package com.karthik.Employee.Dao;

import com.karthik.Employee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
