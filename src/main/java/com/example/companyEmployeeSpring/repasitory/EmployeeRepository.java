package com.example.companyEmployeeSpring.repasitory;

import com.example.companyEmployeeSpring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
