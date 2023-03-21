package com.springbootthymeleaf.springbootthymeleaf.service;

import java.util.List;

import com.springbootthymeleaf.springbootthymeleaf.model.Employee;

public interface Employeeservice {
    List < Employee > getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void deleteEmployeeById(int id);
    
}
