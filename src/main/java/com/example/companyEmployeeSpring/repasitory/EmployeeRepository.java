package com.example.companyEmployeeSpring.repasitory;

import com.example.companyEmployeeSpring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

}
