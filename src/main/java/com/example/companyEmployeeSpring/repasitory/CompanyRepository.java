package com.example.companyEmployeeSpring.repasitory;

import com.example.companyEmployeeSpring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
