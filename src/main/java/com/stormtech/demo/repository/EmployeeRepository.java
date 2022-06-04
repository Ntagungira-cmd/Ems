package com.stormtech.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stormtech.demo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
