package com.springbootthymeleaf.springbootthymeleaf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootthymeleaf.springbootthymeleaf.model.Employee;
import com.springbootthymeleaf.springbootthymeleaf.repository.Employeerepository;
import com.springbootthymeleaf.springbootthymeleaf.service.Employeeservice;

@Controller
public class Employeecontroller {
    @Autowired
    private Employeeservice employeeservice;
    @Autowired
    private Employeerepository emprepo;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeservice.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeservice.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") int id, Model model) {

        // get employee from the service
        Employee employee = employeeservice.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {

        // call delete employee method 
        this.employeeservice.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/loginform")
    public String loginForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "login";
    }


    @PostMapping("/login")
    public String login_user(@RequestParam("firstname") String name,@RequestParam("password") String password, HttpSession session,ModelMap modelMap)
     { Employee auser=emprepo.findByNamePassword(name, password);
         if(auser!=null) { 
            String uname=auser.getFirstname();
             String upass=auser.getPassword();
              if(name.equals(uname) && password.equals(upass)){ session.setAttribute("employee",name);
              return "dummy";
             }
             else {
                 modelMap.put("error", "Invalid Account");
                  return "redirect:/login";
                 }
                 } else{
                    modelMap.put("error", "Invalid Account");
                     return "redirect:/login";
                     }
 }
}
