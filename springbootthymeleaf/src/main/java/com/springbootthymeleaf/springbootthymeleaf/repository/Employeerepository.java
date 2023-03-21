package com.springbootthymeleaf.springbootthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootthymeleaf.springbootthymeleaf.model.Employee;

@Repository
public interface Employeerepository extends JpaRepository<Employee, Integer>{
    @Query("from Employee where firstname=?1 and password=?2")
    public Employee findByNamePassword(String name,String password);
}
