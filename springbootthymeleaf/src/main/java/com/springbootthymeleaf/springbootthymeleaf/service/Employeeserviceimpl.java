package com.springbootthymeleaf.springbootthymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootthymeleaf.springbootthymeleaf.model.Employee;
import com.springbootthymeleaf.springbootthymeleaf.repository.Employeerepository;

@Service
public class Employeeserviceimpl implements Employeeservice{
    @Autowired
    private Employeerepository employeerepository;

    @Override
    public List < Employee > getAllEmployees() {
        return employeerepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeerepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional < Employee > optional = employeerepository.findById(id);
            Employee employee = null;
            if (optional.isPresent()) {
                employee = optional.get();
            } else {
                throw new RuntimeException(" Employee not found for id :: " + id);
            }
            return employee;
    }

    @Override
    public void deleteEmployeeById(int id) {
        // TODO Auto-generated method stub
        this.employeerepository.deleteById(id);
    }

   
   }
