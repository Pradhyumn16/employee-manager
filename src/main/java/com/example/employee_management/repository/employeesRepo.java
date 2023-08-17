package com.example.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management.employee.employees;

public interface employeesRepo extends JpaRepository<employees, Long> {

}
