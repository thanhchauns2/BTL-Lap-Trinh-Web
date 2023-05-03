package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.*;
import com.example.demo.service.EmployeeService;

@RequestMapping("/employees")
@Controller
public class EmployeeController {
	
	@Autowired
    private EmployeeService employeeService;
	
	@GetMapping
	public String home(Model model) {
		Iterable<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		return "/employees/employees.html";
	}
	
	@GetMapping("/new")
	public String add_new(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/add-new.html";
	}
	
	@PostMapping("/save")
	public String save(Model model, @Valid Employee employee, Errors errors) {
		if(errors.hasErrors())
			return "employees/new-employee";
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		employeeService.delete(theEmp);
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		employeeService.delete(theEmp);
		model.addAttribute("employee", theEmp);
		return "employees/add-new.html";
	}
}
